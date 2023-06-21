package com.bmc.sfg.beer.order.service.testComponents;

import com.bmc.sfg.beer.order.service.config.JmsConfig;
import com.bmc.sfg.brewery.model.events.ValidateOrderRequest;
import com.bmc.sfg.brewery.model.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author Masoumeh Yeganeh
 * @created 21/06/2023
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void list(Message msg){

        ValidateOrderRequest request = (ValidateOrderRequest) msg.getPayload();

        System.out.println("************* I Ran...");

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResult.builder()
                        .isValid(true)
                        .orderId(request.getBeerOrder().getId())
                        .build());
    }
}
