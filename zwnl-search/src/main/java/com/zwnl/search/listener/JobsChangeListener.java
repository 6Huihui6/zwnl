package com.zwnl.search.listener;

import com.zwnl.model.company.dtos.JobsDTO;
import com.zwnl.search.service.ISearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.List;

import static com.zwnl.common.constants.MqConstants.Exchange.JOBS_EXCHANGE;
import static com.zwnl.common.constants.MqConstants.Key.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobsChangeListener {

    private final ISearchService searchService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "zwnl.jobs.queue", durable = "true"),
            exchange = @Exchange(name = JOBS_EXCHANGE, type = ExchangeTypes.TOPIC),
            key = JOBS_KEY_TEMPLATE
    ))
    public void JobsUpdate(JobsDTO jobsDTO) throws IOException {
        log.info("SyncArticleListener,message={}", jobsDTO);
        searchService.syncChangeJobs(jobsDTO);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "zwnl.jobs.queue", durable = "true"),
            exchange = @Exchange(name = JOBS_EXCHANGE, type = ExchangeTypes.TOPIC),
            key = COMPANY_KEY_TEMPLATE
    ))
    public void JobsListUpdate(List<JobsDTO> jobsDTOList) throws IOException {
        log.info("SyncArticleListener,message={}", jobsDTOList);
        searchService.jobsListChange(jobsDTOList);
    }


}