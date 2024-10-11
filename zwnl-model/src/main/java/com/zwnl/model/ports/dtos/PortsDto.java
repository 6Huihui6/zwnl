package com.zwnl.model.ports.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PortsDto {

    /**
     * 帖子ID
     */
    private Integer portsId;

    /**
     * 帖子类型
     */
    private String portsType;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 图片url
     */
    private String image;

    /**
     * 标题
     */
    private String title;

    /**
     * 帖子频道
     */
    private Integer channel;

    /**
     * 标签
     */
    private String labels;

    /**
     * 定时发布时间，不定时则为空
     */
    private LocalDate publishTime;

}
