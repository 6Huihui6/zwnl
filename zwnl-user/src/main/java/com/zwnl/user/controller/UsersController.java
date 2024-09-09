package com.zwnl.user.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hui
 * @since 2024-09-09
 */
@RestController
@RequestMapping("/users")
@Tag(name = "用户管理接口")
public class UsersController {

    @Operation(summary = "普通body请求")
    @PostMapping
    public String post() {
        return "success";
    }

}
