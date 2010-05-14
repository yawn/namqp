package de.eeriedaily.namqp.v08.methods;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.UnsignedShort;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public interface Method extends Transmittable {

    public UnsignedShort getMethodClassId();

    public UnsignedShort getMethodMethodId();

    public String getMethodClassAndMethodName();

}
