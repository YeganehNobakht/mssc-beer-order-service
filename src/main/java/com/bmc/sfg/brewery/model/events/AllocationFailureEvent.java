package com.bmc.sfg.brewery.model.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Masoumeh Yeganeh
 * @created 12/06/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocationFailureEvent {
    private UUID orderId;
}