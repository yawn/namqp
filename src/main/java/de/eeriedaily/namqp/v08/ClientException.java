package de.eeriedaily.namqp.v08;

import java.util.Arrays;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public abstract class ClientException extends AMQPException {

    protected ClientException() {
    }

    protected ClientException(Throwable cause) {
        super(cause);
    }

    protected ClientException(String message) {
        super(message);
    }

    protected ClientException(String message, Throwable cause) {
        super(message, cause);
    }

}
