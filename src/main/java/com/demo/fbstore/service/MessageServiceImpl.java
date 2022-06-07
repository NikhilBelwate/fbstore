package com.demo.fbstore.service;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.core.subscriber.PubSubSubscriberOperations;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.integration.channel.DirectChannel;

@Service
public class MessageServiceImpl implements MessageServiceMain {

    private String mainMessage;

    public String getMainMessage() {
        return mainMessage;
    }

    public void setMainMessage(String mainMessage) {
        this.mainMessage = mainMessage;
    }


    @Bean
    public MessageChannel myMessageChannel() {
        return new DirectChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
            @Qualifier("myMessageChannel") MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, "NewEmpSubscription");
        adapter.setOutputChannel(inputChannel);

        return adapter;
    }

    @ServiceActivator(inputChannel = "myMessageChannel")
    public void receiveMessage(String payload){
        this.mainMessage=payload;
    }
}
