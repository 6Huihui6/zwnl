package com.zwnl.resume.service;

import com.zwnl.model.resume.dtos.ResumesDTO;
import com.zwnl.model.resume.po.Skills;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 技能表 服务类
 * </p>
 *
 * @author hui
 * @since 2024-09-16
 */
public interface ISkillsService extends IService<Skills> {

    /**
     * 保存技能
     * @param resumesDTO
     */
    void saveSkills(ResumesDTO resumesDTO);
}
