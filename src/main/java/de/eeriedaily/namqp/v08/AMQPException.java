package de.eeriedaily.namqp.v08;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public abstract class AMQPException extends RuntimeException {

    protected AMQPException() {
    }

    protected AMQPException(Throwable cause) {
        super(cause);
    }

    protected AMQPException(String message) {
        super(message);
    }

    protected AMQPException(String message, Throwable cause) {
        super(message, cause);
    }

}
