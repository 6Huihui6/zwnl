package com.zwnl.company.job;

import com.xxl.job.core.handler.annotation.XxlJob;
import com.zwnl.company.service.IJobsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;



@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateJobsTask {

    private final IJobsService jobsService;

    @XxlJob("updateJobHandler")
    public void updateJobList() {
        log.info("Update job list task is running...");
        jobsService.updateJobList();
        log.info("Update job list task is finished.");
    }
}
