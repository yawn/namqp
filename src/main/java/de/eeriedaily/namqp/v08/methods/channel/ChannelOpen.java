package de.eeriedaily.namqp.v08.methods.channel;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method opens a virtual connection (a channel).
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ChannelOpen extends Channel {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(10);
    protected static final String METHOD_NAME = "open";

    private ShortString outOfBand;

    public ChannelOpen(ChannelBuffer channelBuffer) {
        this(new ShortString(channelBuffer));
    }

    public ChannelOpen(ShortString outOfBand) {
        this.outOfBand = outOfBand;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public ShortString getOutOfBand() {
        return this.outOfBand;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(outOfBand);
    }

}
