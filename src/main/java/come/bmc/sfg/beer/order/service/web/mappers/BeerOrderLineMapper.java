package come.bmc.sfg.beer.order.service.web.mappers;

import come.bmc.sfg.beer.order.service.domain.BeerOrderLine;
import org.mapstruct.DecoratedWith;
import come.bmc.sfg.brewery.model.BeerOrderLineDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerOrderLineMapperDecorator.class)
public interface BeerOrderLineMapper {

    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
