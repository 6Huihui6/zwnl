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

import static com.zwnl.common.constants.MqConstants.Exchange.JOBS_EXCHANGE;
import static com.zwnl.common.constants.MqConstants.Key.JOBS_KEY_TEMPLATE;
import static com.zwnl.common.constants.MqConstants.Key.JOBS_NEW_KEY;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobsNewListener {

    private final ISearchService searchService;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "zwnl.jobs.new", durable = "true"),
            exchange = @Exchange(name = JOBS_EXCHANGE, type = ExchangeTypes.TOPIC),
            key = JOBS_NEW_KEY
    ))
    public void jobsNew(JobsDTO jobsDTO) throws IOException {
            log.info("SyncArticleListener,message={}",jobsDTO);
            searchService.syncJobs(jobsDTO);
    }


}