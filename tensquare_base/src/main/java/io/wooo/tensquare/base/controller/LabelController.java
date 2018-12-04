package io.wooo.tensquare.base.controller;

import io.wooo.tensquare.base.entity.Label;
import io.wooo.tensquare.base.model.LabelSearchRequest;
import io.wooo.tensquare.base.service.LabelService;
import io.wooo.tensquare.common.entity.PageResult;
import io.wooo.tensquare.common.entity.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: wushuaiping
 * @date: 2018/11/13 7:38 PM
 * @description:
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
@AllArgsConstructor
public class LabelController {

    private LabelService labelService;

    private HttpServletRequest request;

    @PostMapping("/search")
    public Result findBySearch(@RequestBody LabelSearchRequest searchRequest) {
        List<Label> labels = labelService.findBySearch(searchRequest);
        return new Result(labels);
    }

    @PostMapping("/search/page")
    public Result findByPage(@RequestBody LabelSearchRequest searchRequest,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "20") Integer size) {
        PageResult<Label> labels = labelService.findByPage(searchRequest, page, size);
        return new Result(labels);
    }

    @GetMapping("/{id}")
    public Result<Label> findOne(@PathVariable(value = "id") String id) {
        if ("1".equals(id)) {
            throw new NullPointerException("熔断器测试");
        }
        final Label label = labelService.findById(id);
        return new Result<>(label);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(value = "id") String id) {
        labelService.delete(id);
        return new Result();
    }

    @PostMapping
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return new Result();
    }

    @PutMapping
    public Result update(@RequestBody Label label) {
        labelService.update(label);
        return new Result();
    }

    @GetMapping
    public Result<List<Label>> getAll() {
        final String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        return new Result<>(labelService.getAll());
    }


}
