package sfg.beer.order.service.web.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sfg.beer.order.service.domain.BeerOrder;
import sfg.beer.order.service.domain.BeerOrder.BeerOrderBuilder;
import sfg.beer.order.service.domain.BeerOrderLine;
import sfg.beer.order.service.domain.BeerOrderStatusEnum;
import sfg.beer.order.service.domain.Customer;
import sfg.beer.order.service.web.model.BeerOrderDto;
import sfg.beer.order.service.web.model.BeerOrderDto.BeerOrderDtoBuilder;
import sfg.beer.order.service.web.model.BeerOrderLineDto;
import sfg.beer.order.service.web.model.OrderStatusEnum;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-30T10:06:28+0330",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
@Component
public class BeerOrderMapperImpl implements BeerOrderMapper {

    @Autowired
    private DateMapper dateMapper;
    @Autowired
    private BeerOrderLineMapper beerOrderLineMapper;

    @Override
    public BeerOrderDto beerOrderToDto(BeerOrder beerOrder) {
        if ( beerOrder == null ) {
            return null;
        }

        BeerOrderDtoBuilder beerOrderDto = BeerOrderDto.builder();

        beerOrderDto.customerId( beerOrderCustomerId( beerOrder ) );
        beerOrderDto.id( beerOrder.getId() );
        if ( beerOrder.getVersion() != null ) {
            beerOrderDto.version( beerOrder.getVersion().intValue() );
        }
        beerOrderDto.createdDate( dateMapper.asOffsetDateTime( beerOrder.getCreatedDate() ) );
        beerOrderDto.lastModifiedDate( dateMapper.asOffsetDateTime( beerOrder.getLastModifiedDate() ) );
        beerOrderDto.beerOrderLines( beerOrderLineSetToBeerOrderLineDtoList( beerOrder.getBeerOrderLines() ) );
        beerOrderDto.orderStatus( beerOrderStatusEnumToOrderStatusEnum( beerOrder.getOrderStatus() ) );
        beerOrderDto.orderStatusCallbackUrl( beerOrder.getOrderStatusCallbackUrl() );
        beerOrderDto.customerRef( beerOrder.getCustomerRef() );

        return beerOrderDto.build();
    }

    @Override
    public BeerOrder dtoToBeerOrder(BeerOrderDto dto) {
        if ( dto == null ) {
            return null;
        }

        BeerOrderBuilder beerOrder = BeerOrder.builder();

        beerOrder.id( dto.getId() );
        if ( dto.getVersion() != null ) {
            beerOrder.version( dto.getVersion().longValue() );
        }
        beerOrder.createdDate( dateMapper.asTimestamp( dto.getCreatedDate() ) );
        beerOrder.lastModifiedDate( dateMapper.asTimestamp( dto.getLastModifiedDate() ) );
        beerOrder.customerRef( dto.getCustomerRef() );
        beerOrder.beerOrderLines( beerOrderLineDtoListToBeerOrderLineSet( dto.getBeerOrderLines() ) );
        beerOrder.orderStatus( orderStatusEnumToBeerOrderStatusEnum( dto.getOrderStatus() ) );
        beerOrder.orderStatusCallbackUrl( dto.getOrderStatusCallbackUrl() );

        return beerOrder.build();
    }

    private UUID beerOrderCustomerId(BeerOrder beerOrder) {
        if ( beerOrder == null ) {
            return null;
        }
        Customer customer = beerOrder.getCustomer();
        if ( customer == null ) {
            return null;
        }
        UUID id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<BeerOrderLineDto> beerOrderLineSetToBeerOrderLineDtoList(Set<BeerOrderLine> set) {
        if ( set == null ) {
            return null;
        }

        List<BeerOrderLineDto> list = new ArrayList<BeerOrderLineDto>( set.size() );
        for ( BeerOrderLine beerOrderLine : set ) {
            list.add( beerOrderLineMapper.beerOrderLineToDto( beerOrderLine ) );
        }

        return list;
    }

    protected OrderStatusEnum beerOrderStatusEnumToOrderStatusEnum(BeerOrderStatusEnum beerOrderStatusEnum) {
        if ( beerOrderStatusEnum == null ) {
            return null;
        }

        OrderStatusEnum orderStatusEnum;

        switch ( beerOrderStatusEnum ) {
            case NEW: orderStatusEnum = OrderStatusEnum.NEW;
            break;
            case VALIDATED: orderStatusEnum = OrderStatusEnum.VALIDATED;
            break;
            case VALIDATION_EXCEPTION: orderStatusEnum = OrderStatusEnum.VALIDATION_EXCEPTION;
            break;
            case ALLOCATED: orderStatusEnum = OrderStatusEnum.ALLOCATED;
            break;
            case ALLOCATION_EXCEPTION: orderStatusEnum = OrderStatusEnum.ALLOCATION_EXCEPTION;
            break;
            case PENDING_INVENTORY: orderStatusEnum = OrderStatusEnum.PENDING_INVENTORY;
            break;
            case PICKED_UP: orderStatusEnum = OrderStatusEnum.PICKED_UP;
            break;
            case DELIVERED: orderStatusEnum = OrderStatusEnum.DELIVERED;
            break;
            case DELIVERY_EXCEPTION: orderStatusEnum = OrderStatusEnum.DELIVERY_EXCEPTION;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + beerOrderStatusEnum );
        }

        return orderStatusEnum;
    }

    protected Set<BeerOrderLine> beerOrderLineDtoListToBeerOrderLineSet(List<BeerOrderLineDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<BeerOrderLine> set = new HashSet<BeerOrderLine>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( BeerOrderLineDto beerOrderLineDto : list ) {
            set.add( beerOrderLineMapper.dtoToBeerOrderLine( beerOrderLineDto ) );
        }

        return set;
    }

    protected BeerOrderStatusEnum orderStatusEnumToBeerOrderStatusEnum(OrderStatusEnum orderStatusEnum) {
        if ( orderStatusEnum == null ) {
            return null;
        }

        BeerOrderStatusEnum beerOrderStatusEnum;

        switch ( orderStatusEnum ) {
            case NEW: beerOrderStatusEnum = BeerOrderStatusEnum.NEW;
            break;
            case VALIDATED: beerOrderStatusEnum = BeerOrderStatusEnum.VALIDATED;
            break;
            case VALIDATION_EXCEPTION: beerOrderStatusEnum = BeerOrderStatusEnum.VALIDATION_EXCEPTION;
            break;
            case ALLOCATED: beerOrderStatusEnum = BeerOrderStatusEnum.ALLOCATED;
            break;
            case ALLOCATION_EXCEPTION: beerOrderStatusEnum = BeerOrderStatusEnum.ALLOCATION_EXCEPTION;
            break;
            case PENDING_INVENTORY: beerOrderStatusEnum = BeerOrderStatusEnum.PENDING_INVENTORY;
            break;
            case PICKED_UP: beerOrderStatusEnum = BeerOrderStatusEnum.PICKED_UP;
            break;
            case DELIVERED: beerOrderStatusEnum = BeerOrderStatusEnum.DELIVERED;
            break;
            case DELIVERY_EXCEPTION: beerOrderStatusEnum = BeerOrderStatusEnum.DELIVERY_EXCEPTION;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + orderStatusEnum );
        }

        return beerOrderStatusEnum;
    }
}
