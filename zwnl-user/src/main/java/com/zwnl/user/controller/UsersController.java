package com.zwnl.user.controller;

//
//import com.zwnl.user.service.IUsersService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequiredArgsConstructor
@RequestMapping("/users")
//@Tag(name = "用户管理接口")
public class UsersController {

//    private  final IUsersService usersService;

    @ApiOperation( "普通body请求")
    @GetMapping
    public String post() {
//        return usersService.post();
        return "post";
    }

}
