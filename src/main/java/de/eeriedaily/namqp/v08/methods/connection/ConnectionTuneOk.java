package de.eeriedaily.namqp.v08.methods.connection;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method sends the client's connection tuning parameters to the
 * server. Certain fields are negotiated, others provide capability
 * information.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ConnectionTuneOk extends Connection {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(31);
    protected static final String METHOD_NAME = "tune-ok";

    private UnsignedShort channelMax;
	private UnsignedLong frameMax;
	private UnsignedShort heartbeat;

    public ConnectionTuneOk(ChannelBuffer channelBuffer) {
        this(new UnsignedShort(channelBuffer), new UnsignedLong(channelBuffer), new UnsignedShort(channelBuffer));
    }

    public ConnectionTuneOk(UnsignedShort channelMax, UnsignedLong frameMax, UnsignedShort heartbeat) {
        this.channelMax = channelMax;
		this.frameMax = frameMax;
		this.heartbeat = heartbeat;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public UnsignedShort getChannelMax() {
        return this.channelMax;
    }    

    public UnsignedLong getFrameMax() {
        return this.frameMax;
    }    

    public UnsignedShort getHeartbeat() {
        return this.heartbeat;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(channelMax, frameMax, heartbeat);
    }

}
