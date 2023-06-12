package com.bmc.sfg.beer.order.service.domain;

/**
 * @author Masoumeh Yeganeh
 * @created 30/05/2023
 */
public enum BeerOrderEventEnum {
    VALIDATE_ORDER, VALIDATION_PASSED, VALIDATION_FAILED,
    ALLOCATE_ORDER, ALLOCATION_SUCCESS, ALLOCATION_NO_INVENTORY, ALLOCATION_FAILED,
    BEERORDER_PICKED_UP

}
