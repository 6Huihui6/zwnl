package com.zwnl.company.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zwnl.model.company.po.Companies;

/**
 * <p>
 * 公司表 服务类
 * </p>
 *
 * @author hui
 * @since 2024-09-17
 */
public interface ICompaniesService extends IService<Companies> {

    /**
     * 删除公司
     * @param id
     */
    void deleteCompany(Integer id);
}
