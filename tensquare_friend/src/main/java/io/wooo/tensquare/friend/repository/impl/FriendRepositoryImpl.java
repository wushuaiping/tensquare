package io.wooo.tensquare.friend.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.wooo.tensquare.friend.entity.QFriend;
import io.wooo.tensquare.friend.repository.FriendRepository;
import io.wooo.tensquare.friend.repository.FriendRepositoryCustom;
import lombok.AllArgsConstructor;

/**
 * @author: wushuaiping
 * @date: 2018/11/30 5:54 PM
 * @description:
 */
@AllArgsConstructor
public class FriendRepositoryImpl implements FriendRepositoryCustom {

    private JPAQueryFactory jpaQueryFactory;

    @Override
    public Integer findFansCountByUserid(String userid) {
        final QFriend friend = QFriend.friend;
        int count = jpaQueryFactory.select(friend.friendid.count()).from(friend).where(friend.userid.eq(userid)).fetchOne().intValue();
        return count;
    }

    @Override
    public Integer findFollowCountByUserid(String userid) {
        final QFriend friend = QFriend.friend;
        int count = jpaQueryFactory.select(friend.userid.count()).from(friend).where(friend.friendid.eq(userid)).fetchOne().intValue();
        return count;
    }
}
