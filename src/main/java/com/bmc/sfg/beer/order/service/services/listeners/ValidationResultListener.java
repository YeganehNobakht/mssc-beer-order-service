package com.bmc.sfg.beer.order.service.services.listeners;

import com.bmc.sfg.beer.order.service.config.JmsConfig;
import com.bmc.sfg.beer.order.service.services.BeerOrderManager;
import com.bmc.sfg.brewery.model.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Masoumeh Yeganeh
 * @created 12/06/2023
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ValidationResultListener {

    private final BeerOrderManager beerOrderManager;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE)
    public void listen(ValidateOrderResult result){
        final UUID beerOrderId = result.getOrderId();

        log.debug("Validation Result for Order Id: " + beerOrderId);
        System.out.println("in validation result listener");

        beerOrderManager.processValidationResult(beerOrderId, result.getIsValid());
    }
}
