package com.zwnl.model.company.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 职位表
 * </p>
 *
 * @author hui
 * @since 2024-09-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("jobs")
@ApiModel(value="Jobs对象", description="职位表")
public class Jobs implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "职位ID")
    @TableId(value = "job_id", type = IdType.AUTO)
    private Integer jobId;

    @ApiModelProperty(value = "职位名称")
    private String title;

    @ApiModelProperty(value = "职位描述")
    private String description;

    @ApiModelProperty(value = "职位要求")
    private String requirements;

    @ApiModelProperty(value = "工作地点")
    private String location;

    @ApiModelProperty(value = "最低薪资")
    private BigDecimal minSalary;

    @ApiModelProperty(value = "最高薪资")
    private BigDecimal maxSalary;

    @ApiModelProperty(value = "薪资范围")
    private String salary;

    @ApiModelProperty(value = "学历要求")
    private String education;

    @ApiModelProperty(value = "公司ID")
    private Integer companyId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;


}
