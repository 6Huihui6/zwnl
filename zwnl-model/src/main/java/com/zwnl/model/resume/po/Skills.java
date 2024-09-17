package com.zwnl.model.resume.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.zwnl.model.resume.enums.proficGrade;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 技能表
 * </p>
 *
 * @author hui
 * @since 2024-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("skills")
@ApiModel(value="Skills对象", description="技能表")
public class Skills implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "技能ID")
    @TableId(value = "skill_id", type = IdType.AUTO)
    private Integer skillId;

    @ApiModelProperty(value = "简历ID")
    private Integer resumeId;

    @ApiModelProperty(value = "技能名称")
    private String skillName;

    @ApiModelProperty(value = "熟练程度")
    private proficGrade proficiency;


}
