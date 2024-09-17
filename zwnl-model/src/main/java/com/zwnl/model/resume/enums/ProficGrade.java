package com.zwnl.model.resume.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.zwnl.common.exceptions.BadRequestException;
import com.zwnl.model.constant.ErrorInfo;
import lombok.Getter;

@Getter
public enum ProficGrade {
    beginner(1, "入门"),
    intermediate(2, "熟练"),
    expert(3, "精通"),
    ;
    @EnumValue
    int value;
    String desc;

    ProficGrade(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static ProficGrade of(int value) {
        if (value == 1) {
            return beginner;
        }
        if (value == 2) {
            return intermediate;
        }
        if (value == 3) {
            return expert;
        }
        throw new BadRequestException(ErrorInfo.Msg.INVALID_USER_STATUS);
    }
}