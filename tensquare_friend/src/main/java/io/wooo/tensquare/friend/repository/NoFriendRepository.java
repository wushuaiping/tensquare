package io.wooo.tensquare.friend.repository;

import io.wooo.tensquare.friend.entity.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wushuaiping
 * @date: 2018/11/30 5:00 PM
 * @description:
 */
public interface NoFriendRepository extends JpaRepository<NoFriend, String> {

    NoFriend findByUseridAndFriendid(String userid, String friendid);
}
