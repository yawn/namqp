package de.eeriedaily.namqp.v08.methods;

import de.eeriedaily.namqp.v08.types.Type;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public interface Property {

    public Class<? extends Type> getValueType();

}
