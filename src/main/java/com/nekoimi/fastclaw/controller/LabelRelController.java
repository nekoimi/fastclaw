package com.nekoimi.fastclaw.controller;

import com.nekoimi.fastclaw.entity.LabelRel;
import com.nekoimi.fastclaw.framework.web.PageReq;
import com.nekoimi.fastclaw.framework.web.PageResult;
import com.nekoimi.fastclaw.service.LabelRelService;
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
 * LabelRel Controller
 *
 * nekoimi  2022-04-29
 */
@RestController
@Api(tags = "书籍标签Rel", produces = "application/json", consumes = "application/json")
public class LabelRelController {
    @Autowired
    private LabelRelService labelrelService;

    /**
     * 获取书籍标签Rel分页列表
     * @param request
     * @return
     */
    @GetMapping("labelRel/list")
    @ApiOperation(value = "获取书籍标签Rel分页列表", response = PageResult.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "分页页码", required = false, defaultValue = "1", paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, defaultValue = "10", paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "sort", value = "排序字段", required = false, defaultValue = "", paramType = "query", dataType = "String",
        allowableValues = "id,created_at,updated_at,deleted,book_id,label_id"),
        @ApiImplicitParam(name = "order", value = "排序规则", required = false, defaultValue = "", paramType = "query", dataType = "String",
        allowableValues = "asc,desc")
    })
    public Mono<PageResult<LabelRel>> pageList(@ApiIgnore ServerHttpRequest request) {
        return labelrelService.page(PageReq.buildFromRequest(request));
    }

    /**
     * 根据id获取书籍标签Rel数据
     *
     * @param id
     * @return
     */
    @GetMapping("labelRel/{id}")
    @ApiOperation(value = "根据id获取书籍标签Rel数据", response = LabelRel.class)
    public Mono<LabelRel> get(@PathVariable("id") String id) {
        return labelrelService.getByIdOrFail(id);
    }

    /**
     * 添加书籍标签Rel数据
     *
     * @return
     */
    @PostMapping("labelRel")
    @ApiOperation(value = "添加书籍标签Rel数据", response = LabelRel.class)
    public Mono<Void> save(@Validated @RequestBody LabelRel body) {
        return labelrelService.save(body).then();
    }

    /**
     * 根据id更新书籍标签Rel
     *
     * @param body
     * @return
     */
    @PutMapping("labelRel/update")
    @ApiOperation(value = "根据id更新书籍标签Rel")
    public Mono<Void> update(@PathVariable("id") String id, @Validated @RequestBody LabelRel body) {
        return labelrelService.updateById(id, body).then();
    }

    /**
     * 根据id更新书籍标签Rel
     *
     * @param body
     * @return
     */
    @PutMapping("labelRel/full")
    @ApiOperation(value = "根据id更新书籍标签Rel")
    public Mono<Void> updateFull(@PathVariable("id") String id, @Validated @RequestBody LabelRel body) {
        return labelrelService.updateById(id, body).then();
    }

    /**
     * 根据id删除书籍标签Rel数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("labelRel/{id}")
    @ApiOperation(value = "根据id删除书籍标签Rel数据")
    public Mono<Void> remove(@PathVariable("id") String id) {
        return labelrelService.removeById(id).then();
    }

    /**
     * 批量删除书籍标签Rel数据
     *
     * @return
     */
    @DeleteMapping("labelRel/batch")
    @ApiOperation(value = "批量删除书籍标签Rel数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "id的列表，使用,分割", required = true, example = "1,2,3", paramType = "query", dataType = "String")
    })
    public Mono<Void> removeBatch(@RequestParam(value = "ids") String ids) {
        return labelrelService.removeBatch(ids);
    }
}
