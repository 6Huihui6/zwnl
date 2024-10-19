package com.zwnl.company.service.impl;

import com.zwnl.common.autoconfigure.mq.RabbitMqHelper;
import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.common.enums.AppHttpCodeEnum;
import com.zwnl.common.utils.BeanUtils;
import com.zwnl.company.mapper.CompaniesMapper;
import com.zwnl.company.mapper.JobsMapper;
import com.zwnl.company.service.IJobsService;
import com.zwnl.model.company.VO.JobsVO;
import com.zwnl.model.company.dtos.JobsDTO;
import com.zwnl.model.company.po.Companies;
import com.zwnl.model.company.po.Jobs;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwnl.model.user.pos.Users;
import com.zwnl.utils.thread.AppThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.zwnl.common.constants.MqConstants.Exchange.JOBS_EXCHANGE;
import static com.zwnl.common.constants.MqConstants.Key.*;

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

    private  final CompaniesMapper companiesMapper;
    private final RabbitMqHelper rabbitMqHelper;

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
        if (jobs.getCompanyId() == null) {
            throw new RuntimeException("公司id不能为空");
        }
        this.save(jobs);
//        int insert = jobsMapper.insert(jobs);
        JobsDTO jobsDoc = BeanUtils.copyBean(jobs, JobsDTO.class);
        Companies companies = companiesMapper.selectById(jobs.getCompanyId());
        BeanUtils.copyProperties(companies, jobsDoc);
        rabbitMqHelper.send(
                JOBS_EXCHANGE,
                JOBS_NEW_KEY,
                jobsDoc
        );

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
        Companies companies = companiesMapper.selectById(jobs.getCompanyId());
        BeanUtils.copyProperties(companies, jobsDTO);
        rabbitMqHelper.send(
                JOBS_EXCHANGE,
                JOBS_KEY_TEMPLATE,
                jobsDTO
        );
    }


    /**
     *  定时任务更新职位信息
     */

    public void updateJobList() {
        List<Jobs> list = list();
        List<JobsDTO> jobsDTOList = BeanUtils.copyList(list, JobsDTO.class);
        Set<Integer> companyIds = jobsDTOList.stream().filter(c -> c.getCompanyId() != null).map(c -> c.getCompanyId()).collect(Collectors.toSet());
        //更新职位信息
        List<Companies> companies = companiesMapper.selectBatchIds(companyIds);
        Map<Integer, Companies> companiesMap = companies.stream().collect(Collectors.toMap(Companies::getCompanyId, c -> c));

        for (JobsDTO jobsDTO : jobsDTOList) {
            Companies company = companiesMap.get(jobsDTO.getCompanyId());
            BeanUtils.copyProperties(company, jobsDTO);
        }
        rabbitMqHelper.send(
                JOBS_EXCHANGE,
                COMPANY_KEY_TEMPLATE,
                jobsDTOList
        );
    }

    /**
     * 获取所有职位信息
     *
     * @return
     */
    @Override
    public List<JobsVO> getAllJobs() {
        List<Jobs> list = list();
        Set<Integer> companyIds = list.stream().map(Jobs::getCompanyId).filter(Objects::nonNull).collect(Collectors.toSet());
        List<Companies> companies = companiesMapper.selectBatchIds(companyIds);
        Map<Integer, Companies> companiesMap = companies.stream().collect(Collectors.toMap(Companies::getCompanyId, c -> c));
        List<JobsVO> jobsVOList = BeanUtils.copyList(list, JobsVO.class);
        for (JobsVO jobsVO : jobsVOList) {
            jobsVO.setCompanies(companiesMap.get(jobsVO.getCompanyId()));
        }
        return jobsVOList;
    }

    /**
     * 根据id获取职位信息
     *
     * @param jobId
     * @return
     */
    @Override
    public ResponseResult<JobsVO> getJobById(Integer jobId) {
        if (jobId == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        Jobs jobs = this.getById(jobId);
        JobsVO jobsVO = BeanUtils.copyBean(jobs, JobsVO.class);
        Companies companies = companiesMapper.selectById(jobs.getCompanyId());
        jobsVO.setCompanies(companies);
        return ResponseResult.okResult(jobsVO);
    }


}
