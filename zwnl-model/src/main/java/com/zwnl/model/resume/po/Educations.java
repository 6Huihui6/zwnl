package com.zwnl.model.resume.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 教育经历表
 * </p>
 *
 * @author hui
 * @since 2024-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("educations")
@ApiModel(value="Educations对象", description="教育经历表")
public class Educations implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教育背景ID")
    @TableId(value = "education_id", type = IdType.AUTO)
    private Integer educationId;

    @ApiModelProperty(value = "简历ID")
    private Integer resumeId;

    @ApiModelProperty(value = "学校或教育机构名称")
    private String institutionName;

    @ApiModelProperty(value = "学位")
    private String degree;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "开始时间")
    private LocalDate startDate;

    @ApiModelProperty(value = "结束时间")
    private LocalDate endDate;


}
