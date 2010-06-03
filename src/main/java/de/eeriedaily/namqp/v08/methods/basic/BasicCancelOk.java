package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method confirms that the cancellation was completed.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class BasicCancelOk extends Basic {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(31);
    protected static final String METHOD_NAME = "cancel-ok";

    private ShortString consumerTag;

    public BasicCancelOk(ChannelBuffer channelBuffer) {
        this(new ShortString(channelBuffer));
    }

    public BasicCancelOk(ShortString consumerTag) {
        this.consumerTag = consumerTag;
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


    @Override
    protected Transmittable[] getTransmittables() {
        return all(consumerTag);
    }

}
