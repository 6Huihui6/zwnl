package com.zwnl.resume.service.impl;

import com.zwnl.common.utils.BeanUtils;
import com.zwnl.model.resume.dtos.ResumesDTO;
import com.zwnl.model.resume.po.WorkExperiences;
import com.zwnl.resume.mapper.WorkExperiencesMapper;
import com.zwnl.resume.service.IWorkExperiencesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 工作经历表 服务实现类
 * </p>
 *
 * @author hui
 * @since 2024-09-16
 */
@Service
public class WorkExperiencesServiceImpl extends ServiceImpl<WorkExperiencesMapper, WorkExperiences> implements IWorkExperiencesService {

    /**
     * 保存工作经历
     *
     * @param resumesDTO
     */
    @Override
    public void saveWorkExperiences(ResumesDTO resumesDTO) {
        WorkExperiences workExperiences =  BeanUtils.copyBean(resumesDTO,WorkExperiences.class);
        this.save(workExperiences);
    }
}
