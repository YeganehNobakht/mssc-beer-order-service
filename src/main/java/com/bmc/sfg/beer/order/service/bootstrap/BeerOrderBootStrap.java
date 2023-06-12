package com.bmc.sfg.beer.order.service.bootstrap;

import com.bmc.sfg.beer.order.service.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import com.bmc.sfg.beer.order.service.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;



@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderBootStrap implements CommandLineRunner {
    public static final String TASTING_ROOM = "Tasting Room";
    public static final String BEER_1_UPC = "063124200036";
    public static final String BEER_2_UPC = "063124300019";
    public static final String BEER_3_UPC = "083783375213";

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCustomerData();
    }

    private void loadCustomerData() {
        if (customerRepository.count() == 0) {
            Customer savedCustomer = customerRepository.save(Customer.builder()
                    .customerName(TASTING_ROOM)
                    .apiKey(UUID.randomUUID())
                    .build());

            log.debug("Testing Room Customer Id: " + savedCustomer.getId().toString());
            System.out.println("Testing Room Customer Id: " + savedCustomer.getId().toString());
        }
    }
}
