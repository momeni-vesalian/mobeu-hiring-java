package com.mobiquityinc.service.solver;

import com.mobiquityinc.model.KnapsackProblem;
import com.mobiquityinc.model.KnapsackResult;

/**
 * You can choose a implementation according to your strategy.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public interface KnapsackProblemSolver {

    KnapsackResult solve(KnapsackProblem problem);

}
