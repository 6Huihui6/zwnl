package com.zwnl.resume.controller;


import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.model.resume.VO.ResumesVO;
import com.zwnl.model.resume.dtos.ResumesDTO;
import com.zwnl.resume.service.IResumesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 简历表 前端控制器
 * </p>
 *
 * @author hui
 * @since 2024-09-16
 */
@RestController
@RequestMapping("/resumes")
@RequiredArgsConstructor
@Api(tags = "简历管理")
public class ResumesController {

    private  final IResumesService resumesService;
    @PostMapping
    @ApiOperation("新增用户简历")
    public ResponseResult saveResume(@RequestBody ResumesDTO resumesDTO) {
        return  resumesService.saveResume(resumesDTO);
    }


    @GetMapping
    @ApiOperation("查询用户简历")
    public ResponseResult<ResumesVO> queryResume() {
        return  resumesService.queryResume();
    }

    @PutMapping
    @ApiOperation("更新用户简历")
    public ResponseResult updateResume(@RequestBody ResumesDTO resumesDTO) {
        return  resumesService.updateResume(resumesDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation("公司端---查询用户简历详情")
    public ResponseResult<ResumesVO> queryResumeById(@PathVariable("id") Integer id) {
        return  resumesService.queryResumeById(id);
        }


        @GetMapping("/{id}/download")
        @ApiOperation("下载用户简历")
        public ResponseResult<String> downloadResume(@PathVariable("id") Integer id) {
            return  resumesService.downloadResume(id);
        }

//        @GetMapping("/{label}")
//        @ApiOperation("根据标签查询简历")
//        public ResponseResult<ResumesVO> queryResumeByLabel(@PathVariable("label") String label) {
//            return  resumesService.queryResumeByLabel(label);
//        }




}
