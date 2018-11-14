package io.wooo.tensquare.recruit.controller;

import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.recruit.service.EnterpriseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 3:37 PM
 * @description:
 */
@RestController
@RequestMapping(value = "/enterprise")
@AllArgsConstructor
public class EnterpriseController {

    private EnterpriseService enterpriseService;

    @GetMapping("/hotlist}")
    public Result getHotlist(){
        return new Result(enterpriseService.getEnterpriseByIshot("1"));
    }

}
