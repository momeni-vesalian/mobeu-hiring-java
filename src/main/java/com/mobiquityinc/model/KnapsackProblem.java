package com.mobiquityinc.model;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * This class is used to raise the backpack problem.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
@Value
@Accessors(fluent = true)
public class KnapsackProblem {

    /**
     * According to the constraints, maximum package wight is 100
     */
    private static final BigDecimal MAXIMUM_WEIGHT = BigDecimal.valueOf(100);

    private BigDecimal weightLimit;
    private List<KnapsackItem> items;

    @Builder
    public KnapsackProblem(BigDecimal weightLimit, List<KnapsackItem> items) {
        this.items = items;
        this.weightLimit = Optional.of(weightLimit)
                .filter(w -> MAXIMUM_WEIGHT.compareTo(w) >= 0)
                .orElseThrow(() -> new IllegalArgumentException("Maximum package wight is 100!"));
    }

}
