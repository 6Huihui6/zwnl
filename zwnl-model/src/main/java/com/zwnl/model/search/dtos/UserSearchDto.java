package com.zwnl.model.search.dtos;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;

import java.util.Date;

@Data
public class UserSearchDto {

    /**
     * 搜索关键字
     */
    String searchWords;
    /**
     * 当前页
     */
    int pageNum;
    /**
     * 分页条数
     */
    int pageSize;
    /**
     * 最小时间
     */
    Date minBehotTime;

    public int getFromIndex(){
        if(this.pageNum<1)return 0;
        if(this.pageSize<1) this.pageSize = 10;
        return this.pageSize * (pageNum-1);
    }
    // 这里定义查询条件
//    @ApiModelProperty(value = "岗位名称")
//    private String title;
//
//    @ApiModelProperty(value = "岗位描述")
//    private String description;
//
//    @ApiModelProperty(value = "岗位要求")
//    private String requirements;
//
//    @ApiModelProperty(value = "岗位薪资")
//    private String salary;
//
//    @ApiModelProperty(value = "岗位技能要求")
//    private String skills;
//
//    @ApiModelProperty(value = "岗位学历要求")
//    private String education;
//
//    @ApiModelProperty(value = "岗位地址")
//    private String location;
//
//    @ApiModelProperty(value = "公司名称")
//    private String name;
//
//    @ApiModelProperty(value = "公司行业")
//    private String industry;

}
