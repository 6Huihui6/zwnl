package com.zwnl.company.controller;


import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.company.service.ICompaniesService;
import com.zwnl.model.company.po.Companies;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 公司表 前端控制器
 * </p>
 *
 * @author hui
 * @since 2024-09-17
 */
@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "公司接口")
public class CompaniesController {

    private final ICompaniesService companiesService;

    @PostMapping
    @ApiOperation( "新增公司信息")
    public ResponseResult createCompany(@RequestBody Companies companies) {
        log.info("新增公司信息：{}", companies);
        companiesService.save(companies);
        return ResponseResult.okResult("新增公司信息成功");
    }
    @PutMapping
    @ApiOperation( "更新公司信息")
    public ResponseResult updateCompany(@RequestBody Companies companies) {
        log.info("更新公司信息：{}", companies);
        companiesService.updateById(companies);
        return ResponseResult.okResult("更新公司信息成功");
    }
    @DeleteMapping("/{id}")
    @ApiOperation( "删除公司信息")
    public ResponseResult deleteCompany(@PathVariable("id") Integer id) {
        log.info("删除公司信息：{}", id);
        companiesService.deleteCompany(id);
        return ResponseResult.okResult("删除公司信息成功");
    }
    @GetMapping("/{id}")
    @ApiOperation( "查询公司信息")
    public ResponseResult getCompany(@PathVariable("id") Integer id) {
        log.info("查询公司信息：{}", id);
        Companies companies = companiesService.getById(id);
        return ResponseResult.okResult(companies);
    }
    @GetMapping
    @ApiOperation( "查询公司列表")
    public ResponseResult getCompanies() {
        log.info("查询公司列表");
        return ResponseResult.okResult(companiesService.list());
    }

}
