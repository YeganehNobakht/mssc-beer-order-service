package sfg.beer.order.service.web.mappers;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sfg.beer.order.service.domain.BeerOrderLine;
import sfg.beer.order.service.domain.BeerOrderLine.BeerOrderLineBuilder;
import sfg.beer.order.service.web.model.BeerOrderLineDto;
import sfg.beer.order.service.web.model.BeerOrderLineDto.BeerOrderLineDtoBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-20T18:16:21+0330",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class BeerOrderLineMapperImpl_ implements BeerOrderLineMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        if ( line == null ) {
            return null;
        }

        BeerOrderLineDtoBuilder beerOrderLineDto = BeerOrderLineDto.builder();

        beerOrderLineDto.id( line.getId() );
        if ( line.getVersion() != null ) {
            beerOrderLineDto.version( line.getVersion().intValue() );
        }
        beerOrderLineDto.createdDate( dateMapper.asOffsetDateTime( line.getCreatedDate() ) );
        beerOrderLineDto.lastModifiedDate( dateMapper.asOffsetDateTime( line.getLastModifiedDate() ) );
        beerOrderLineDto.upc( line.getUpc() );
        beerOrderLineDto.beerId( line.getBeerId() );
        beerOrderLineDto.orderQuantity( line.getOrderQuantity() );

        return beerOrderLineDto.build();
    }

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
        if ( dto == null ) {
            return null;
        }

        BeerOrderLineBuilder beerOrderLine = BeerOrderLine.builder();

        beerOrderLine.id( dto.getId() );
        if ( dto.getVersion() != null ) {
            beerOrderLine.version( dto.getVersion().longValue() );
        }
        beerOrderLine.createdDate( dateMapper.asTimestamp( dto.getCreatedDate() ) );
        beerOrderLine.lastModifiedDate( dateMapper.asTimestamp( dto.getLastModifiedDate() ) );
        beerOrderLine.beerId( dto.getBeerId() );
        beerOrderLine.upc( dto.getUpc() );
        beerOrderLine.orderQuantity( dto.getOrderQuantity() );

        return beerOrderLine.build();
    }
}
