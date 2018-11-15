package io.woo.tensquare.article.controller;

import io.woo.tensquare.article.service.ArticleService;
import io.wooo.tensquare.common.entity.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}