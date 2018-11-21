package io.wooo.tensquare.spit.repository;

import io.wooo.tensquare.spit.document.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: wushuaiping
 * @date: 2018/11/21 1:50 PM
 * @description:
 */
public interface SpitRepository extends MongoRepository<Spit, String> {

    Page<Spit> findByParentid(String parentid, Pageable pageable);
}
