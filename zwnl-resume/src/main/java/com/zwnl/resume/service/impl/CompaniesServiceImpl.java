package com.zwnl.resume.service.impl;

import com.zwnl.resume.domain.po.Companies;
import com.zwnl.resume.mapper.CompaniesMapper;
import com.zwnl.resume.service.ICompaniesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司表 服务实现类
 * </p>
 *
 * @author hui
 * @since 2024-09-17
 */
@Service
public class CompaniesServiceImpl extends ServiceImpl<CompaniesMapper, Companies> implements ICompaniesService {

}
