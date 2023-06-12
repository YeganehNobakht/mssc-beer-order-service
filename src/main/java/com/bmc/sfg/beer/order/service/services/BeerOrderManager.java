package com.bmc.sfg.beer.order.service.services;

import com.bmc.sfg.beer.order.service.domain.BeerOrder;
import com.bmc.sfg.brewery.model.BeerOrderDto;

/**
 * @author Masoumeh Yeganeh
 * @created 30/05/2023
 */
public interface BeerOrderManager {

    BeerOrder newBeerOrder(BeerOrder beerOrder);

    void beerOrderAllocationPassed(BeerOrderDto beerOrderDto);

    void beerOrderAllocationPendingInventory(BeerOrderDto beerOrderDto);

    void beerOrderAllocationFailed(BeerOrderDto beerOrderDto);
}
