package com.zwnl.model.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import com.zwnl.common.constants.ErrorInfo;
import com.zwnl.common.enums.BaseEnum;
import com.zwnl.common.exceptions.BadRequestException;
import lombok.Getter;

@Getter
public enum UserType implements BaseEnum {
    STAFF(1, "其他员工"),
    EMPLOYEE(2, "零工"),
    EMPLOYER(3, "雇主"),
    ;
    @EnumValue
    int value;
    String desc;

    UserType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static UserType of(int value) {
        for (UserType type : UserType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new BadRequestException(ErrorInfo.Msg.INVALID_USER_TYPE);
    }
}
