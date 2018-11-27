package io.wooo.tensquare.search.controller;

import io.wooo.tensquare.common.entity.PageResult;
import io.wooo.tensquare.common.entity.Result;
import io.wooo.tensquare.search.entity.Article;
import io.wooo.tensquare.search.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wushuaiping
 * @date: 2018/11/27 2:03 PM
 * @description:
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/article")
public class ArticleController {

    private ArticleService articleService;

    @GetMapping("/page")
    public Result getArticleByPage(@RequestParam(value = "keyword", required = false) String keyword,
                                   @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        final PageResult<Article> articleByPage = articleService.getArticleByPage(keyword, PageRequest.of(page - 1, size));
        return new Result(articleByPage);
    }

    @PostMapping
    public Result save(@RequestBody Article article) {
        articleService.save(article);
        return new Result();
    }

    @PutMapping
    public Result update(@RequestBody Article article) {
        articleService.save(article);
        return new Result();
    }

    @GetMapping
    public Result getAll() {
        return new Result(articleService.findAll());
    }

    @DeleteMapping
    public Result deleted(@RequestParam(value = "id") String id) {
        articleService.deleted(id);
        return new Result();
    }

    @PutMapping("/{id}/{state}")
    public Result updateState(@PathVariable(value = "id") String id,
                              @PathVariable(value = "state") String state) {
        articleService.updateState(id, state);
        return new Result();
    }
}
