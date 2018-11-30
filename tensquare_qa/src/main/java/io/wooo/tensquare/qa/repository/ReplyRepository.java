package io.wooo.tensquare.qa.repository;

import io.wooo.tensquare.qa.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 4:44 PM
 * @description:
 */
public interface ReplyRepository extends JpaRepository<Reply, String> {
}