package io.wooo.tensquare.recruit.controller;

import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.recruit.service.RecruitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 3:49 PM
 * @description:
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/recruit")
public class RecruitController {

    private RecruitService recruitService;

    @GetMapping("/search/recommend")
    public Result recommend(){
        return new Result(recruitService.recommend());
    }

    @GetMapping("/search/newlist")
    public Result newlist(){
        return new Result(recruitService.newlist());
    }
}
