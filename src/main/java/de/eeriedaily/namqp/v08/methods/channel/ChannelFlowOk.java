package de.eeriedaily.namqp.v08.methods.channel;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * Confirms to the peer that a flow command was received and processed.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ChannelFlowOk extends Channel {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(21);
    protected static final String METHOD_NAME = "flow-ok";

    private Bit active;

    public ChannelFlowOk(ChannelBuffer channelBuffer) {
        this(new Bit(channelBuffer, 0));
    }

    public ChannelFlowOk(Bit active) {
        this.active = active;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public Bit getActive() {
        return this.active;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(new BitField(active));
    }

}
