package com.zwnl.model.ports.dtos;

import lombok.Data;

@Data
public class BehaviorDto {

    private Long portsId;

    //1点赞/收藏，0取消
    private short operation;

    //1帖子，2评论，3视频
    private short type;
}