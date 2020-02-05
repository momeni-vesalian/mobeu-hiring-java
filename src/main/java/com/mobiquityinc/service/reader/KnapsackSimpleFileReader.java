package com.mobiquityinc.service.reader;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.FileFormatException;
import com.mobiquityinc.model.KnapsackItem;
import com.mobiquityinc.model.KnapsackProblem;
import com.mobiquityinc.util.KnapsackRegexUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import static com.mobiquityinc.util.KnapsackRegexUtils.ITEM_PATTERN;
import static com.mobiquityinc.util.KnapsackRegexUtils.LINE_PATTERN;

/**
 * This class is a simple file reader which will parse the file according to this {@link KnapsackRegexUtils#LINE_REGEX pattern}
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public class KnapsackSimpleFileReader implements KnapsackProblemReader {

    @Override
    public Stream<KnapsackProblem> read(String path) throws APIException {
        try {
            return Files.lines(Paths.get(path))
                    .filter(StringUtils::isNotBlank)
                    .map(this::parseLine);
        } catch (IOException e) {
            throw new APIException("Error occurred in reading the file", e);
        }
    }

    private KnapsackProblem parseLine(String line) {
        validateLine(line);
        final String[] lineSections = line.split(":");
        return KnapsackProblem.builder()
                .weightLimit(new BigDecimal(lineSections[0].trim()))
                .items(parseItems(lineSections[1]))
                .build();
    }

    private void validateLine(String line) {
        final Matcher matcher = LINE_PATTERN.matcher(line);
        if (!matcher.find()) {
            throw new FileFormatException(String.format("Unknown line format: '%s'", line));
        }
    }

    private List<KnapsackItem> parseItems(String itemsSection) {
        final List<KnapsackItem> items = new ArrayList<>();
        final Matcher matcher = ITEM_PATTERN.matcher(itemsSection);
        while (matcher.find()) {
            items.add(parseItem(matcher.group(1)));
        }
        return items;
    }

    private KnapsackItem parseItem(String itemSection) {
        final String[] itemSections = itemSection.split(",");
        return KnapsackItem.builder()
                .index(Integer.valueOf(itemSections[0]))
                .weight(new BigDecimal(itemSections[1].trim()))
                .cost(Integer.valueOf(itemSections[2].substring(1)))
                .build();
    }

}
