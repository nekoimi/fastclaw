package com.nekoimi.fastclaw.controller;

import com.nekoimi.fastclaw.entity.Image;
import com.nekoimi.fastclaw.framework.web.PageReq;
import com.nekoimi.fastclaw.framework.web.PageResult;
import com.nekoimi.fastclaw.service.ImageService;
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
 * Image Controller
 *
 * nekoimi  2022-04-29
 */
@RestController
@Api(tags = "书籍图片", produces = "application/json", consumes = "application/json")
public class ImageController {
    @Autowired
    private ImageService imageService;

    /**
     * 获取书籍图片分页列表
     * @param request
     * @return
     */
    @GetMapping("image/list")
    @ApiOperation(value = "获取书籍图片分页列表", response = PageResult.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "分页页码", required = false, defaultValue = "1", paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, defaultValue = "10", paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "sort", value = "排序字段", required = false, defaultValue = "", paramType = "query", dataType = "String",
        allowableValues = "id,created_at,updated_at,deleted,book_id,url,file_id"),
        @ApiImplicitParam(name = "order", value = "排序规则", required = false, defaultValue = "", paramType = "query", dataType = "String",
        allowableValues = "asc,desc")
    })
    public Mono<PageResult<Image>> pageList(@ApiIgnore ServerHttpRequest request) {
        return imageService.page(PageReq.buildFromRequest(request));
    }

    /**
     * 根据id获取书籍图片数据
     *
     * @param id
     * @return
     */
    @GetMapping("image/{id}")
    @ApiOperation(value = "根据id获取书籍图片数据", response = Image.class)
    public Mono<Image> get(@PathVariable("id") String id) {
        return imageService.getByIdOrFail(id);
    }

    /**
     * 添加书籍图片数据
     *
     * @return
     */
    @PostMapping("image")
    @ApiOperation(value = "添加书籍图片数据", response = Image.class)
    public Mono<Void> save(@Validated @RequestBody Image body) {
        return imageService.save(body).then();
    }

    /**
     * 根据id更新书籍图片
     *
     * @param body
     * @return
     */
    @PutMapping("image/update")
    @ApiOperation(value = "根据id更新书籍图片")
    public Mono<Void> update(@PathVariable("id") String id, @Validated @RequestBody Image body) {
        return imageService.updateById(id, body).then();
    }

    /**
     * 根据id更新书籍图片
     *
     * @param body
     * @return
     */
    @PutMapping("image/full")
    @ApiOperation(value = "根据id更新书籍图片")
    public Mono<Void> updateFull(@PathVariable("id") String id, @Validated @RequestBody Image body) {
        return imageService.updateById(id, body).then();
    }

    /**
     * 根据id删除书籍图片数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("image/{id}")
    @ApiOperation(value = "根据id删除书籍图片数据")
    public Mono<Void> remove(@PathVariable("id") String id) {
        return imageService.removeById(id).then();
    }

    /**
     * 批量删除书籍图片数据
     *
     * @return
     */
    @DeleteMapping("image/batch")
    @ApiOperation(value = "批量删除书籍图片数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "id的列表，使用,分割", required = true, example = "1,2,3", paramType = "query", dataType = "String")
    })
    public Mono<Void> removeBatch(@RequestParam(value = "ids") String ids) {
        return imageService.removeBatch(ids);
    }
}
