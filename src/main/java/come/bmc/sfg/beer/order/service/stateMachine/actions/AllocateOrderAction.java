package come.bmc.sfg.beer.order.service.stateMachine.actions;

import come.bmc.sfg.beer.order.service.config.JmsConfig;
import come.bmc.sfg.beer.order.service.domain.BeerOrder;
import come.bmc.sfg.beer.order.service.domain.BeerOrderEventEnum;
import come.bmc.sfg.beer.order.service.domain.BeerOrderStatusEnum;
import come.bmc.sfg.beer.order.service.repositories.BeerOrderRepository;
import come.bmc.sfg.beer.order.service.services.BeerOrderManager;
import come.bmc.sfg.beer.order.service.services.BeerOrderManagerImpl;
import come.bmc.sfg.beer.order.service.web.mappers.BeerOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Masoumeh Yeganeh
 * @created 30/05/2023
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AllocateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        BeerOrder beerOrder = beerOrderRepository.findOneById(UUID.fromString(beerOrderId));

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_QUEUE, beerOrderMapper.beerOrderToDto(beerOrder));

        log.debug("sent allocation request for order id: " + beerOrderId);
    }
}
