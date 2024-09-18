package com.zwnl.company.service.impl;

import com.zwnl.company.mapper.CompaniesMapper;
import com.zwnl.company.service.ICompaniesService;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwnl.company.service.IJobsService;
import com.zwnl.model.company.po.Companies;
import com.zwnl.model.company.po.Jobs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 公司表 服务实现类
 * </p>
 *
 * @author hui
 * @since 2024-09-17
 */
@Service
@RequiredArgsConstructor
public class CompaniesServiceImpl extends ServiceImpl<CompaniesMapper, Companies> implements ICompaniesService {


    private  final IJobsService jobsService;
    /**
     * 删除公司
     *
     * @param id
     */
    @Override
    public void deleteCompany(Integer id) {
        //当前公司还发布有职位，应该把该公司发布的职位删除再删除公司
//        jobsService,lambdaQuery().eq(Companies::getCompanyId,id).list().forEach(jobsService::deleteJob);
        List<Jobs> list = jobsService.lambdaQuery().eq(Jobs::getCompanyId, id).list();
        Set<Integer> jobIds=new HashSet<>();
        for (Jobs jobs : list) {
            if(jobs.getJobId()!=null) {
                jobIds.add(jobs.getJobId());
            }
        }
        jobsService.removeByIds(jobIds);
        this.removeById(id);
    }
}
