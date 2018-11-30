package io.wooo.tensquare.qa.repository;

import io.wooo.tensquare.common.entity.PageResult;
import io.wooo.tensquare.qa.entity.Problem;
import org.springframework.data.domain.Pageable;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 4:55 PM
 * @description:
 */
public interface ProblemRepositoryCustom {

    /**
     * 查询最新的问题列表
     *
     * @param labelId
     * @param pageable
     * @return
     */
    PageResult<Problem> findByLabelIdAndNewlist(String labelId, Pageable pageable);

    /**
     * 热门列表
     *
     * @param labelId
     * @param pageable
     * @return
     */
    PageResult<Problem> findByHotlist(String labelId, Pageable pageable);

    /**
     * 等待回答列表
     *
     * @param labelId
     * @param pageable
     * @return
     */
    PageResult<Problem> findByWaitlist(String labelId, Pageable pageable);

}