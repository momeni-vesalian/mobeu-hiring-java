package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.KnapsackResult;
import com.mobiquityinc.service.reader.KnapsackProblemReader;
import com.mobiquityinc.service.reader.KnapsackSimpleFileReader;
import com.mobiquityinc.service.solver.KnapsackEfficientRecursiveSolver;
import com.mobiquityinc.service.solver.KnapsackProblemSolver;

public final class Packer {

    private Packer() {
    }

    public static String pack(String filePath) throws APIException {
        final KnapsackProblemReader reader = new KnapsackSimpleFileReader();
        final KnapsackProblemSolver solver = new KnapsackEfficientRecursiveSolver();
        try {
            return reader.read(filePath)
                    .map(solver::solve)
                    .map(KnapsackResult::toString)
                    .reduce((first, second) -> String.format("%s\r\n%s", first, second))
                    .orElse("-");
        } catch (IllegalArgumentException e) {
            throw new APIException("Error occurred in reading the file", e);
        }
    }

}
