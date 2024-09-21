package com.zwnl.resume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.model.resume.VO.ResumesVO;
import com.zwnl.model.resume.dtos.ResumesDTO;
import com.zwnl.model.resume.po.Resumes;

/**
 * <p>
 * 简历表 服务类
 * </p>
 *
 * @author hui
 * @since 2024-09-16
 */
public interface IResumesService extends IService<Resumes> {

    /**
     * 保存简历
     * @param resumesDTO
     */
    ResponseResult saveResume(ResumesDTO resumesDTO);

    /**
     * 查询简历
     * @return
     */
    ResponseResult<ResumesVO> queryResume();


    /**
     * 更新简历
     * @param resumesDTO
     * @return
     */
    ResponseResult updateResume(ResumesDTO resumesDTO);

    /**
     * 查询简历
     * @param id
     * @return
     */
    ResponseResult<ResumesVO> queryResumeById(Integer id);

    /**
     * 下载简历
     * @param id
     * @return
     */
    ResponseResult<String> downloadResume(Integer id);
}
