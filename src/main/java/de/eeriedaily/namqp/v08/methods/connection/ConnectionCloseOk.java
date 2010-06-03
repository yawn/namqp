package de.eeriedaily.namqp.v08.methods.connection;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method confirms a Connection.Close method and tells the
 * recipient that it is safe to release resources for the connection
 * and close the socket.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ConnectionCloseOk extends Connection {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(61);
    protected static final String METHOD_NAME = "close-ok";

    

    public ConnectionCloseOk(ChannelBuffer channelBuffer) {
        this();
    }

    public ConnectionCloseOk() {
        
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }



    @Override
    protected Transmittable[] getTransmittables() {
        return all();
    }

}
