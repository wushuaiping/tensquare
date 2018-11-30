package io.wooo.tensquare.qa.service;

import io.wooo.tensquare.common.entity.PageResult;
import io.wooo.tensquare.common.util.IdWorker;
import io.wooo.tensquare.qa.entity.Problem;
import io.wooo.tensquare.qa.repository.ProblemRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 4:45 PM
 * @description:
 */
@Service
@AllArgsConstructor
public class ProblemService {

    private ProblemRepository problemRepository;

    private IdWorker idWorker;

    public PageResult<Problem> findByLabelIdAndNewlist(String labelId, Integer page, Integer size) {
        return problemRepository.findByLabelIdAndNewlist(labelId, PageRequest.of(page, size));
    }

    public PageResult<Problem> findByHotlist(String labelId, Integer page, Integer size) {
        return problemRepository.findByHotlist(labelId, PageRequest.of(page, size));
    }

    public PageResult<Problem> findByWaitlist(String labelId, Integer page, Integer size) {
        return problemRepository.findByWaitlist(labelId, PageRequest.of(page, size));
    }

    public void save(Problem problem) {
        // 如果没有id就判定为新增，则需要初始化一些信息
        if (StringUtils.isBlank(problem.getId())) {
            problem.setId(idWorker.nextIdStringValue());
            problem.initProblem(problem);
        }
        problemRepository.save(problem);
    }
}