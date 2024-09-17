package com.zwnl.resume.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.common.utils.BeanUtils;
import com.zwnl.model.resume.VO.ResumesVO;
import com.zwnl.model.resume.dtos.ResumesDTO;
import com.zwnl.model.resume.po.Educations;
import com.zwnl.model.resume.po.Resumes;
import com.zwnl.model.resume.po.Skills;
import com.zwnl.model.resume.po.WorkExperiences;
import com.zwnl.model.user.pos.Users;
import com.zwnl.resume.mapper.ResumesMapper;
import com.zwnl.resume.service.IEducationsService;
import com.zwnl.resume.service.IResumesService;
import com.zwnl.resume.service.ISkillsService;
import com.zwnl.resume.service.IWorkExperiencesService;
import com.zwnl.utils.thread.AppThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 简历表 服务实现类
 * </p>
 *
 * @author hui
 * @since 2024-09-16
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ResumesServiceImpl extends ServiceImpl<ResumesMapper, Resumes> implements IResumesService {

    private final ISkillsService skillsService;
    private final IEducationsService educationsService;
    private final IWorkExperiencesService  workExperiencesService;

    /**
     * 保存简历
     *
     * @param resumesDTO
     */
    @Override
    public ResponseResult saveResume(ResumesDTO resumesDTO) {
//获取当前登录用户
        Users user = AppThreadLocalUtil.getUser();
        Integer userId =user.getUserId();
        Resumes resumes  = BeanUtils.copyBean(resumesDTO, Resumes.class);
        resumes.setSeekerId(userId);
        this.save(resumes);
        resumesDTO.setResumeId(resumes.getResumeId());
        // 保存简历详细信息
        skillsService.saveSkills(resumesDTO);
        // 保存简历教育经历
        educationsService.saveEducations(resumesDTO);
        // 保存简历工作经历
        workExperiencesService.saveWorkExperiences(resumesDTO);
        return ResponseResult.okResult(200, "保存成功");
    }

    /**
     * 查询简历
     *
     * @return
     */
    @Override
    public ResponseResult<ResumesVO> queryResume() {
        //获取当前登录用户
        Users user = AppThreadLocalUtil.getUser();
        Integer userId = user.getUserId();
        Resumes resumes = this.lambdaQuery().eq(Resumes::getSeekerId, userId).one();
        //封装简历VO
        ResumesVO resumesVO = BeanUtils.copyBean(resumes, ResumesVO.class);
        //封装简历详细信息
        Skills skills = skillsService.lambdaQuery().eq(Skills::getResumeId, resumes.getResumeId()).one();
        BeanUtils.copyProperties(skills, resumesVO);
//        resumesVO.setSkillId(skills.getSkillId());
//        resumesVO.setSkillName(skills.getSkillName());
//        resumesVO.setProficiency(skills.getProficiency());
        //封装简历教育经历
        Educations educations = educationsService.lambdaQuery().eq(Educations::getResumeId, resumes.getResumeId()).one();
        BeanUtils.copyProperties(educations, resumesVO);
//        resumesVO.setEducationId(educations.getEducationId());
//        resumesVO.setMajor(educations.getMajor());
//        resumesVO.setDegree(educations.getDegree());
//        resumesVO.setInstitutionName(educations.getInstitutionName());
//        resumesVO.setStartDate(educations.getStartDate());
//        resumesVO.setEndDate(educations.getEndDate());
        //封装简历工作经历
        WorkExperiences workExperiences=workExperiencesService.lambdaQuery().eq(WorkExperiences::getResumeId, resumes.getResumeId()).one();
        BeanUtils.copyProperties(workExperiences, resumesVO);
        return ResponseResult.okResult( resumesVO);
    }

    /**
     * 更新简历
     *
     * @param resumesDTO
     * @return
     */
    @Override
    public ResponseResult updateResume(ResumesDTO resumesDTO) {
        //获取当前登录用户
        Users user = AppThreadLocalUtil.getUser();
        Integer userId = user.getUserId();
        Resumes resumes = BeanUtils.copyBean(resumesDTO, Resumes.class);
        resumes.setSeekerId(userId);
        this.updateById(resumes);
        // 更新简历详细信息
        Skills skills = BeanUtils.copyBean(resumesDTO, Skills.class);
        skillsService.updateById(skills);
        // 更新简历教育经历
        Educations educations = BeanUtils.copyBean(resumesDTO, Educations.class);
        educationsService.updateById(educations);
        // 更新简历工作经历
        WorkExperiences workExperiences = BeanUtils.copyBean(resumesDTO, WorkExperiences.class);
        workExperiencesService.updateById(workExperiences);
        return ResponseResult.okResult(200, "更新成功");
    }
}
