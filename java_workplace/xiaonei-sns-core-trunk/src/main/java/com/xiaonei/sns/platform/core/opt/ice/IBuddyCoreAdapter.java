package com.xiaonei.sns.platform.core.opt.ice;

import java.util.List;

import mop.hi.oce.domain.buddy.BuddyApply;
import mop.hi.oce.domain.buddy.BuddyApplyWithInfo;
import mop.hi.oce.domain.buddy.BuddyBlock;
import mop.hi.oce.domain.buddy.BuddyRelation;

/**
 * 好友中心逻辑
 * 
 * @author Michael(liyong@opi-corp.com)
 * 
 */
public interface IBuddyCoreAdapter extends IRegisterable {

    /**
     * 增加一个好友申请。 如果已经申请或者是被加黑名单，则什么都不做。 如果曾经是黑名单关系，则删除黑名单，并申请。
     * 如果曾经是被申请，则接受申请。
     * 
     * @param request
     */
    void addApply(BuddyApplyWithInfo request, String ref);

    /**
     * 增加一批好友申请。 如果已经申请或者是被加黑名单，则什么都不做。 如果曾经是黑名单关系，则删除黑名单，并申请。
     * 如果曾经是被申请，则接受申请。
     * 
     * @param request
     */
    void addApplyBatch(List<BuddyApplyWithInfo> requests, String ref);

    /**
     * 接受好友申请。 当且仅当曾经是申请关系，才会进行操作。 其他情况不做操作。
     * 
     * @param request
     */
    void acceptApply(BuddyApply request);

    /**
     * 接受所有好友申请。
     * 
     * @param accepter
     */
    void acceptAllApply(int accepter);

    /**
     * 拒绝好友申请。 当且仅当曾经是申请关系时，才会进行操作。 其他情况不操作。
     * 
     * @param request
     */

    void denyApply(BuddyApply request);

    /**
     * 拒绝所有好友申请。
     * 
     * @param accepter
     */
    void denyAllApply(int accepter);

    /**
     * 删除好友关系。 如果不是好友，则不操作。
     * 
     * @param relation
     */
    void removeFriend(BuddyRelation relation);

    /**
     * 增加黑名单 无论曾经是什么关系，一律删除改为黑名单。
     * 
     * @param block
     */
    void addBlock(BuddyBlock block);

    /**
     * 删除黑名单 当且仅当曾经是黑名单关系，才进行操作。
     * 
     * @param block
     */
    void removeBlock(BuddyBlock block);

    /**
     * 获取两者的关系。
     * 
     * @param fromId
     * @param toId
     * @return
     */
    BuddyRelation getRelation(int fromId, int toId);

    /**
     * 建议不要使用这个暴力的接口，祝你好运
     * 
     * @param fromId
     * @param toId
     */
    void addFriend(int fromId, int toId);

    /**
     * 建议不要使用这个暴力的接口，祝你好运
     * 
     * @param fromId
     * @param toId
     */
    void addFriendDirectly(int fromId, int toId);

    /**
     * 取host已经发送好友申请的列表（对方还没有接受的）<br>
     * limit = -1 取全部
     * 
     * @param hostId
     * @param begin
     * @param limit
     * @return
     */
    List<BuddyRelation> getApplyList(int hostId, int begin, int limit);

}
