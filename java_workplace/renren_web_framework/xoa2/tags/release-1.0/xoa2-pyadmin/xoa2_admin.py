#!/usr/local/bin/python
# -*- coding: utf8 -*-

import socket
import sys
import os
import fcntl  
import zookeeper
from termcolor import colored
import httplib
import urllib
import datetime
import time
use_color = True

def ERROR(msg):
  if use_color:
    return colored(msg, 'red')
  return msg

def SUCCESS(msg):
  if use_color:
    return colored(msg, 'green')
  return msg

def WARN(msg):
  if use_color:
    return colored(msg, 'yellow')
  return msg

def INFO(msg):
  if use_color:
    return colored(msg, 'magenta')
  return msg


def ParseNodeData(data):
  value = data[0].splitlines()
  ret_map = {}
  for line in value:
    if len(line) == 0 or line[0] == '#':
      continue
    pos = line.find('=')
    if pos > 0:
      ret_map[line[0:pos]] = line[pos + 1:]

  return ret_map

def OpenZkLog(log_path):
    zkinfolog = open(log_path, "aw")
#    s_fd1 = os.dup(1)
    s_fd2 = os.dup(2)

#    os.dup2(3, 1) 
    os.dup2(3, 2)
#    fcntl.flock(zkinfolog, fcntl.LOCK_EX)   
    return zkinfolog

def CloseZkLog(log_fd):
#    fcntl.flock(log_fd, fcntl.LOCK_UN)
#    os.dup2(4, 1)
    os.dup2(4, 2)
    log_fd.close()
    os.close(4)
#    os.close(5)


class Service:
  def __init__(self, zk, service_id):
    self.zk = zk  
    self.service_id = service_id
    self.alarm_phones = ['18611951882']

    self.endpoints = []

  def GetPath(self):
    keys = self.service_id.split('.')
    path = ""
    for key in reversed(keys):
      path += '/'
      path += key

    return path

  def SendSMS(self, phone, message):
    conn = httplib.HTTPConnection("10.3.18.198", 3000)
    url = "/asms?"
    data = {'contact' : phone, 
            'host_name' : '127.0.0.1', 
            'service_name' : 'xoa2',
            'state' : 'err',
            'message_content' : message}
    url += urllib.urlencode(data)
    print INFO(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + " SMS ALARM " + url)
    conn.request('GET', url)
    conn.close()

  def AlarmOwners(self, msg):
    for phone in self.alarm_phones:
      self.SendSMS(phone, msg)


  def IsValidId(self):
    if not self.service_id.endswith('.xoa.renren.com'):
      return False
    keys = self.service_id.split('.')
    if len(keys) != 5:
      return False
    return True

  def Load(self):
    if not self.IsValidId():
      print ERROR( datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + " bad service id:" + self.service_id)
      return False

    data_map = {}
    try:
      zkinfolog = OpenZkLog("./log.txt")
      service_data = zookeeper.get(self.zk, self.GetPath())
      CloseZkLog(zkinfolog)
      data_map = ParseNodeData(service_data)
    except zookeeper.NoNodeException, msg:
      print ERROR( datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + service_id + " node exist.")
      return False

    if 'alarm_phones' in data_map.keys():
      self.alarm_phones = data_map['alarm_phones'].split(',')
    else:
      print WARN(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + "service id:" + self.service_id + " no alarm phones config.")

    self.LoadEndpoints()

    return True

  def GetActiveGroupPath(self):
    return self.GetPath() + '/.service_group_0'

  def LoadEndpoints(self):
    group_path = self.GetActiveGroupPath()
    try:
      zkinfolog = OpenZkLog("./log.txt")
      group_children = zookeeper.get_children(self.zk, group_path, None);
      CloseZkLog(zkinfolog)
    except zookeeper.NoNodeException, msg:
      print ERROR(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + group_path + " node exist.")
      return

    for shard in group_children:
      shard_path = group_path + '/' + shard
      zkinfolog = OpenZkLog("./log.txt")
      shard_children = zookeeper.get_children(self.zk, shard_path, None);
      CloseZkLog(zkinfolog)
      for endpoint in shard_children:
        ep = Endpoint(self.zk, self, endpoint)
        self.endpoints.append(ep)

  def ShowStatus(self):
    print INFO(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + "service id is:" + self.service_id)
    print INFO(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + "alarm phones:" + str(self.alarm_phones))
    for ep in self.endpoints:
      print INFO(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + "endpoint is:" + ep.endpoint + "    status is:" + ep.Status() + "    healthy is:" + ep.Healthy())

class Endpoint:
  def __init__(self, zk, service, endpoint):
    self.zk = zk
 
    self.service = service
    self.endpoint = endpoint
    self.props = {}

  def Load(self):
    zkinfolog = OpenZkLog("./log.txt")
    endpoint_data = zookeeper.get(self.zk, self.GetPath())
    CloseZkLog(zkinfolog)
    self.props = ParseNodeData(endpoint_data)

  def GetPath(self):
    return self.service.GetActiveGroupPath() + '/shard_0/' + self.endpoint

  def Create(self):
    perm = [{"perms":0x1f,"scheme":"world","id":"anyone"}]
    
   #try:
   #  zookeeper.create(zk, GetPathById(self.service_id), "alarm_phones=18611951882", perm, 0); 
   #except zookeeper.NodeExistsException, msg:
   #  pass

   #try:
   #  zookeeper.create(zk, GetActiveGroupPath(self.service_id), "priority=1", perm, 0); 
   #except zookeeper.NodeExistsException, msg:
   #  pass

    try:
      zkinfolog = OpenZkLog("./log.txt")
      zookeeper.create(self.zk, self.GetPath(), "status=disabled", perm, 0); 
      CloseZkLog(zkinfolog)
    except zookeeper.NodeExistsException, msg:
      print ERROR(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + "endpoint is:" + endpoint + " already exists:" + msg)
      return False
    except zookeeper.NoNodeException, msg:
      print ERROR(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + "zookeeper.NoNodeException:" + msg)
      return False

    return True

  def UpdateProperty(self, prop, value):
    if prop in self.props:
      if value == self.props[prop]:
        return

    self.props[prop] = value;
    data = ""
    for (k,v) in self.props.items():
      data += k
      data += '='
      data += v
      data += '\r\n'

    print INFO(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + "set zk data " + self.GetPath() + ":" + data)
    zkinfolog = OpenZkLog("./log.txt")
    zookeeper.set(self.zk , self.GetPath(), data);
    CloseZkLog(zkinfolog)
    
  def Enable(self, enabled):
    if len(self.props) <= 0:
      self.Load()

    if enabled:
      self.UpdateProperty('status', 'enabled')
    else:
      self.UpdateProperty('status', 'disabled')
  
  def IsEnabled(self):
    if not self.props:
      self.Load()
    return ('status' in self.props.keys()) and (self.props['status'] == 'enabled')

  def Status(self):
    if self.IsEnabled():
      return SUCCESS('enabled')
    else:
      return ERROR('disabled')

  def CheckHealthy(self, host, port):
    for i in range(0,5):
      try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.settimeout(1)
      except socket.error, msg:
        continue

      try:
        sock.connect((host, port))
        sock.close()
        return True
      except socket.error, msg:
        print ERROR(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] check_error:") + host + ":" + str(port) + " " + str(msg))
        sock.close()
    return False
    
  def Healthy(self):
    pos = self.endpoint.find(':')
    host = self.endpoint[0 : pos]
    port = int(self.endpoint[pos + 1:])
    if self.CheckHealthy(host, port):
      self.UpdateProperty('healthy', '1')
      return SUCCESS('healthy')
    else:
      if self.IsEnabled():
        self.service.AlarmOwners("xoa2 " + self.service.service_id + " " + self.endpoint + " connect failed")
      self.UpdateProperty('healthy', '0')
      return ERROR('connect-failed')


def GetServiceIdByPath(service_path):
  keys = service_path.split('/')
  id = ""
  for key in reversed(keys):
    if key:
      if len(id) > 0:
        id += '.'
      id += key

  return id

class Xoa2Admin:
  def __init__(self, zk_addr):
    zkinfolog = OpenZkLog("./log.txt")
    self.zk = zookeeper.init(zk_addr)
    CloseZkLog(zkinfolog)

  def __del__(self):
    # TODO: zookeeper此时为何为None?
    if zookeeper:
      zkinfolog = OpenZkLog("./log.txt")
      zookeeper.close(self.zk)
      CloseZkLog(zkinfolog)

  

  def ListService(self, service_id = None, endpoint = None):
    if service_id:
      if endpoint:
        ep = Endpoint(self.zk, Service(self.zk, service_id), endpoint)
        print INFO(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + "    endpoint is:" + endpoint + "     status is:" + ep.Status() + "    healthy is:" + ep.Healthy())
        return ep
      else:
        service = Service(self.zk, service_id)
        service.Load()
	service.ShowStatus()
        return service
    else:
      zkinfolog = OpenZkLog("./log.txt")
      departments = zookeeper.get_children(self.zk, '/com/renren/xoa', None)
      CloseZkLog(zkinfolog)
      service_ids = []
      for department in departments:
        dp_path = '/com/renren/xoa/' + department
        zkinfolog = OpenZkLog("./log.txt")
        services = zookeeper.get_children(self.zk, dp_path, None)
        CloseZkLog(zkinfolog)
        for service in services:
          service_id = GetServiceIdByPath(dp_path + '/' + service)
          print INFO(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + "service id is:" + service_id)
          service_ids.append(service_id)
      return service_ids

  def CreateEndpoint(self, service_id, endpoint):
    ep = Endpoint(self.zk, Service(self.zk, service_id), endpoint)
    ep.Create()
    print INFO(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + "    endpoint is:" + endpoint + "    status is:" + ep.Status() + "    healthy is:" + ep.Healthy())

  def EnableEndpoint(self, service_id, endpoint, enabled):
    ep = Endpoint(self.zk, Service(self.zk, service_id), endpoint)
    print INFO(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + "    endpoint is:" + endpoint + "    status is:" + ep.Status() + "    healthy is:" + ep.Healthy())
    if enabled:
      word = "正在启用 "
    else:
      word = "正在禁用 "
    print INFO(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + word + endpoint + "...")
    ep.Enable(enabled)
    print INFO(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + "    endpoint is:" + endpoint + "    status is:" + ep.Status() + "    healthy is:" + ep.Healthy())

def Usage(reason = None):
  if reason:
    print ERROR(datetime.datetime.now().strftime("[%Y-%m-%d %H:%M:%S] ") + "    error reason is:" + reason)
  print "Usage:"
  print "> help"
  print "> list [service_id] [endpoint]"
  print "> create service_id endpoint"
  print "> enable service_id endpoint"
  print "> disable service_id endpoint"
  print ""

if __name__ == '__main__':
  use_color = True
  command = ""
  service_id = ""
  endpoint = ""

  if len(sys.argv) < 2:
    Usage()
    sys.exit(1)
  else:
    command = sys.argv[1]
  
  zk_addr = "10.11.21.185:2181,10.11.21.186:2181,10.3.18.186:2181/xoa2"
  #zk_addr = "10.3.22.60:2181,10.3.22.59:2181,10.3.22.63:2181,10.3.22.62:2181,10.3.22.61:2181/xoa2"

  if command == 'help':
    Usage()
  elif command == 'list':
    if len(sys.argv) > 2:
      service_id = sys.argv[2]
    if len(sys.argv) > 3:
      endpoint = sys.argv[3]
    admin = Xoa2Admin(zk_addr)
    admin.ListService(service_id, endpoint)
  elif command == 'create':
    if len(sys.argv) <= 3:
      Usage("bad parameter")
      sys.exit(1)
    admin = Xoa2Admin(zk_addr)
    admin.CreateEndpoint(sys.argv[2], sys.argv[3])
  elif command == 'disable':
    if len(sys.argv) <= 3:
      Usage("bad parameter")
      sys.exit(1)
    admin = Xoa2Admin(zk_addr)
    admin.EnableEndpoint(sys.argv[2], sys.argv[3], False)
  elif command == 'enable':
    if len(sys.argv) <= 3:
      Usage("bad parameter")
      sys.exit(1)
    admin = Xoa2Admin(zk_addr)
    admin.EnableEndpoint(sys.argv[2], sys.argv[3], True)
  else:
    Usage("unknown command : " + command)
else:
  use_color = False
