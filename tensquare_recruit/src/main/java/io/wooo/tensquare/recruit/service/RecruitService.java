package io.wooo.tensquare.recruit.service;

import io.wooo.tensquare.recruit.entity.Recruit;
import io.wooo.tensquare.recruit.repository.RecruitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 3:44 PM
 * @description:
 */
@Service
@AllArgsConstructor
public class RecruitService {

    private RecruitRepository recruitRepository;

    public List<Recruit> recommend() {
        return recruitRepository.findTop6ByStateOrderByCreatedDateDesc("2");
    }

    public List<Recruit> newlist() {
        return recruitRepository.findTop6ByStateNotOrderByCreatedDateDesc("0");
    }
}
