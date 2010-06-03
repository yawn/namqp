package de.eeriedaily.namqp.v08.methods.connection;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method signals to the client that the connection is ready for
 * use.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ConnectionOpenOk extends Connection {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(41);
    protected static final String METHOD_NAME = "open-ok";

    private ShortString knownHosts;

    public ConnectionOpenOk(ChannelBuffer channelBuffer) {
        this(new ShortString(channelBuffer));
    }

    public ConnectionOpenOk(ShortString knownHosts) {
        this.knownHosts = knownHosts;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public ShortString getKnownHosts() {
        return this.knownHosts;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(knownHosts);
    }

}
