package com.zwnl.resume.service.impl;

import com.zwnl.common.utils.BeanUtils;
import com.zwnl.model.resume.dtos.ResumesDTO;
import com.zwnl.model.resume.po.Educations;
import com.zwnl.resume.mapper.EducationsMapper;
import com.zwnl.resume.service.IEducationsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 教育经历表 服务实现类
 * </p>
 *
 * @author hui
 * @since 2024-09-16
 */
@Service
public class EducationsServiceImpl extends ServiceImpl<EducationsMapper, Educations> implements IEducationsService {

    /**
     * 保存教育经历
     *
     * @param resumesDTO
     */
    @Override
    public void saveEducations(ResumesDTO resumesDTO) {
        Educations educations = BeanUtils.copyBean(resumesDTO,Educations.class);
        this.save(educations);
    }
}
