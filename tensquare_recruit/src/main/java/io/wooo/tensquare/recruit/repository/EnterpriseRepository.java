package io.wooo.tensquare.recruit.repository;

import io.wooo.tensquare.recruit.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 3:32 PM
 * @description:
 */
public interface EnterpriseRepository extends JpaRepository<Enterprise, String> {

    List<Enterprise> findByIshot(String ishot);
}
