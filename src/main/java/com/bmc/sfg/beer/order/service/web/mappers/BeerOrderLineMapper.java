package com.bmc.sfg.beer.order.service.web.mappers;

import com.bmc.sfg.beer.order.service.domain.BeerOrderLine;
import org.mapstruct.DecoratedWith;
import com.bmc.sfg.brewery.model.BeerOrderLineDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerOrderLineMapperDecorator.class)
public interface BeerOrderLineMapper {

    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
