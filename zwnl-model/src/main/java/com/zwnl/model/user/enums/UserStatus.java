package com.zwnl.model.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.zwnl.common.exceptions.BadRequestException;
import com.zwnl.model.constant.ErrorInfo;
import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE(1, "正常"),
    INACTIVE(2, "不活跃"),
    BANNED(3, "禁用"),
    ;
    @EnumValue
    int value;
    String desc;

    UserStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static UserStatus of(int value) {
        if (value == 1) {
            return ACTIVE;
        }
        if (value == 2) {
            return INACTIVE;
        }
        if (value == 3) {
            return BANNED;
        }
        throw new BadRequestException(ErrorInfo.Msg.INVALID_USER_STATUS);
    }
}