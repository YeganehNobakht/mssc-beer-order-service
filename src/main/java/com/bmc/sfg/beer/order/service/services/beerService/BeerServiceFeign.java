package com.bmc.sfg.beer.order.service.services.beerService;

import com.bmc.sfg.brewery.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Masoumeh Yeganeh
 * @created 27/06/2023
 */
@Profile("local-discovery")
@RequiredArgsConstructor
@Slf4j
@Service
public class BeerServiceFeign implements BeerService{

    private final BeerServiceFeignClient beerServiceFeignClient;

    @Override
    public Optional<BeerDto> getBeerById(UUID uuid) {
        ResponseEntity<BeerDto> beerDtoResoponseEntity = beerServiceFeignClient.getBeerDtoById(uuid);
        BeerDto body = beerDtoResoponseEntity.getBody();
        return Optional.ofNullable(body);
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
        ResponseEntity<BeerDto> beerDtoResoponseEntity = beerServiceFeignClient.getBeerDtoByUpc(upc);
        BeerDto body = beerDtoResoponseEntity.getBody();
        return Optional.ofNullable(body);
    }
}
