package de.eeriedaily.namqp.v08.types;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * A read-only type. For writing bits see BitField.
 *
 * @see BitField
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class Bit {

    private final boolean bit;

    public Bit(boolean bit) {
        this.bit = bit;
    }

    public Bit(ChannelBuffer channelBuffer, int idx) {

        if (idx > 0) // return to the last byte it the idx ­ 0
            channelBuffer.readerIndex(channelBuffer.readerIndex() - 1);

        this.bit = get(channelBuffer.readByte(), idx);
        
    }

    protected static boolean get(byte b, int idx) {
        assert idx <= 8;
        return (b & (1 << idx)) != 0;
    }

    public boolean isTrue() {
        return bit;
    }

    @Override
    public String toString() {
        return String.valueOf(bit);
    }

}
