package com.zwnl.resume.service.impl;

import com.zwnl.common.utils.BeanUtils;
import com.zwnl.model.resume.dtos.ResumesDTO;
import com.zwnl.model.resume.po.Skills;
import com.zwnl.resume.mapper.SkillsMapper;
import com.zwnl.resume.service.ISkillsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 技能表 服务实现类
 * </p>
 *
 * @author hui
 * @since 2024-09-16
 */
@Service
public class SkillsServiceImpl extends ServiceImpl<SkillsMapper, Skills> implements ISkillsService {

    /**
     * 保存技能
     *
     * @param resumesDTO
     */
    @Override
    public void saveSkills(ResumesDTO resumesDTO) {
        Skills skills =BeanUtils.copyBean(resumesDTO,Skills.class);
        this.save(skills);
    }
}
