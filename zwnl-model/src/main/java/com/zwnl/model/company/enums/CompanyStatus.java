package com.zwnl.model.company.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.zwnl.common.exceptions.BadRequestException;
import com.zwnl.model.constant.ErrorInfo;
import lombok.Getter;

@Getter
public enum CompanyStatus {
    ON_SHELF(1, "已上市"),
    UN_FINANCED(2, "未融资");


    @EnumValue
    private final Integer sqlValue;
    private final String description;

    CompanyStatus(int sqlValue, String description) {
        this.sqlValue = sqlValue;
        this.description = description;
    }

    public static CompanyStatus of(String sqlValue) {
        for (CompanyStatus size : CompanyStatus.values()) {
            if (size.getSqlValue().equals(sqlValue)) {
                return size;
            }
        }
        throw new BadRequestException(ErrorInfo.Msg.INVALID_COMPANY_STATUS);
    }
}