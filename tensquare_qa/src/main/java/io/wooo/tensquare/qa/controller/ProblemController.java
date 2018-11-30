package io.wooo.tensquare.qa.controller;

import io.wooo.tensquare.common.entity.PageResult;
import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.qa.client.BaseClient;
import io.wooo.tensquare.qa.config.model.LoginUser;
import io.wooo.tensquare.qa.entity.Problem;
import io.wooo.tensquare.qa.enums.IdentityEnum;
import io.wooo.tensquare.qa.service.ProblemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 4:46 PM
 * @description:
 */
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/problem", produces = { "application/json;charset=UTF-8" })
public class ProblemController {

    private ProblemService problemService;

    private HttpServletRequest request;

    private BaseClient baseClient;

    @GetMapping(value = "/label/{labelId}")
    public Result finByLabelId(@PathVariable(value = "labelId") String labelId) {
        return baseClient.findOne(labelId);
    }

    @GetMapping(value = "/newlist/{labelId}")
    public Result<PageResult<Problem>> newlist(@PathVariable(value = "labelId") String labelId,
                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "page", defaultValue = "20") Integer size) {
        return new Result<>(problemService.findByLabelIdAndNewlist(labelId, page - 1, size));
    }

    @GetMapping(value = "/hotlist/{labelId}")
    public Result<PageResult<Problem>> findByHotlist(@PathVariable(value = "labelId") String labelId,
                                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                     @RequestParam(value = "page", defaultValue = "20") Integer size) {
        return new Result<>(problemService.findByHotlist(labelId, page - 1, size));
    }

    @GetMapping(value = "/waitlist/{labelId}")
    public Result<PageResult<Problem>> findByWaitlist(@PathVariable(value = "labelId") String labelId,
                                                      @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                      @RequestParam(value = "page", defaultValue = "20") Integer size) {
        return new Result<>(problemService.findByWaitlist(labelId, page - 1, size));
    }

    @PutMapping
    public Result update(@RequestBody Problem problem) {
        problemService.save(problem);
        return new Result();
    }

    @PostMapping
    public Result save(@RequestBody Problem problem) {
        final LoginUser loginUser = (LoginUser) request.getAttribute(IdentityEnum.CLAIMS_USER.getDes());
        if (loginUser == null || loginUser.getIdentify() != IdentityEnum.CLAIMS_USER) {
            return new Result(false, HttpStatus.UNAUTHORIZED.value(), "invalid_token");
        }
        problemService.save(problem);
        return new Result();
    }

}
