package com.nekoimi.vasashi.controller;

import com.nekoimi.vasashi.entity.UserInfo;
import com.nekoimi.vasashi.framework.web.PageReq;
import com.nekoimi.vasashi.framework.web.PageResult;
import com.nekoimi.vasashi.service.UserInfoService;
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
 * UserInfo Controller
 *
 * nekoimi  2022-04-29
 */
@RestController
@Api(tags = "用户信息", produces = "application/json", consumes = "application/json")
public class UserInfoController {
    @Autowired
    private UserInfoService userinfoService;

    /**
     * 获取用户信息分页列表
     * @param request
     * @return
     */
    @GetMapping("userInfo/list")
    @ApiOperation(value = "获取用户信息分页列表", response = PageResult.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "分页页码", required = false, defaultValue = "1", paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, defaultValue = "10", paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "sort", value = "排序字段", required = false, defaultValue = "", paramType = "query", dataType = "String",
        allowableValues = "id,created_at,updated_at,deleted,user_id,nickname,avatar"),
        @ApiImplicitParam(name = "order", value = "排序规则", required = false, defaultValue = "", paramType = "query", dataType = "String",
        allowableValues = "asc,desc")
    })
    public Mono<PageResult<UserInfo>> pageList(@ApiIgnore ServerHttpRequest request) {
        return userinfoService.page(PageReq.buildFromRequest(request));
    }

    /**
     * 根据id获取用户信息数据
     *
     * @param id
     * @return
     */
    @GetMapping("userInfo/{id}")
    @ApiOperation(value = "根据id获取用户信息数据", response = UserInfo.class)
    public Mono<UserInfo> get(@PathVariable("id") String id) {
        return userinfoService.getByIdOrFail(id);
    }

    /**
     * 添加用户信息数据
     *
     * @return
     */
    @PostMapping("userInfo")
    @ApiOperation(value = "添加用户信息数据", response = UserInfo.class)
    public Mono<Void> save(@Validated @RequestBody UserInfo body) {
        return userinfoService.save(body).then();
    }

    /**
     * 根据id更新用户信息
     *
     * @param body
     * @return
     */
    @PutMapping("userInfo/update")
    @ApiOperation(value = "根据id更新用户信息")
    public Mono<Void> update(@PathVariable("id") String id, @Validated @RequestBody UserInfo body) {
        return userinfoService.updateById(id, body).then();
    }

    /**
     * 根据id更新用户信息
     *
     * @param body
     * @return
     */
    @PutMapping("userInfo/full")
    @ApiOperation(value = "根据id更新用户信息")
    public Mono<Void> updateFull(@PathVariable("id") String id, @Validated @RequestBody UserInfo body) {
        return userinfoService.updateById(id, body).then();
    }

    /**
     * 根据id删除用户信息数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("userInfo/{id}")
    @ApiOperation(value = "根据id删除用户信息数据")
    public Mono<Void> remove(@PathVariable("id") String id) {
        return userinfoService.removeById(id).then();
    }

    /**
     * 批量删除用户信息数据
     *
     * @return
     */
    @DeleteMapping("userInfo/batch")
    @ApiOperation(value = "批量删除用户信息数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "id的列表，使用,分割", required = true, example = "1,2,3", paramType = "query", dataType = "String")
    })
    public Mono<Void> removeBatch(@RequestParam(value = "ids") String ids) {
        return userinfoService.removeBatch(ids);
    }
}
