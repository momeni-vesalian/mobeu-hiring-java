package com.mobiquityinc.model;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * This class contains all the attributes of an item which you might choose.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
@Value
@Accessors(fluent = true)
public class KnapsackItem {

    /**
     * According to the constraints, maximum item wight is 100
     */
    private static final BigDecimal MAXIMUM_WEIGHT = BigDecimal.valueOf(100);

    private Integer index;
    private BigDecimal weight;
    private Integer cost;

    @Builder
    public KnapsackItem(Integer index, BigDecimal weight, Integer cost) {
        this.index = index;
        this.cost = cost;
        this.weight = Optional.of(weight)
                .filter(w -> MAXIMUM_WEIGHT.compareTo(w) >= 0)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Index '%d': Maximum wight is 100!", index)));
    }

}
