package io.wooo.tensquare.gathering.repository;

import io.wooo.tensquare.gathering.entity.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wushuaiping
 * @date: 2018/11/20 3:49 PM
 * @description:
 */
public interface GatheringRepository extends JpaRepository<Gathering, String> {
}
