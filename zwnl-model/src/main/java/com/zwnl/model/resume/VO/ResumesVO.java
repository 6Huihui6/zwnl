package com.zwnl.model.resume.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zwnl.model.resume.enums.ProficGrade;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

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
@ApiModel(value="Resumes对象", description="简历表")
public class ResumesVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "简历ID")
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

    @ApiModelProperty(value = "技能ID")
    @TableId(value = "skill_id", type = IdType.AUTO)
    private Integer skillId;

    @ApiModelProperty(value = "技能名称")
    private String skillName;

    @ApiModelProperty(value = "熟练程度")
    private ProficGrade proficiency;

    @ApiModelProperty(value = "教育背景ID")
    @TableId(value = "education_id", type = IdType.AUTO)
    private Integer educationId;

    @ApiModelProperty(value = "工作经历ID")
    @TableId(value = "experience_id", type = IdType.AUTO)
    private Integer experienceId;

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
