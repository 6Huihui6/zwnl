package com.zwnl.company.service;

import com.zwnl.model.company.dtos.JobsDTO;
import com.zwnl.model.company.po.Jobs;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 职位表 服务类
 * </p>
 *
 * @author hui
 * @since 2024-09-18
 */
public interface IJobsService extends IService<Jobs> {

    /**
     * 保存职位信息
     * @param jobsDTO
     */
    void saveJob(JobsDTO jobsDTO);

    /**
     * 更新职位信息
     * @param jobsDTO
     */
    void updateJob(JobsDTO jobsDTO);
}
