/**
 * 
 */
package com.xiaonei.passport.task.task;

import com.xiaonei.feed.edm.adapter.FeedEdmAdapter;
import com.xiaonei.passport.logger.LoginLoggerDef;
import com.xiaonei.passport.logger.PpLogger;
import com.xiaonei.passport.task.followmission.IFollowMissionTask;
import com.xiaonei.passport.util.StatUtil;

/**
 * @author lookis (comic.liu@gmail.com)
 * 
 */
public class FeedTask implements IFollowMissionTask {

	private static PpLogger log = LoginLoggerDef.feedTask;

	private int userId = 0;
	private String ip;

	public FeedTask(int userId, String ip) {
		this.userId = userId;
		this.ip = ip;
	}

	@Override
	public void run() {
		StatUtil su = new StatUtil(log);
		log.info("execute FeedEdmAdapter start id:" + userId);
		try {
			FeedEdmAdapter.getInstance().add(userId, ip);
		} catch (Exception e) {
			log.error("FeedEdmAdapter error", e);
		}
		su.stepTimeCost("1:FeedTask:submit");
		log.info("execute FeedEdmAdapter end");
		su.getWholeTime("FeedTaskSuccFinish");
	}

	@Override
	public String getName() {
		return "FeedTask";
	}

}