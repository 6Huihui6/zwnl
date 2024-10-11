package com.zwnl.model.ports.dtos;

import com.zwnl.common.domain.dto.PageRequestDto;
import lombok.Data;

@Data
public class PageDto extends PageRequestDto {
    private Integer channel;

    // 排序 1:时间 2:热度
    private Integer order = 2;
}
