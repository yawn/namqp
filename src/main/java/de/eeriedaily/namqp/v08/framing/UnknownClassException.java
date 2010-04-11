package de.eeriedaily.namqp.v08.framing;

import de.eeriedaily.namqp.v08.AMQPException;
import de.eeriedaily.namqp.v08.ClientException;
import de.eeriedaily.namqp.v08.types.UnsignedShort;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class UnknownClassException extends ClientException {

    public UnknownClassException(UnsignedShort classId) {
        super(String.format("Unknown / unsupported class id '%s'", classId));
    }

}
