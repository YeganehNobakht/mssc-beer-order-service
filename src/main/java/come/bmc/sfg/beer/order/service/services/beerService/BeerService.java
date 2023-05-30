package come.bmc.sfg.beer.order.service.services.beerService;

import come.bmc.sfg.brewery.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Masoumeh Yeganeh
 * @created 15/05/2023
 */
public interface BeerService {

    Optional<BeerDto> getBeerById(UUID uuid);

    Optional<BeerDto> getBeerByUpc(String upc);
}
