package com.zwnl.company.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwnl.model.company.dtos.JobsDTO;
import com.zwnl.model.company.po.Jobs;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 职位表 Mapper 接口
 * </p>
 *
 * @author hui
 * @since 2024-09-18
 */
public interface JobsMapper extends BaseMapper<Jobs> {

//    @Select("SELECT jobs.*, companies.* FROM jobs LEFT JOIN companies ON jobs.company_id = companies.company_id")
//    List<JobsDTO> getAllJobs();
}
