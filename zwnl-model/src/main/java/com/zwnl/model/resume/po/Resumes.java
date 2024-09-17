package com.zwnl.model.resume.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 简历表
 * </p>
 *
 * @author hui
 * @since 2024-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("resumes")
@ApiModel(value="Resumes对象", description="简历表")
public class Resumes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "简历ID")
    @TableId(value = "resume_id", type = IdType.AUTO)
    private Integer resumeId;

    @ApiModelProperty(value = "求职者ID")
    private Integer seekerId;

    @ApiModelProperty(value = "简历标题")
    private String title;

    @ApiModelProperty(value = "个人简介")
    private String summary;

    @ApiModelProperty(value = "联系方式")
    private String contactInfo;

    @ApiModelProperty(value = "简历附件URL")
    private String resumeFileUrl;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;


}
