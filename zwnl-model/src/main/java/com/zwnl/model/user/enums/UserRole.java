package com.zwnl.model.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import com.zwnl.common.exceptions.BadRequestException;
import com.zwnl.model.constant.ErrorInfo;
import lombok.Getter;

@Getter
public enum UserRole {
    FROZEN(0, "禁止使用"),
    NORMAL(1, "已激活"),
    ;
    @EnumValue
    int value;
    String desc;

    UserRole(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static UserRole of(int value) {
        if (value == 0) {
            return FROZEN;
        }
        if (value == 1) {
            return NORMAL;
        }
        throw new BadRequestException(ErrorInfo.Msg.INVALID_USER_STATUS);
    }
}