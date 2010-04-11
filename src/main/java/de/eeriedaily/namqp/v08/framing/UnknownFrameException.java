package de.eeriedaily.namqp.v08.framing;

import de.eeriedaily.namqp.v08.ClientException;
import de.eeriedaily.namqp.v08.types.Octet;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class UnknownFrameException extends ClientException {

    public UnknownFrameException(Octet frameId) {
        super(String.format("Unknown / unsupported frame id '%d'", frameId.getInt()));
    }

}
