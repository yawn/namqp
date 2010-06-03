package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method provides a direct access to the messages in a queue
 * using a synchronous dialogue that is designed for specific types of
 * application where synchronous functionality is more important than
 * performance.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class BasicGet extends Basic {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(70);
    protected static final String METHOD_NAME = "get";

    private UnsignedShort ticket;
	private ShortString queue;
	private Bit noAck;

    public BasicGet(ChannelBuffer channelBuffer) {
        this(new UnsignedShort(channelBuffer), new ShortString(channelBuffer), new Bit(channelBuffer, 0));
    }

    public BasicGet(UnsignedShort ticket, ShortString queue, Bit noAck) {
        this.ticket = ticket;
		this.queue = queue;
		this.noAck = noAck;
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

    public Bit getNoAck() {
        return this.noAck;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(ticket, queue, new BitField(noAck));
    }

}
