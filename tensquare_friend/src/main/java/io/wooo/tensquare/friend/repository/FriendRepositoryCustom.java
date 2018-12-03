package io.wooo.tensquare.friend.repository;

/**
 * @author: wushuaiping
 * @date: 2018/11/30 5:52 PM
 * @description:
 */
public interface FriendRepositoryCustom {

    /**
     * 查粉丝数
     *
     * @param userid
     * @return
     */
    Integer findFansCountByUserid(String userid);

    /**
     * 查关注数
     *
     * @param friendid
     * @return
     */
    Integer findFollowCountByUserid(String friendid);

}
