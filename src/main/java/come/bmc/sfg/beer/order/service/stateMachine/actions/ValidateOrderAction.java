package come.bmc.sfg.beer.order.service.stateMachine.actions;

import come.bmc.sfg.beer.order.service.config.JmsConfig;
import come.bmc.sfg.beer.order.service.domain.BeerOrder;
import come.bmc.sfg.beer.order.service.domain.BeerOrderEventEnum;
import come.bmc.sfg.beer.order.service.domain.BeerOrderStatusEnum;
import come.bmc.sfg.beer.order.service.repositories.BeerOrderRepository;
import come.bmc.sfg.beer.order.service.services.BeerOrderManagerImpl;
import come.bmc.sfg.beer.order.service.web.mappers.BeerOrderMapper;
import come.bmc.sfg.brewery.model.events.ValidateOrderRequest;
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
public class ValidateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;
    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        Optional<BeerOrder> beerOrderOptional = beerOrderRepository.findById(UUID.fromString(beerOrderId));

        beerOrderOptional.ifPresentOrElse(beerOrder -> {
            jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_QUEUE, ValidateOrderRequest.builder()
                    .beerOrder(beerOrderMapper.beerOrderToDto(beerOrder))
                    .build());
        }, () -> log.error("Order Not Found. Id: " + beerOrderId));

        log.debug("Sent Validation request to queue for order id " + beerOrderId);
    }
}