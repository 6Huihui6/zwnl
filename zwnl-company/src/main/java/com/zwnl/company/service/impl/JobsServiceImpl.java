package com.zwnl.company.service.impl;

import com.zwnl.common.utils.BeanUtils;
import com.zwnl.company.interceptor.AppTokenInterceptor;
import com.zwnl.company.mapper.JobsMapper;
import com.zwnl.company.service.ICompaniesService;
import com.zwnl.company.service.IJobsService;
import com.zwnl.model.company.dtos.JobsDTO;
import com.zwnl.model.company.po.Jobs;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwnl.model.user.pos.Users;
import com.zwnl.utils.thread.AppThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 职位表 服务实现类
 * </p>
 *
 * @author hui
 * @since 2024-09-18
 */
@Service
@RequiredArgsConstructor
public class JobsServiceImpl extends ServiceImpl<JobsMapper, Jobs> implements IJobsService {

    /**
     * 保存职位信息
     *
     * @param jobsDTO
     */
    @Override
    public void saveJob(JobsDTO jobsDTO) {
           //获取当前登录公司的用户id信息
        Users user = AppThreadLocalUtil.getUser();
        Integer userId = user.getUserId();
        jobsDTO.setUserId(userId);
        Jobs jobs = BeanUtils.copyBean(jobsDTO, Jobs.class);
        //保存职位信息
        if (jobs.getCompanyId() != null) {
        this.save(jobs);
        }else {
            throw new RuntimeException("公司id不能为空");
        }

    }

    /**
     * 更新职位信息
     *
     * @param jobsDTO
     */
    @Override
    public void updateJob(JobsDTO jobsDTO) {
        Jobs jobs = BeanUtils.copyBean(jobsDTO, Jobs.class);
        //更新职位信息
        this.updateById(jobs);
    }
}
