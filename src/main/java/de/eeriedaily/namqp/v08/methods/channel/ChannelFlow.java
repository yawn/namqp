package de.eeriedaily.namqp.v08.methods.channel;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method asks the peer to pause or restart the flow of content
 * data. This is a simple flow-control mechanism that a peer can use
 * to avoid oveflowing its queues or otherwise finding itself receiving
 * more messages than it can process. Note that this method is not
 * intended for window control. The peer that receives a request to
 * stop sending content should finish sending the current content, if
 * any, and then wait until it receives a Flow restart method.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ChannelFlow extends Channel {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(20);
    protected static final String METHOD_NAME = "flow";

    private Bit active;

    public ChannelFlow(ChannelBuffer channelBuffer) {
        this(new Bit(channelBuffer, 0));
    }

    public ChannelFlow(Bit active) {
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
