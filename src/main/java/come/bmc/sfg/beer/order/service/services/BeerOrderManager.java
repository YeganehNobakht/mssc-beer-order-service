package come.bmc.sfg.beer.order.service.services;

import come.bmc.sfg.beer.order.service.domain.BeerOrder;

/**
 * @author Masoumeh Yeganeh
 * @created 30/05/2023
 */
public interface BeerOrderManager {

    BeerOrder newBeerOrder(BeerOrder beerOrder);
}