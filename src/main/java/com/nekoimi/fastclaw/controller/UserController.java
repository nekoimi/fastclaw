package com.nekoimi.fastclaw.controller;

import com.nekoimi.fastclaw.entity.User;
import com.nekoimi.fastclaw.framework.web.PageReq;
import com.nekoimi.fastclaw.framework.web.PageResult;
import com.nekoimi.fastclaw.service.UserService;
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
 * User Controller
 *
 * nekoimi  2022-04-29
 */
@RestController
@Api(tags = "账号信息", produces = "application/json", consumes = "application/json")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取账号信息分页列表
     * @param request
     * @return
     */
    @GetMapping("user/list")
    @ApiOperation(value = "获取账号信息分页列表", response = PageResult.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "分页页码", required = false, defaultValue = "1", paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, defaultValue = "10", paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "sort", value = "排序字段", required = false, defaultValue = "", paramType = "query", dataType = "String",
        allowableValues = "id,created_at,updated_at,deleted,username,password,enable"),
        @ApiImplicitParam(name = "order", value = "排序规则", required = false, defaultValue = "", paramType = "query", dataType = "String",
        allowableValues = "asc,desc")
    })
    public Mono<PageResult<User>> pageList(@ApiIgnore ServerHttpRequest request) {
        return userService.page(PageReq.buildFromRequest(request));
    }

    /**
     * 根据id获取账号信息数据
     *
     * @param id
     * @return
     */
    @GetMapping("user/{id}")
    @ApiOperation(value = "根据id获取账号信息数据", response = User.class)
    public Mono<User> get(@PathVariable("id") String id) {
        return userService.getByIdOrFail(id);
    }

    /**
     * 添加账号信息数据
     *
     * @return
     */
    @PostMapping("user")
    @ApiOperation(value = "添加账号信息数据", response = User.class)
    public Mono<Void> save(@Validated @RequestBody User body) {
        return userService.save(body).then();
    }

    /**
     * 根据id更新账号信息
     *
     * @param body
     * @return
     */
    @PutMapping("user/update")
    @ApiOperation(value = "根据id更新账号信息")
    public Mono<Void> update(@PathVariable("id") String id, @Validated @RequestBody User body) {
        return userService.updateById(id, body).then();
    }

    /**
     * 根据id更新账号信息
     *
     * @param body
     * @return
     */
    @PutMapping("user/full")
    @ApiOperation(value = "根据id更新账号信息")
    public Mono<Void> updateFull(@PathVariable("id") String id, @Validated @RequestBody User body) {
        return userService.updateById(id, body).then();
    }

    /**
     * 根据id删除账号信息数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("user/{id}")
    @ApiOperation(value = "根据id删除账号信息数据")
    public Mono<Void> remove(@PathVariable("id") String id) {
        return userService.removeById(id).then();
    }

    /**
     * 批量删除账号信息数据
     *
     * @return
     */
    @DeleteMapping("user/batch")
    @ApiOperation(value = "批量删除账号信息数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "id的列表，使用,分割", required = true, example = "1,2,3", paramType = "query", dataType = "String")
    })
    public Mono<Void> removeBatch(@RequestParam(value = "ids") String ids) {
        return userService.removeBatch(ids);
    }
}
