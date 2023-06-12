package com.bmc.sfg.beer.order.service.services;

import com.bmc.sfg.beer.order.service.domain.BeerOrder;
import com.bmc.sfg.beer.order.service.domain.BeerOrderEventEnum;
import com.bmc.sfg.beer.order.service.domain.BeerOrderStatusEnum;
import com.bmc.sfg.beer.order.service.stateMachine.BeerOrderStateChangeInterceptor;
import com.bmc.sfg.beer.order.service.repositories.BeerOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * @author Masoumeh Yeganeh
 * @created 30/05/2023
 */

@Service
@RequiredArgsConstructor
public class BeerOrderManagerImpl implements BeerOrderManager {

    public static final String ORDER_ID_HEADER = "ORDER_ID_HEADER";
    private final BeerOrderStateChangeInterceptor beerOrderStateChangeInterceptor;

    private final StateMachineFactory<BeerOrderStatusEnum, BeerOrderEventEnum> stateMachineFactory;
    private final BeerOrderRepository beerOrderRepository;

    @Override
    @Transactional
    public BeerOrder newBeerOrder(BeerOrder beerOrder) {
        beerOrder.setId(null);
        beerOrder.setOrderStatus(BeerOrderStatusEnum.NEW);

        BeerOrder saveBeerOrder = beerOrderRepository.save(beerOrder);
        sendBeerOrderEvent(saveBeerOrder, BeerOrderEventEnum.VALIDATE_ORDER);
        return saveBeerOrder;
    }

    public void processValidationResult(UUID beerOrderId, Boolean isValid) {
        BeerOrder beerOrder = beerOrderRepository.getOne(beerOrderId);

        if (isValid){
            sendBeerOrderEvent(beerOrder, BeerOrderEventEnum.VALIDATION_PASSED);

            BeerOrder validatedOrder = beerOrderRepository.findOneById(beerOrderId);

            sendBeerOrderEvent(validatedOrder, BeerOrderEventEnum.ALLOCATE_ORDER);
        }else {
            sendBeerOrderEvent(beerOrder, BeerOrderEventEnum.VALIDATION_FAILED);
        }
    }

    private void sendBeerOrderEvent(BeerOrder beerOrder, BeerOrderEventEnum eventEnum){
        StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> sm = build(beerOrder);

        Message msg = MessageBuilder.withPayload(eventEnum)
                .setHeader(ORDER_ID_HEADER, beerOrder.getId().toString())
                .build();

        sm.sendEvent(msg);
    }

    private StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> build(BeerOrder beerOrder){
//        this is going to make a request of stateMachineFactory to return back a state machine for that beerOrder id;
        StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> sm = stateMachineFactory.getStateMachine(beerOrder.getId());

        sm.stop();

        sm.getStateMachineAccessor()
                        .doWithAllRegions(sma->{
                            sma.addStateMachineInterceptor(beerOrderStateChangeInterceptor);
                            sma.resetStateMachine(new DefaultStateMachineContext<>(beerOrder.getOrderStatus(), null, null, null));
                        });

        sm.start();
        return sm;
    }
}
