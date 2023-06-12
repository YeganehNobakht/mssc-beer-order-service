package com.bmc.sfg.beer.order.service.stateMachine.actions;

import com.bmc.sfg.beer.order.service.domain.BeerOrderEventEnum;
import com.bmc.sfg.beer.order.service.domain.BeerOrderStatusEnum;
import com.bmc.sfg.beer.order.service.services.BeerOrderManagerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * @author Masoumeh Yeganeh
 * @created 12/06/2023
 */
@Slf4j
@Component
public class ValidationFailureAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        log.error("Compensating Transaction.... Validation Failed: " + beerOrderId);
    }
}
