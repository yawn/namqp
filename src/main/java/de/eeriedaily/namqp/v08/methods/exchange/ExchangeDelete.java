package de.eeriedaily.namqp.v08.methods.exchange;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method deletes an exchange. When an exchange is deleted all queue
 * bindings on the exchange are cancelled.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ExchangeDelete extends Exchange {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(20);
    protected static final String METHOD_NAME = "delete";

    private UnsignedShort ticket;
	private ShortString exchange;
	private Bit ifUnused;
	private Bit nowait;

    public ExchangeDelete(ChannelBuffer channelBuffer) {
        this(new UnsignedShort(channelBuffer), new ShortString(channelBuffer), new Bit(channelBuffer, 0), new Bit(channelBuffer, 1));
    }

    public ExchangeDelete(UnsignedShort ticket, ShortString exchange, Bit ifUnused, Bit nowait) {
        this.ticket = ticket;
		this.exchange = exchange;
		this.ifUnused = ifUnused;
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

    public ShortString getExchange() {
        return this.exchange;
    }    

    public Bit getIfUnused() {
        return this.ifUnused;
    }    

    public Bit getNowait() {
        return this.nowait;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(ticket, exchange, new BitField(ifUnused, nowait));
    }

}
