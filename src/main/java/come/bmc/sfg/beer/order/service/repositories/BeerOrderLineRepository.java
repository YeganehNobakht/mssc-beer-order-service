package come.bmc.sfg.beer.order.service.repositories;

import come.bmc.sfg.beer.order.service.domain.BeerOrderLine;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerOrderLineRepository extends PagingAndSortingRepository<BeerOrderLine, UUID> {
}
