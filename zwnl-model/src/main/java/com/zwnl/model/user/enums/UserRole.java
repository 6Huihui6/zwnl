package com.zwnl.model.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import com.zwnl.common.exceptions.BadRequestException;
import com.zwnl.model.constant.ErrorInfo;
import lombok.Getter;

@Getter
public enum UserRole {
    EMPLOYEE(1, "employee"),
    EMPLOYER(0, "employer"),
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
            return EMPLOYER;
        }
        if (value == 1) {
            return EMPLOYEE;
        }
        throw new BadRequestException(ErrorInfo.Msg.INVALID_USER_STATUS);
    }
}