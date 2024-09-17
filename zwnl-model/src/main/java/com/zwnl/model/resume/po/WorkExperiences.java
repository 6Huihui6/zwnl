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
 * 工作经历表
 * </p>
 *
 * @author hui
 * @since 2024-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("work_experiences")
@ApiModel(value="WorkExperiences对象", description="工作经历表")
public class WorkExperiences implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工作经历ID")
    @TableId(value = "experience_id", type = IdType.AUTO)
    private Integer experienceId;

    @ApiModelProperty(value = "简历ID")
    private Integer resumeId;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "职位名称")
    private String position;

    @ApiModelProperty(value = "开始时间")
    private LocalDate cstartDate;

    @ApiModelProperty(value = "结束时间")
    private LocalDate cendDate;

    @ApiModelProperty(value = "职责和业绩")
    private String responsibilities;


}
