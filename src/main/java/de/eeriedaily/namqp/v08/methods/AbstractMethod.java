package de.eeriedaily.namqp.v08.methods;

import de.eeriedaily.namqp.v08.AbstractTransmittableContainer;

import java.util.Arrays;

/**
 * Base class for AMQP methods.
 *
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public abstract class AbstractMethod extends AbstractTransmittableContainer implements Method {

    /**
     * Returns the name of the methods class, e.g. connection.
     *
     * @return The name.
     */
    protected abstract String getMethodClassName();

    /**
     * Returns the name of the methods method, e.g. start-ok.
     *
     * @return The name.
     */
    protected abstract String getMethodMethodName();

    /**
     * Returns a unique description of this methods by joining class and method name with a dot
     * e.g. connection.start-ok.
     *
     * @return The unique name.
     */
    public final String getMethodClassAndMethodName() {
        return String.format("%s.%s", getMethodClassName(), getMethodMethodName()).intern();
    }

    @Override
    public String toString() {
        return String.format("%s: %s", getMethodClassAndMethodName(),
                Arrays.toString(getTransmittables()));

    }

}
