package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * The server provides the client with a consumer tag, which is used
 * by the client for methods called on the consumer at a later stage.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class BasicConsumeOk extends Basic {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(21);
    protected static final String METHOD_NAME = "consume-ok";

    private ShortString consumerTag;

    public BasicConsumeOk(ChannelBuffer channelBuffer) {
        this(new ShortString(channelBuffer));
    }

    public BasicConsumeOk(ShortString consumerTag) {
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
