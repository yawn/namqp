package de.eeriedaily.namqp.v08.methods.channel;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method indicates that the sender wants to close the channel.
 * This may be due to internal conditions (e.g. a forced shut-down) or
 * due to an error handling a specific method, i.e. an exception. When
 * a close is due to an exception, the sender provides the class and
 * method id of the method which caused the exception.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ChannelClose extends Channel {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(40);
    protected static final String METHOD_NAME = "close";

    private UnsignedShort replyCode;
	private ShortString replyText;
	private UnsignedShort classId;
	private UnsignedShort methodId;

    public ChannelClose(ChannelBuffer channelBuffer) {
        this(new UnsignedShort(channelBuffer), new ShortString(channelBuffer), new UnsignedShort(channelBuffer), new UnsignedShort(channelBuffer));
    }

    public ChannelClose(UnsignedShort replyCode, ShortString replyText, UnsignedShort classId, UnsignedShort methodId) {
        this.replyCode = replyCode;
		this.replyText = replyText;
		this.classId = classId;
		this.methodId = methodId;
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

    public UnsignedShort getClassId() {
        return this.classId;
    }    

    public UnsignedShort getMethodId() {
        return this.methodId;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(replyCode, replyText, classId, methodId);
    }

}
