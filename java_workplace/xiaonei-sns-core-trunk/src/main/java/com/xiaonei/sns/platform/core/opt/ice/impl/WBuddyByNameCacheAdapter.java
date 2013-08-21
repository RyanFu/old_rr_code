package com.xiaonei.sns.platform.core.opt.ice.impl;

import java.util.List;

import com.xiaonei.sns.platform.core.opt.ice.IBuddyByNameCacheAdapter;
import com.xiaonei.xce.buddybynamecache.BuddyByNameCacheAdapter;

/**
 * 
 * @author michael(liyong@opi-corp.com)
 * 
 */
public class WBuddyByNameCacheAdapter extends
		BaseUsersAdapter<BuddyByNameCacheAdapter> implements
		IBuddyByNameCacheAdapter {

	protected WBuddyByNameCacheAdapter() {
	}

	@Override
	public int getFriendCount(int userId) {
		return getAdapter().getFriendCount(userId);
	}

	@Override
	public List<Integer> getFriendList(int userId, int limit) {
		return getAdapter().getFriendList(userId, limit);
	}

	@Override
	public List<Integer> getFriendList(int userId) {
		return getFriendList(userId, -1);
	}

	@Override
	protected String getServiceName() {
		return "buddy by name cache";
	}

	@Override
	protected BuddyByNameCacheAdapter makeObject() {
		return BuddyByNameCacheAdapter.getInstance();
	}

}
