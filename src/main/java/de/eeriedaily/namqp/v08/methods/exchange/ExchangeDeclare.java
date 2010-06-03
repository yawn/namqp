package de.eeriedaily.namqp.v08.methods.exchange;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method creates an exchange if it does not already exist, and if the
 * exchange exists, verifies that it is of the correct and expected class.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ExchangeDeclare extends Exchange {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(10);
    protected static final String METHOD_NAME = "declare";

    private UnsignedShort ticket;
	private ShortString exchange;
	private ShortString type;
	private Bit passive;
	private Bit durable;
	private Bit autoDelete;
	private Bit internal;
	private Bit nowait;
	private FieldTable arguments;

    public ExchangeDeclare(ChannelBuffer channelBuffer) {
        this(new UnsignedShort(channelBuffer), new ShortString(channelBuffer), new ShortString(channelBuffer), new Bit(channelBuffer, 0), new Bit(channelBuffer, 1), new Bit(channelBuffer, 2), new Bit(channelBuffer, 3), new Bit(channelBuffer, 4), new FieldTable(channelBuffer));
    }

    public ExchangeDeclare(UnsignedShort ticket, ShortString exchange, ShortString type, Bit passive, Bit durable, Bit autoDelete, Bit internal, Bit nowait, FieldTable arguments) {
        this.ticket = ticket;
		this.exchange = exchange;
		this.type = type;
		this.passive = passive;
		this.durable = durable;
		this.autoDelete = autoDelete;
		this.internal = internal;
		this.nowait = nowait;
		this.arguments = arguments;
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

    public ShortString getType() {
        return this.type;
    }    

    public Bit getPassive() {
        return this.passive;
    }    

    public Bit getDurable() {
        return this.durable;
    }    

    public Bit getAutoDelete() {
        return this.autoDelete;
    }    

    public Bit getInternal() {
        return this.internal;
    }    

    public Bit getNowait() {
        return this.nowait;
    }    

    public FieldTable getArguments() {
        return this.arguments;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(ticket, exchange, type, new BitField(passive, durable, autoDelete, internal, nowait), arguments);
    }

}
