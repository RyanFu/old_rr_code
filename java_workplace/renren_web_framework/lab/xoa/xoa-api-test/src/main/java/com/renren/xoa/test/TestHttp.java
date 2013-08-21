package com.renren.xoa.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.renren.xoa.http.HttpClientFactory;

/**
 * @author Li Weibo (weibo.leo@gmail.com) //I believe spring-brother
 * @since 2010-3-9 下午04:31:47
 */
public class TestHttp {

	private AtomicBoolean running = new AtomicBoolean(true);
	
	private AtomicInteger sent = new AtomicInteger();
	
	private AtomicInteger succ = new AtomicInteger();
	
	private AtomicInteger fail = new AtomicInteger();
	
	private ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(100);
	
	private long startTime;
	
	private Runnable monitor = new Runnable() {
		@Override
		public void run() {
			long currentTime = System.currentTimeMillis();
			int duration = (int)((currentTime - startTime) / 1000);
			int s = sent.get();
			int r = succ.get();
			int f = fail.get();
			System.out.println(duration + "s (sent:" + s + ", " + 1.0 * s / duration
					+ "/s), (received:" + r + ", " + 1.0 * r / duration + "/s), (fail:" + f + ")");
		}
	};
	
	private void startMonitor() {
		startTime = System.currentTimeMillis();
		int duration = 10;
		threadPool.scheduleAtFixedRate(monitor, duration, duration, TimeUnit.SECONDS);
	}
	
	private class TestTask implements Runnable {

		private String domain;
		
		private String path;
		
		private HttpClient client;
		
		public TestTask(String domain, String path) {
			this.domain = domain;
			this.path = path;
			//client = HttpClientFactory.getInstance().getHttpClient();
		}
		
		@Override
		public void run() {
			while (running.get()) {
				try {
					
					HttpMethod method = new GetMethod("http://" + domain + path);
					client.executeMethod(method);
					sent.incrementAndGet();
					if (method.getStatusCode() == 200) {
						System.out.println(method.getResponseBodyAsString());
						succ.incrementAndGet();
					} else {
						fail.incrementAndGet();
					}
					method.releaseConnection();
					//Thread.sleep(100);
					//break;
				} catch (Exception e) {
					e.printStackTrace();
					fail.incrementAndGet();
				}
			}
		}
	}
	
	private static void singleThreadTest(String domain, String path) {
		
		System.out.println("start single thread test");
		/*String domain = "10.3.21.36:8888";
		String path = "/spdy/index.jsp";*/
		System.out.println(domain + path);
		TestHttp app = new TestHttp();
		app.startMonitor();
		app.threadPool.execute(app.new TestTask(domain, path));
	}
	
	public static void main(String[] args) {
		
		if (args.length != 2) {
			System.out.println("Usage:java xxx domain path");
			return;
		} 
		
		singleThreadTest(args[0], args[1]);
	}
	
}
