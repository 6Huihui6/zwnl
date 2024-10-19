package com.zwnl.search.service;

import com.zwnl.model.company.dtos.JobsDTO;

import java.io.IOException;
import java.util.List;

public interface ISearchService {
    /**
     * 同步职位信息
     * @param jobsDTO
     */
    void syncJobs(JobsDTO jobsDTO) throws IOException;

    /**
     * 同步职位变更信息
     * @param jobsDTO
     */
    void syncChangeJobs(JobsDTO jobsDTO) throws IOException;

    /**
     * 同步职位列表变更信息
     * @param jobsDTOList
     */
    void jobsListChange(List<JobsDTO> jobsDTOList) throws IOException;
}
