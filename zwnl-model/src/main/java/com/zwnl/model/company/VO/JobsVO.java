package com.zwnl.model.company.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zwnl.model.company.enums.CompanySize;
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
public class JobsVO implements Serializable {

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

    @ApiModelProperty(value = "公司ID")
    private Integer companyId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;



    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "所属行业")
    private String industry;

    @ApiModelProperty(value = "公司规模1-5代表('1-50', '51-200', '201-500', '501-1000', '1001+')")
    private CompanySize size;

    @ApiModelProperty(value = "公司地址")
    private String address;

    @ApiModelProperty(value = "联系人姓名")
    private String contactName;

    @ApiModelProperty(value = "联系人邮箱")
    private String contactEmail;

    @ApiModelProperty(value = "联系人电话")
    private String contactPhone;

    @ApiModelProperty(value = "公司描述")
    private String cdescription;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;


}
