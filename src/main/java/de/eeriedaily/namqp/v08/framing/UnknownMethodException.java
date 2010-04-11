package de.eeriedaily.namqp.v08.framing;

import de.eeriedaily.namqp.v08.ClientException;
import de.eeriedaily.namqp.v08.types.UnsignedShort;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class UnknownMethodException extends ClientException {

    public UnknownMethodException(UnsignedShort classId, UnsignedShort methodId) {
        super(String.format("Unknown / unsupported method id '%s' for class '%s'", methodId, classId));
    }

}
