package com.nekoimi.fastclaw.controller;

import com.nekoimi.fastclaw.entity.Book;
import com.nekoimi.fastclaw.framework.web.PageReq;
import com.nekoimi.fastclaw.framework.web.PageResult;
import com.nekoimi.fastclaw.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * Book Controller
 *
 * nekoimi  2022-04-29
 */
@RestController
@Api(tags = "书籍信息", produces = "application/json", consumes = "application/json")
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 获取书籍信息分页列表
     * @param request
     * @return
     */
    @GetMapping("book/list")
    @ApiOperation(value = "获取书籍信息分页列表", response = PageResult.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "分页页码", required = false, defaultValue = "1", paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, defaultValue = "10", paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "sort", value = "排序字段", required = false, defaultValue = "", paramType = "query", dataType = "String",
        allowableValues = "id,created_at,updated_at,deleted,series_id,title,movie_number,actress_id,cover_url,cover_file_id,duration,manufacturer"),
        @ApiImplicitParam(name = "order", value = "排序规则", required = false, defaultValue = "", paramType = "query", dataType = "String",
        allowableValues = "asc,desc")
    })
    public Mono<PageResult<Book>> pageList(@ApiIgnore ServerHttpRequest request) {
        return bookService.page(PageReq.buildFromRequest(request));
    }

    /**
     * 根据id获取书籍信息数据
     *
     * @param id
     * @return
     */
    @GetMapping("book/{id}")
    @ApiOperation(value = "根据id获取书籍信息数据", response = Book.class)
    public Mono<Book> get(@PathVariable("id") String id) {
        return bookService.getByIdOrFail(id);
    }

    /**
     * 添加书籍信息数据
     *
     * @return
     */
    @PostMapping("book")
    @ApiOperation(value = "添加书籍信息数据", response = Book.class)
    public Mono<Void> save(@Validated @RequestBody Book body) {
        return bookService.save(body).then();
    }

    /**
     * 根据id更新书籍信息
     *
     * @param body
     * @return
     */
    @PutMapping("book/update")
    @ApiOperation(value = "根据id更新书籍信息")
    public Mono<Void> update(@PathVariable("id") String id, @Validated @RequestBody Book body) {
        return bookService.updateById(id, body).then();
    }

    /**
     * 根据id更新书籍信息
     *
     * @param body
     * @return
     */
    @PutMapping("book/full")
    @ApiOperation(value = "根据id更新书籍信息")
    public Mono<Void> updateFull(@PathVariable("id") String id, @Validated @RequestBody Book body) {
        return bookService.updateById(id, body).then();
    }

    /**
     * 根据id删除书籍信息数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("book/{id}")
    @ApiOperation(value = "根据id删除书籍信息数据")
    public Mono<Void> remove(@PathVariable("id") String id) {
        return bookService.removeById(id).then();
    }

    /**
     * 批量删除书籍信息数据
     *
     * @return
     */
    @DeleteMapping("book/batch")
    @ApiOperation(value = "批量删除书籍信息数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "id的列表，使用,分割", required = true, example = "1,2,3", paramType = "query", dataType = "String")
    })
    public Mono<Void> removeBatch(@RequestParam(value = "ids") String ids) {
        return bookService.removeBatch(ids);
    }
}
