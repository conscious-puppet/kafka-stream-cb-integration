package com.abshekh.kafkastreampoc.service.events.consumers;

import com.abshekh.kafkastreampoc.model.kafka.Topic2Message;
import com.abshekh.kafkastreampoc.rest.client.PocRestClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class Topic2ConsumerService {
    private final PocRestClient pocRestClient;

    public Topic2ConsumerService(PocRestClient pocRestClient) {
        this.pocRestClient = pocRestClient;
    }

    @Bean
    public Consumer<KStream<Object, Topic2Message>> topic2Consumer() {
        return input -> input.foreach((k, val) -> {
            log.debug("topic2Consumer: {}", val);
            pocRestClient.restClient(val.getMessage());
            log.debug("topic2Consumer end...");
        });
    }
}