package come.bmc.sfg.brewery.model.events;

import come.bmc.sfg.brewery.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Masoumeh Yeganeh
 * @created 30/05/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocationOrderRequest {
    private BeerOrderDto beerOrderDto;

}
