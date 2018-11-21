package io.wooo.tensquare.gathering.controller;

import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.gathering.entity.Gathering;
import io.wooo.tensquare.gathering.service.GatheringService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wushuaiping
 * @date: 2018/11/20 3:48 PM
 * @description:
 */
@RestController
@RequestMapping("/gathering")
@AllArgsConstructor
public class GatheringController {

    private GatheringService gatheringService;

    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") String id) {
        return new Result(gatheringService.getByOne(id));
    }

    @GetMapping
    public Result getAll() {
        return new Result(gatheringService.getAll());
    }

    @PostMapping
    public Result save(@RequestBody Gathering gathering) {
        gatheringService.save(gathering);
        return new Result();
    }

    @PutMapping
    public Result update(@RequestBody Gathering gathering) {
        gatheringService.save(gathering);
        return new Result();
    }

    @DeleteMapping("/{id}")
    public Result deleted(@PathVariable String id) {
        gatheringService.deleted(id);
        return new Result();
    }
}
