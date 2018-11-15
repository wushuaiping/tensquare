package io.wooo.tensquare.qa.repository;

import io.wooo.tensquare.qa.entity.Problem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 4:44 PM
 * @description:
 */
public interface ProblemRepository extends JpaRepository<Problem, String>, ProblemRepositoryCustom{
}
