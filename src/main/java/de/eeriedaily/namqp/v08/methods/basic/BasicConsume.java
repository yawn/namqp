package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method asks the server to start a "consumer", which is a
 * transient request for messages from a specific queue. Consumers
 * last as long as the channel they were created on, or until the
 * client cancels them.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class BasicConsume extends Basic {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(20);
    protected static final String METHOD_NAME = "consume";

    private UnsignedShort ticket;
	private ShortString queue;
	private ShortString consumerTag;
	private Bit noLocal;
	private Bit noAck;
	private Bit exclusive;
	private Bit nowait;

    public BasicConsume(ChannelBuffer channelBuffer) {
        this(new UnsignedShort(channelBuffer), new ShortString(channelBuffer), new ShortString(channelBuffer), new Bit(channelBuffer, 0), new Bit(channelBuffer, 1), new Bit(channelBuffer, 2), new Bit(channelBuffer, 3));
    }

    public BasicConsume(UnsignedShort ticket, ShortString queue, ShortString consumerTag, Bit noLocal, Bit noAck, Bit exclusive, Bit nowait) {
        this.ticket = ticket;
		this.queue = queue;
		this.consumerTag = consumerTag;
		this.noLocal = noLocal;
		this.noAck = noAck;
		this.exclusive = exclusive;
		this.nowait = nowait;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public UnsignedShort getTicket() {
        return this.ticket;
    }    

    public ShortString getQueue() {
        return this.queue;
    }    

    public ShortString getConsumerTag() {
        return this.consumerTag;
    }    

    public Bit getNoLocal() {
        return this.noLocal;
    }    

    public Bit getNoAck() {
        return this.noAck;
    }    

    public Bit getExclusive() {
        return this.exclusive;
    }    

    public Bit getNowait() {
        return this.nowait;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(ticket, queue, consumerTag, new BitField(noLocal, noAck, exclusive, nowait));
    }

}
