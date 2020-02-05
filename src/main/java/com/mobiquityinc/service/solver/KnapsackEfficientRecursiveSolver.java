package com.mobiquityinc.service.solver;

import com.mobiquityinc.model.KnapsackItem;
import com.mobiquityinc.model.KnapsackProblem;
import com.mobiquityinc.model.KnapsackResult;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

/**
 * This class is an efficient implementation of {@link KnapsackProblemSolver KnapsackSolver}
 * which works according to dynamic programing algorithm recursively.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public class KnapsackEfficientRecursiveSolver implements KnapsackProblemSolver {

    @Override
    public KnapsackResult solve(KnapsackProblem problem) {
        final BigDecimal weightLimit = problem.weightLimit();
        final List<KnapsackItem> allItems = problem.items();
        final int itemsSize = allItems.size();
        if ((weightLimit.compareTo(ZERO) <= 0) || (itemsSize <= 0)) {
            return KnapsackResult.empty();
        }

        final KnapsackItem currentItem = allItems.get(itemsSize - 1);
        final List<KnapsackItem> previousItems = allItems.subList(0, itemsSize - 1);

        KnapsackProblem previousProblem = KnapsackProblem.builder()
                .weightLimit(weightLimit)
                .items(previousItems)
                .build();
        KnapsackProblem remainedProblem = KnapsackProblem.builder()
                .weightLimit(weightLimit.subtract(currentItem.weight()))
                .items(previousItems)
                .build();

        final KnapsackResult previousResult = solve(previousProblem);
        final KnapsackResult currentResult = solve(remainedProblem).clone().addItem(currentItem);

        return max(previousResult, currentResult, weightLimit);
    }

    private KnapsackResult max(KnapsackResult first, KnapsackResult second, BigDecimal weightLimit) {
        if (second.weight().compareTo(weightLimit) > 0) {
            return first.weight().compareTo(weightLimit) > 0 ? KnapsackResult.empty() : first;
        }
        return (first.weight().compareTo(weightLimit) > 0) || (first.compareTo(second) < 0) ? second : first;
    }

}
