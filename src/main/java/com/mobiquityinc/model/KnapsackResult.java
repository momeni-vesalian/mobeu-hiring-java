package com.mobiquityinc.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This data structure is used to hold the result of Knapsack problem.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
@Getter
@Accessors(fluent = true)
@EqualsAndHashCode
public class KnapsackResult implements Cloneable, Comparable<KnapsackResult> {

    private static final KnapsackResult EMPTY = new KnapsackResult();

    private BigDecimal weight = BigDecimal.ZERO;
    private Integer cost = 0;
    private List<KnapsackItem> items = new ArrayList<>();

    private KnapsackResult() {
    }

    public static KnapsackResult empty() {
        return EMPTY;
    }

    public KnapsackResult addItem(KnapsackItem item) {
        this.weight = this.weight.add(item.weight());
        this.cost += item.cost();
        this.items.add(item);
        return this;
    }

    @Override
    public KnapsackResult clone() {
        KnapsackResult clone = new KnapsackResult();
        clone.weight = this.weight;
        clone.cost = this.cost;
        clone.items = new ArrayList<>(this.items);
        return clone;
    }

    @Override
    public int compareTo(KnapsackResult other) {
        if (other == null) {
            return 1;
        }
        if (this.cost.compareTo(other.cost) == 0) {
            return other.weight.compareTo(this.weight);
        }
        return this.cost.compareTo(other.cost);
    }

    @Override
    public String toString() {
        return items.stream()
                .map(KnapsackItem::index)
                .map(Object::toString)
                .reduce((first, second) -> String.format("%s, %s", first, second))
                .orElse("-");
    }

}
