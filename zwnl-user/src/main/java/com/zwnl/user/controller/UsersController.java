package com.zwnl.user.controller;

//
//import com.zwnl.user.service.IUsersService;

import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.common.utils.AppJwtUtil;
import com.zwnl.model.user.dtos.UserDTO;
import com.zwnl.model.user.dtos.UserLoginDTO;
import com.zwnl.model.user.pos.Users;
import com.zwnl.model.user.vos.UserDetailVO;
import com.zwnl.model.user.vos.UserLoginVO;
import com.zwnl.user.constant.JwtClaimsConstant;
import com.zwnl.user.service.IUserDetailService;
import com.zwnl.user.service.IUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
@Api(tags = "用户管理接口")
@Slf4j
public class UsersController {

    private  final IUsersService usersService;
    private  final IUserDetailService userDetailService;


@PostMapping("/login")
@ApiOperation("微信登录")
public ResponseResult login(@RequestBody UserLoginDTO userLoginDTO){
    log.info("微信用户登录：{}",userLoginDTO.getCode());

    //微信登录
    Users user = usersService.wxLogin(userLoginDTO);

    //为微信用户生成jwt令牌
//    Map<String, Object> claims = new HashMap<>();
//    claims.put(JwtClaimsConstant.USER_ID,user.getUserId());
    String token = AppJwtUtil.getToken(Long.valueOf(JwtClaimsConstant.USER_ID));

    UserLoginVO userLoginVO = UserLoginVO.builder()
            .id(Long.valueOf(user.getUserId()))
            .openid(user.getOpenid())
            .token(token)
            .build();
    return ResponseResult.okResult(userLoginVO);
}



@PostMapping("/register")
@ApiOperation("用户注册")
public ResponseResult register(@RequestBody Users users) {
    log.info("用户注册：{}", users);
    usersService.register(users);
    return ResponseResult.okResult("注册成功");
}

    @ApiOperation("获取当前登录用户信息")
    @GetMapping( "/me")
    public UserDetailVO me() {
        return usersService.myInfo();
    }
//    @ApiOperation("更新用户信息")
//    @PutMapping("/{id}")
//    public void updateUser(@RequestBody @Valid UserDTO userDTO){
//        usersService.updateUser(userDTO);
//    }
}
