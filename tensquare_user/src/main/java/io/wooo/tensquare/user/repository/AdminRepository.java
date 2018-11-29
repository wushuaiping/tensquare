package io.wooo.tensquare.user.repository;

import io.wooo.tensquare.user.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wushuaiping
 * @date: 2018/11/28 3:24 PM
 * @description:
 */
public interface AdminRepository extends JpaRepository<Admin, String> {

    Page<Admin> findByUsernameLike(String keyword, Pageable pageable);

    Admin findByMobile(String mobile);
}
