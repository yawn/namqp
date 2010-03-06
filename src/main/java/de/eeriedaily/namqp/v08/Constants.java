package de.eeriedaily.namqp.v08;

import de.eeriedaily.namqp.v08.types.Octet;
import de.eeriedaily.namqp.v08.types.UnsignedShort;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import static org.jboss.netty.buffer.ChannelBuffers.unmodifiableBuffer;
import static org.jboss.netty.buffer.ChannelBuffers.wrappedBuffer;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public final class Constants {

    public static final ChannelBuffer HEADER = unmodifiableBuffer(wrappedBuffer(new byte[] { 'A', 'M', 'Q', 'P' }));
    public static final ChannelBuffer VERSION = unmodifiableBuffer(wrappedBuffer(new byte[] { 1, 1, 8, 0 }));
    public static final ChannelBuffer PROTOCOL_HEADER = unmodifiableBuffer(wrappedBuffer(HEADER, VERSION));

    public static final Octet EOF = new Octet(206);

    public static final UnsignedShort ADMIN_CHANNEL = new UnsignedShort(0);

    private Constants() {
    }

}
