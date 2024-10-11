package com.zwnl.model.ports.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

/**
 * <p>
 * 帖子表
 * </p>
 *
 * @author hrtslz
 * @since 2024-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ports")
public class Ports implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子ID
     */
    @TableId(value = "ports_id", type = IdType.AUTO)
    private Integer portsId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 帖子类型
     */
    private String portsType;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 发帖时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 点赞数
     */
    private Integer like;

    /**
     * 是否删除
     */
    private Integer isDeleted;

    /**
     * 收藏数
     */
    private Integer collection;

    /**
     * 转发数
     */
    private Integer forward;

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
     * 浏览量

     */
    private Integer views;

    /**
     * 热度
     */
    private Integer heat;

    /**
     * 帖子状态
     0 草稿
     1 提交（待审核）
     2 审核失败
     3 人工审核
     4 人工审核通过
     8 审核通过（待发布）
     9 已发布
     */
    private Integer status;

    /**
     * 定时发布时间，不定时则为空
     */
    private LocalDate publishTime;

    /**
     * 拒绝理由
     */
    private String reason;

    //状态枚举类
    @Alias("PortaStatus")
    public enum Status{
        NORMAL((short)0),SUBMIT((short)1),FAIL((short)2),ADMIN_AUTH((short)3),ADMIN_SUCCESS((short)4),SUCCESS((short)8),PUBLISHED((short)9);
        short code;
        Status(short code){
            this.code = code;
        }
        public int getCode(){
            return this.code;
        }
    }


}
