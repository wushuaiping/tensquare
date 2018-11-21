package io.wooo.tensquare.spit.controller;

import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.spit.document.Spit;
import io.wooo.tensquare.spit.mapper.SpitMapper;
import io.wooo.tensquare.spit.model.SpitModel;
import io.wooo.tensquare.spit.service.SpitService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wushuaiping
 * @date: 2018/11/21 2:09 PM
 * @description:
 */
@RestController
@RequestMapping("/spit")
@AllArgsConstructor
public class SpitController {

    private SpitService spitService;

    private RedisTemplate redisTemplate;

    @GetMapping
    public Result getAll() {
        return new Result(spitService.getAll());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") String id) {
        return new Result(spitService.getById(id));
    }

    @PutMapping
    public Result update(@RequestBody SpitModel spit) {
        if (spit == null || StringUtils.isBlank(spit.get_id())) {
            return new Result(false, HttpStatus.BAD_REQUEST.value(), "修改的数据不能为空");
        }
        final Spit spitEntity = SpitMapper.MAPPER.toEntity(spit);
        spitService.save(spitEntity);
        return new Result();
    }

    @PostMapping
    public Result save(@RequestBody Spit spit) {
        spitService.save(spit);
        return new Result();
    }

    @DeleteMapping("/{id}")
    public Result deleted(@PathVariable("id") String id) {
        spitService.deleted(id);
        return new Result();
    }

    @GetMapping("/{parentid}/{page}/{size}")
    public Result getByParentId(@PathVariable("parentid") String parentid,
                                @PathVariable("page") Integer page,
                                @PathVariable("size") Integer size) {
        final Pageable pageable = PageRequest.of(page - 1, size);
        return new Result(spitService.getByParentId(parentid, pageable));
    }

    @PutMapping("/thumup/{id}")
    public Result thumup(@PathVariable("id") String id) {
        String userid = "123";
        String key = "thumup_";
        if (redisTemplate.opsForValue().get( key + userid) != null){
            return new Result(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "不能重复点赞哦~(╯﹏╰)");
        }else {
            redisTemplate.opsForValue().set(key + userid, "这小伙子点过赞了");
        }
        return new Result(spitService.thumup(id));
    }
}
