package com.mobiquityinc;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * This class is the start point of the application, and will process the passed parameters.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                throw new APIException("You should pass the file path");
            }

            final String pack = Packer.pack(args[0]);

            log.info(String.format("Output:\r\n%s", pack));
        } catch (Exception e) {
            log.error(Optional.of(e.getMessage()).orElse("Error occurred in executing the packer project!"));
        }
    }

}
