package com.mobiquityinc.service.reader;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.KnapsackProblem;

import java.util.stream.Stream;

/**
 * All the implementations of this interface should connect to a data source, and read the knapsack problems as a stream.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public interface KnapsackProblemReader {

    Stream<KnapsackProblem> read(String uri) throws APIException;

}
