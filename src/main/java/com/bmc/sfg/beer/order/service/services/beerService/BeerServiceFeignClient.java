package com.bmc.sfg.beer.order.service.services.beerService;

import com.bmc.sfg.brewery.model.BeerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

/**
 * @author Masoumeh Yeganeh
 * @created 27/06/2023
 */
@FeignClient(name = "beer-service")
public interface BeerServiceFeignClient {

    @GetMapping("/api/v1/beer/{beerId}")
    ResponseEntity<BeerDto> getBeerDtoById(@PathVariable UUID beerId);

    @GetMapping("/api/v1/beerUpc/{upc}")
    ResponseEntity<BeerDto> getBeerDtoByUpc(@PathVariable String upc);
}
