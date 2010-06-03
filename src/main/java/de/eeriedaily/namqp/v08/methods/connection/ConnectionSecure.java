package de.eeriedaily.namqp.v08.methods.connection;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * The SASL protocol works by exchanging challenges and responses until
 * both peers have received sufficient information to authenticate each
 * other. This method challenges the client to provide more information.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ConnectionSecure extends Connection {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(20);
    protected static final String METHOD_NAME = "secure";

    private LongString challenge;

    public ConnectionSecure(ChannelBuffer channelBuffer) {
        this(new LongString(channelBuffer));
    }

    public ConnectionSecure(LongString challenge) {
        this.challenge = challenge;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public LongString getChallenge() {
        return this.challenge;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(challenge);
    }

}
