package com.renren.xcache.pool;

import java.io.ByteArrayInputStream;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.KeeperException.NoNodeException;

import com.renren.xcache.zookeeper.ZooKeeperFactory;

/**
 * 基于ZooKeeper配置服务的{@link CachePoolFactory}实现。
 * 
 * @author Li Weibo (weibo.leo@gmail.com) //I believe spring-brother
 * @since 2010-9-21 下午06:18:36
 */
public class ZooKeeperBasedPoolFactory extends AbstractPoolFactory {

	public static final String ROOT_PATH = "/pools/";
	
	public static final String EXT = ".pl";
	
	private ZooKeeper zookeeper;
	
	private Watcher zookeeperWatcher = new ZookeeperWatcher();
	
	private static ZooKeeperBasedPoolFactory instance = new ZooKeeperBasedPoolFactory();
	
	public static ZooKeeperBasedPoolFactory getInstance() {
		return instance;
	}

	private ZooKeeperBasedPoolFactory() {
		init();
	}
	
	@Override
	protected CachePool loadPoolConfig(String poolName) {
		try {
			byte[] data = zookeeper.getData(getConfigPath(poolName), zookeeperWatcher, null);
			return CachePoolUtil.loadFromConfig(poolName, new ByteArrayInputStream(data));
		} catch(NoNodeException e) {
			return null;
		} catch (Exception e) {
			throw new RuntimeException(
					"Error occurs while loading pool config: " + poolName, e);
		} 
	}

	private String getConfigPath(String poolName){
		return ROOT_PATH + poolName + EXT;
	}
	
	public void init() {
		zookeeper = ZooKeeperFactory.newZooKeeper(zookeeperWatcher);
	}
	
	private class ZookeeperWatcher implements Watcher {
		@Override
		public void process(WatchedEvent event) {
			
			String path = event.getPath();
	        if (event.getType() == Event.EventType.None) {
	            // We are are being told that the state of the
	            // connection has changed
	            switch (event.getState()) {
	            case SyncConnected:
	                // In this particular example we don't need to do anything
	                // here - watches are automatically re-registered with 
	                // server and any watches triggered while the client was 
	                // disconnected will be delivered (in order of course)
	                break;
	            case Expired:
	            	logger.error("Zookeeper session expired:" + event);
	            	ZooKeeper old = zookeeper;
	            	if (logger.isInfoEnabled()) {
	            		logger.info("re-initializing ZooKeeper");
	            	}
	            	init();	//重新初始化一下，也就是重新连接一下
	            	if (logger.isInfoEnabled()) {
	            		logger.info("closing expired ZooKeeper:" + old.hashCode());
	            	}
	            	try {
						old.close();	//将旧的关闭掉
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
	                break;
	            }
	        } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
	        	//do nothing
	        } else if (event.getType() == Event.EventType.NodeDeleted) {
	        	/*if (logger.isInfoEnabled()) {
	        		logger.info("node deleted:" + path);
	        	}*/
	        	
	        	//do nothing
	        } else if (event.getType() == Event.EventType.NodeDataChanged) {
	        	/*int index = path.indexOf(SERVICE_NODE_FOLDER);
	        	if (index > 0) {
					String serviceId = toServiceId(path.substring(0, index
							+ SERVICE_NODE_FOLDER.length()));
					if (logger.isInfoEnabled()) {
						logger.info("config changed for service: " + serviceId
								+ ", caused by node change:" + path);
					}
					updateServiceNodes(serviceId, getServiceNodes(serviceId));
	        	} else {
	        		
	        	}*/
	        	int index = path.indexOf(ROOT_PATH);
	        	if (index >= 0) {
	        		String fileName = path.substring(ROOT_PATH.length());
	        		if (fileName.endsWith(EXT)) {
	        			String poolName = fileName.substring(0, fileName.length() - EXT.length());
	        			CachePool newPool = loadPoolConfig(poolName);
	        			onPoolModified(newPool);
	        		} else {
	        			logger.warn("Unrecognized fileName:" + event);
	        		}
	        	} else {
	        		logger.warn("Unrecognized event:" + event);
	        	}
	        	
	        } else {
	        	logger.warn("Unhandled event:" + event);
	        }
		}
	} 
}
