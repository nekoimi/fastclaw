package com.nekoimi.vasashi.controller;

import com.nekoimi.vasashi.entity.Magnet;
import com.nekoimi.vasashi.framework.web.PageReq;
import com.nekoimi.vasashi.framework.web.PageResult;
import com.nekoimi.vasashi.service.MagnetService;
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
 * Magnet Controller
 *
 * nekoimi  2022-04-29
 */
@RestController
@Api(tags = "书籍磁力链接", produces = "application/json", consumes = "application/json")
public class MagnetController {
    @Autowired
    private MagnetService magnetService;

    /**
     * 获取书籍磁力链接分页列表
     * @param request
     * @return
     */
    @GetMapping("magnet/list")
    @ApiOperation(value = "获取书籍磁力链接分页列表", response = PageResult.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "分页页码", required = false, defaultValue = "1", paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, defaultValue = "10", paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "sort", value = "排序字段", required = false, defaultValue = "", paramType = "query", dataType = "String",
        allowableValues = "id,created_at,updated_at,deleted,book_id,link"),
        @ApiImplicitParam(name = "order", value = "排序规则", required = false, defaultValue = "", paramType = "query", dataType = "String",
        allowableValues = "asc,desc")
    })
    public Mono<PageResult<Magnet>> pageList(@ApiIgnore ServerHttpRequest request) {
        return magnetService.page(PageReq.buildFromRequest(request));
    }

    /**
     * 根据id获取书籍磁力链接数据
     *
     * @param id
     * @return
     */
    @GetMapping("magnet/{id}")
    @ApiOperation(value = "根据id获取书籍磁力链接数据", response = Magnet.class)
    public Mono<Magnet> get(@PathVariable("id") String id) {
        return magnetService.getByIdOrFail(id);
    }

    /**
     * 添加书籍磁力链接数据
     *
     * @return
     */
    @PostMapping("magnet")
    @ApiOperation(value = "添加书籍磁力链接数据", response = Magnet.class)
    public Mono<Void> save(@Validated @RequestBody Magnet body) {
        return magnetService.save(body).then();
    }

    /**
     * 根据id更新书籍磁力链接
     *
     * @param body
     * @return
     */
    @PutMapping("magnet/update")
    @ApiOperation(value = "根据id更新书籍磁力链接")
    public Mono<Void> update(@PathVariable("id") String id, @Validated @RequestBody Magnet body) {
        return magnetService.updateById(id, body).then();
    }

    /**
     * 根据id更新书籍磁力链接
     *
     * @param body
     * @return
     */
    @PutMapping("magnet/full")
    @ApiOperation(value = "根据id更新书籍磁力链接")
    public Mono<Void> updateFull(@PathVariable("id") String id, @Validated @RequestBody Magnet body) {
        return magnetService.updateById(id, body).then();
    }

    /**
     * 根据id删除书籍磁力链接数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("magnet/{id}")
    @ApiOperation(value = "根据id删除书籍磁力链接数据")
    public Mono<Void> remove(@PathVariable("id") String id) {
        return magnetService.removeById(id).then();
    }

    /**
     * 批量删除书籍磁力链接数据
     *
     * @return
     */
    @DeleteMapping("magnet/batch")
    @ApiOperation(value = "批量删除书籍磁力链接数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "id的列表，使用,分割", required = true, example = "1,2,3", paramType = "query", dataType = "String")
    })
    public Mono<Void> removeBatch(@RequestParam(value = "ids") String ids) {
        return magnetService.removeBatch(ids);
    }
}
