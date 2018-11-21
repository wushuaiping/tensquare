package io.wooo.tensquare.article.controller;

import io.wooo.tensquare.article.entity.Article;
import io.wooo.tensquare.article.service.ArticleService;
import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.common.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wushuaiping
 * @date: 2018/11/15 10:50 AM
 * @description:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;

    @PutMapping("/approve/pass/{id}")
    public Result approvePass(@PathVariable("id") String id) {
        articleService.approvePass(id);
        return new Result();
    }

    @PutMapping("/thumbup/{id}")
    public Result thumbup(@PathVariable("id") String id) {
        articleService.thumbup(id);
        return new Result();
    }

    @GetMapping
    public Result getAll() {
        return new Result(articleService.getAll());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") String id) {
        return new Result(articleService.getById(id));
    }

    @PutMapping
    public Result update(@RequestBody Article article) {
        if (article == null || StringUtils.isBlank(article.getId())) {
            throw new BadRequestException("您没有可修改的文章");
        }
        articleService.save(article);
        return new Result();
    }

    @PostMapping
    public Result save(@RequestBody Article article) {
        articleService.save(article);
        return new Result();
    }

    @DeleteMapping("/{id}")
    public Result deleted(@PathVariable("id") String id){
        articleService.deleted(id);
        return new Result();
    }
}