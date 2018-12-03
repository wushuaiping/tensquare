package io.wooo.tensquare.friend.repository;

import io.wooo.tensquare.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: wushuaiping
 * @date: 2018/11/30 4:59 PM
 * @description:
 */
public interface FriendRepository extends JpaRepository<Friend, String>, CrudRepository<Friend, String>, FriendRepositoryCustom {

    /**
     * 查某个用户是否关注了另一个用户
     *
     * @param userId
     * @param targetUserId
     * @return
     */
    Friend findByUseridAndFriendid(String userId, String targetUserId);

}
