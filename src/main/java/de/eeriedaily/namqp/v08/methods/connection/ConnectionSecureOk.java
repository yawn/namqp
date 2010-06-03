package de.eeriedaily.namqp.v08.methods.connection;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method attempts to authenticate, passing a block of SASL data
 * for the security mechanism at the server side.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ConnectionSecureOk extends Connection {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(21);
    protected static final String METHOD_NAME = "secure-ok";

    private LongString response;

    public ConnectionSecureOk(ChannelBuffer channelBuffer) {
        this(new LongString(channelBuffer));
    }

    public ConnectionSecureOk(LongString response) {
        this.response = response;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public LongString getResponse() {
        return this.response;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(response);
    }

}
