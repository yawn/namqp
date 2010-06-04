package de.eeriedaily.namqp.v08;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public interface Transmittable {

    public int getSize();

    public void writeTo(ChannelBuffer channelBuffer);

}
