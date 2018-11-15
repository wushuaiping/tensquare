package io.wooo.tensquare.qa.contoller;

import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.qa.service.ProblemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 4:46 PM
 * @description:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/problem")
public class ProblemController {

    private ProblemService problemService;

    @GetMapping(value = "/newlist/{labelId}")
    public Result newlist(@PathVariable(value = "labelId") String labelId,
                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "page", defaultValue = "20") Integer size) {
        return new Result(problemService.findByLabelIdAndNewlist(labelId, page - 1, size));
    }

    @GetMapping(value = "/hotlist/{labelId}")
    public Result findByHotlist(@PathVariable(value = "labelId") String labelId,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "page", defaultValue = "20") Integer size) {
        return new Result(problemService.findByHotlist(labelId, page - 1, size));
    }

    @GetMapping(value = "/waitlist/{labelId}")
    public Result findByWaitlist(@PathVariable(value = "labelId") String labelId,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "page", defaultValue = "20") Integer size) {
        return new Result(problemService.findByWaitlist(labelId, page - 1, size));
    }
}
