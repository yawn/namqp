package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**

 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class BasicCancel extends Basic {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(30);
    protected static final String METHOD_NAME = "cancel";

    private ShortString consumerTag;
	private Bit nowait;

    public BasicCancel(ChannelBuffer channelBuffer) {
        this(new ShortString(channelBuffer), new Bit(channelBuffer, 0));
    }

    public BasicCancel(ShortString consumerTag, Bit nowait) {
        this.consumerTag = consumerTag;
		this.nowait = nowait;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public ShortString getConsumerTag() {
        return this.consumerTag;
    }    

    public Bit getNowait() {
        return this.nowait;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(consumerTag, new BitField(nowait));
    }

}
