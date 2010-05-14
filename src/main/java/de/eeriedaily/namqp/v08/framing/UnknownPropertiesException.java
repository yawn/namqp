package de.eeriedaily.namqp.v08.framing;

import de.eeriedaily.namqp.v08.ClientException;
import de.eeriedaily.namqp.v08.types.UnsignedShort;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class UnknownPropertiesException extends ClientException {

    public UnknownPropertiesException(UnsignedShort classId) {
        super(String.format("Unknown / unsupported properties for class id '%s'", classId));
    }

}
