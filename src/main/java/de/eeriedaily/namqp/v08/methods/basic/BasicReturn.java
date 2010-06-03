package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method returns an undeliverable message that was published
 * with the "immediate" flag set, or an unroutable message published
 * with the "mandatory" flag set. The reply code and text provide
 * information about the reason that the message was undeliverable.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class BasicReturn extends Basic {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(50);
    protected static final String METHOD_NAME = "return";

    private UnsignedShort replyCode;
	private ShortString replyText;
	private ShortString exchange;
	private ShortString routingKey;

    public BasicReturn(ChannelBuffer channelBuffer) {
        this(new UnsignedShort(channelBuffer), new ShortString(channelBuffer), new ShortString(channelBuffer), new ShortString(channelBuffer));
    }

    public BasicReturn(UnsignedShort replyCode, ShortString replyText, ShortString exchange, ShortString routingKey) {
        this.replyCode = replyCode;
		this.replyText = replyText;
		this.exchange = exchange;
		this.routingKey = routingKey;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public UnsignedShort getReplyCode() {
        return this.replyCode;
    }    

    public ShortString getReplyText() {
        return this.replyText;
    }    

    public ShortString getExchange() {
        return this.exchange;
    }    

    public ShortString getRoutingKey() {
        return this.routingKey;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(replyCode, replyText, exchange, routingKey);
    }

}
