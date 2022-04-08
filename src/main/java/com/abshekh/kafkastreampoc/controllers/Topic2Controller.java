package com.abshekh.kafkastreampoc.controllers;

import com.abshekh.kafkastreampoc.service.events.suppliers.Topic2ProducerService;
import com.abshekh.kafkastreampoc.model.request.Topic2Request;
import com.abshekh.kafkastreampoc.rest.client.PocRestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
@Slf4j
public class Topic2Controller {
    private final Topic2ProducerService topic2ProducerService;
    private final PocRestClient pocRestClient;

    public Topic2Controller(Topic2ProducerService topic2ProducerService, PocRestClient pocRestClient) {
        this.topic2ProducerService = topic2ProducerService;
        this.pocRestClient = pocRestClient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> postTopic2Message(@RequestBody Topic2Request request) {
        log.debug("postTopic2Message: {}", request);
        topic2ProducerService.topic2Publisher(request);
        return Mono.just("ok");
    }


    @GetMapping("/cb")
    @ResponseStatus(HttpStatus.OK)
    public void activateCb() {
        log.debug("activateCb...");
        pocRestClient.restClient("cb");
        log.debug("endCb...");
    }
}
