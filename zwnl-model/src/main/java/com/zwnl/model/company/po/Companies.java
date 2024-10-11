package com.zwnl.model.company.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zwnl.model.company.enums.CompanySize;
import com.zwnl.model.company.enums.CompanyStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 公司表
 * </p>
 *
 * @author hui
 * @since 2024-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("companies")
@ApiModel(value="Companies对象", description="公司表")
public class Companies implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公司ID")
    @TableId(value = "company_id", type = IdType.AUTO)
    private Integer companyId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;


    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "公司状态--1为已上市，2未融资")
    private CompanyStatus comstatus;

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

    @ApiModelProperty(value = "是否在线，1为在线")
    private Integer isOnline;

    @ApiModelProperty(value = "logo")
    private String image;


}
