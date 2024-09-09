package com.zwnl.user.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hui
 * @since 2024-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zwnl_user")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;


    private String username;


    private String email;


    private String password;


    private String role;


    private String avatarUrl;


    private String status;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;


    private LocalDateTime lastLogin;


}
