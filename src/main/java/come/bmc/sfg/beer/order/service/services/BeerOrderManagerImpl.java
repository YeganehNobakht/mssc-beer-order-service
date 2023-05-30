package come.bmc.sfg.beer.order.service.services;

import come.bmc.sfg.beer.order.service.domain.BeerOrder;
import come.bmc.sfg.beer.order.service.domain.BeerOrderEventEnum;
import come.bmc.sfg.beer.order.service.domain.BeerOrderStatusEnum;
import come.bmc.sfg.beer.order.service.repositories.BeerOrderRepository;
import come.bmc.sfg.beer.order.service.stateMachine.BeerOrderStateChangeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        sendBeerOrderEvent(saveBeerOrder);
        return saveBeerOrder;
    }

    private void sendBeerOrderEvent(BeerOrder beerOrder){
        StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> sm = build(beerOrder);

        Message<BeerOrderEventEnum> msg = MessageBuilder.withPayload(BeerOrderEventEnum.VALIDATE_ORDER)
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
