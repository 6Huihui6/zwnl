package com.zwnl.resume.service;

import com.zwnl.model.resume.dtos.ResumesDTO;
import com.zwnl.model.resume.po.WorkExperiences;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 工作经历表 服务类
 * </p>
 *
 * @author hui
 * @since 2024-09-16
 */
public interface IWorkExperiencesService extends IService<WorkExperiences> {

    /**
     * 保存工作经历
     * @param resumesDTO
     */
    void saveWorkExperiences(ResumesDTO resumesDTO);
}
