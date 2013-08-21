package com.xiaonei.xnmonitor.mailreport.classpack;

import java.util.LinkedList;
import java.util.List;

import com.xiaonei.xnmonitor.dto.IMInfo;
import com.xiaonei.xnmonitor.utils.UtilTools;

public class IMDNSPackage {

    public static List<IMInfo> getIMDNSReportList(List<Object[]> resultArr) {
        //url,IP,count(id) as total
        List<IMInfo> resultList = new LinkedList<IMInfo>();
        for (Object[] object : resultArr) {
            IMInfo imInfo = new IMInfo();
            imInfo.setUrlOrHost(object[0].toString());
            imInfo.setIP(object[1].toString());
            imInfo.setTotal(UtilTools.strToInt(object[2].toString()));
            imInfo.setPercent(0.0);
            resultList.add(imInfo);
        }
        return resultList;
    }
    public static List<IMInfo> getIMRemoteDNSReportList(List<Object[]> resultArr) {
    	// remote_host,url,ip,cost_time,type 
        List<IMInfo> resultList = new LinkedList<IMInfo>();
        for (Object[] object : resultArr) {
            IMInfo imInfo = new IMInfo();
            imInfo.setRemoteHost(object[0].toString());
            imInfo.setUrlOrHost(object[1].toString());
            imInfo.setIP(object[2].toString());
            imInfo.setCostTime(UtilTools.strToLong(object[3].toString()));
            imInfo.setType(object[4].toString());
        }
        return resultList;
    }

}
