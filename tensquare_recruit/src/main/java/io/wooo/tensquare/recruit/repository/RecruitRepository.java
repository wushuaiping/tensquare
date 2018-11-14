package io.wooo.tensquare.recruit.repository;

import io.wooo.tensquare.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 2:13 PM
 * @description:
 */
public interface RecruitRepository extends JpaRepository<Recruit, String> {
    List<Recruit> findTop6ByStateOrderByCreatedDateDesc(String state);

    List<Recruit> findTop6ByStateNotOrderByCreatedDateDesc(String state);

}
