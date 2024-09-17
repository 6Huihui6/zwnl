package com.zwnl.resume.service;

import com.zwnl.model.resume.dtos.ResumesDTO;
import com.zwnl.model.resume.po.Educations;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 教育经历表 服务类
 * </p>
 *
 * @author hui
 * @since 2024-09-16
 */
public interface IEducationsService extends IService<Educations> {

    /**
     * 保存教育经历
     * @param resumesDTO
     */
    void saveEducations(ResumesDTO resumesDTO);
}
