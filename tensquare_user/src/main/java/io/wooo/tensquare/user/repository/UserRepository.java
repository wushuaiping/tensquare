package io.wooo.tensquare.user.repository;

import io.wooo.tensquare.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wushuaiping
 * @date: 2018/11/28 3:24 PM
 * @description:
 */
public interface UserRepository extends JpaRepository<User, String> {

    User findByMobile(String mobile);

}
