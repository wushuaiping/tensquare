package io.wooo.tensquare.friend.service;

import io.wooo.tensquare.friend.client.UserClient;
import io.wooo.tensquare.friend.client.model.UpdateUserClientModel;
import io.wooo.tensquare.friend.config.model.LoginUser;
import io.wooo.tensquare.friend.entity.Friend;
import io.wooo.tensquare.friend.entity.NoFriend;
import io.wooo.tensquare.friend.enums.LikeEnum;
import io.wooo.tensquare.friend.repository.FriendRepository;
import io.wooo.tensquare.friend.repository.NoFriendRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: wushuaiping
 * @date: 2018/11/30 5:12 PM
 * @description:
 */
@Service
@AllArgsConstructor
@Transactional
public class FriendService {

    private FriendRepository friendRepository;

    private UserClient userClient;

    private NoFriendRepository noFriendRepository;

    /**
     * 关注
     *
     * @param loginUserId
     * @param friendid
     */
    public void likeOrDontLike(String loginUserId, String friendid) {
        final Friend friend = new Friend(loginUserId, friendid, "0");
        // 查被关注用户的粉丝数
        final Integer fansCount = friendRepository.findFansCountByUserid(friendid);
        // 查询执行关注操作的用户的关注数
        final Integer followCount = friendRepository.findFollowCountByUserid(loginUserId);
        // 修改被关注用户的粉丝数，和执行关注操作用户的关注数
        UpdateUserClientModel clientModel = new UpdateUserClientModel();
        clientModel.setFansCount(fansCount);
        clientModel.setFollowCount(followCount);
        clientModel.setLoginUserId(loginUserId);
        clientModel.setTargetUserId(friendid);
        userClient.updateUserCount(clientModel);
        friendRepository.save(friend);
    }

    /**
     * 不喜欢
     *
     * @param friendid
     * @param loginUser
     */
    public void dontLike(String friendid, LoginUser loginUser) {

//        // 查询被关注者与登录用户是不是互相喜欢状态
//        final Friend follow = friendRepository.findByUseridAndFriendid(friendid, loginUser.getId());
//
//        // 如果是相互喜欢
//        if (follow != null) {
//            // 更改相互喜欢的状态为单向喜欢
//            follow.setIslike(LikeEnum.LIKE.getDesc());
//            friendRepository.save(follow);
//        }
//
//        // 删除当前登录用户喜欢friendid的数据
//        final Friend friend = friendRepository.findByUseridAndFriendid(loginUser.getId(), friendid);
//        if (friend != null) {
//            friendRepository.delete(friend);
//        }

        // 往非好友表添加数据
        final NoFriend noFriend = new NoFriend(loginUser.getId(), friendid);
        final NoFriend isNoFriend = noFriendRepository.findByUseridAndFriendid(loginUser.getId(), friendid);
        // 没有添加过非好友表才继续添加
        if (isNoFriend != null) {
            noFriendRepository.save(noFriend);
        }

//        // 修改两个用户的粉丝数和关注数
//        updateFansCountAndFollowCount(friendid, loginUser);
    }

    /**
     * 喜欢
     *
     * @param friendid
     * @param loginUser
     */
    public void like(String friendid, LoginUser loginUser) {

        // 先查找friendid 是否也喜欢了当前登录用户，如果是的话。就是互相喜欢
        final Friend follow = friendRepository.findByUseridAndFriendid(friendid, loginUser.getId());
        String islike = LikeEnum.LIKE.getDesc();
        if (follow != null) {
            islike = LikeEnum.RECIPROCAL_LIKING.getDesc();
            // 如果是互相喜欢，那么被关注者的喜欢状态也要改为互相喜欢
            follow.setIslike(islike);
            friendRepository.save(follow);
        }

        // 保存当前登录用户的关注数据
        final Friend friend = new Friend(loginUser.getId(), friendid, islike);
        friendRepository.save(friend);

        // 修改两个用户的粉丝数和关注数
        updateFansCountAndFollowCount(friendid, loginUser);
    }

    /**
     * 修改粉丝数和关注数
     *
     * @param friendid
     * @param loginUser
     */
    private void updateFansCountAndFollowCount(String friendid, LoginUser loginUser) {
        // 查被关注用户的粉丝数
        final Integer fansCount = friendRepository.findFansCountByUserid(friendid);

        // 查询执行关注操作的用户的关注数
        final Integer followCount = friendRepository.findFollowCountByUserid(loginUser.getId());

        // 修改被关注用户的粉丝数，和执行关注操作用户的关注数
        UpdateUserClientModel clientModel = new UpdateUserClientModel();
        clientModel.setFansCount(fansCount);
        clientModel.setFollowCount(followCount);
        clientModel.setLoginUserId(loginUser.getId());
        clientModel.setTargetUserId(friendid);
        userClient.updateUserCount(clientModel);
    }

    public void deleteFriend(String friendid, LoginUser loginUser) {
        // 查询被关注者与登录用户是不是互相喜欢状态
        final Friend follow = friendRepository.findByUseridAndFriendid(friendid, loginUser.getId());

        // 如果是相互喜欢
        if (follow != null) {
            // 更改相互喜欢的状态为单向喜欢
            follow.setIslike(LikeEnum.LIKE.getDesc());
            friendRepository.save(follow);
        }

        // 删除当前登录用户喜欢friendid的数据
        final Friend friend = friendRepository.findByUseridAndFriendid(loginUser.getId(), friendid);
        if (friend != null) {
            friendRepository.delete(friend);
        }

        // 在非好友表中添加数据
        NoFriend noFriend = noFriendRepository.findByUseridAndFriendid(loginUser.getId(), friendid);
        if (noFriend == null) {
            noFriend = new NoFriend(loginUser.getId(), friendid);
            noFriendRepository.save(noFriend);
        }

        // 修改两个用户的粉丝数和关注数
        updateFansCountAndFollowCount(friendid, loginUser);
    }
}
