package com.zwnl.model.resume.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.zwnl.common.exceptions.BadRequestException;
import com.zwnl.model.constant.ErrorInfo;
import lombok.Getter;

@Getter
public enum CompanySize {
    SIZE_1_50(1, "1-50人"),
    SIZE_51_200(2, "51-200人"),
    SIZE_201_500(3, "201-500人"),
    SIZE_501_1000(4, "501-1000人"),
    SIZE_1001_PLUS(5, "1001人以上");

    @EnumValue
    private final Integer sqlValue;
    private final String description;

    CompanySize(int sqlValue, String description) {
        this.sqlValue = sqlValue;
        this.description = description;
    }

    public static CompanySize of(String sqlValue) {
        for (CompanySize size : CompanySize.values()) {
            if (size.getSqlValue().equals(sqlValue)) {
                return size;
            }
        }
        throw new BadRequestException(ErrorInfo.Msg.INVALID_COMPANY_SIZE);
    }
}