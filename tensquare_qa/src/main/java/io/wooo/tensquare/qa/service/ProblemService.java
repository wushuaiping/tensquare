package io.wooo.tensquare.qa.service;

import io.wooo.tensquare.common.entity.PageResult;
import io.wooo.tensquare.qa.entity.Problem;
import io.wooo.tensquare.qa.repository.ProblemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 4:45 PM
 * @description:
 */
@Service
@AllArgsConstructor
public class ProblemService {

    private ProblemRepository problemRepository;

    public PageResult<List<Problem>> findByLabelIdAndNewlist(String labelId, Integer page, Integer size) {
        return problemRepository.findByLabelIdAndNewlist(labelId, PageRequest.of(page, size));
    }

    public PageResult<List<Problem>> findByHotlist(String labelId, Integer page, Integer size) {
        return problemRepository.findByHotlist(labelId, PageRequest.of(page, size));
    }

    public PageResult<List<Problem>> findByWaitlist(String labelId, Integer page, Integer size) {
        return problemRepository.findByWaitlist(labelId, PageRequest.of(page, size));
    }
}
