package de.eeriedaily.namqp.v08.methods;

import de.eeriedaily.namqp.v08.methods.connection.*;
import de.eeriedaily.namqp.v08.methods.channel.*;
import de.eeriedaily.namqp.v08.methods.exchange.*;
import de.eeriedaily.namqp.v08.methods.queue.*;
import de.eeriedaily.namqp.v08.methods.basic.*;
import de.eeriedaily.namqp.v08.methods.tx.*;
import de.eeriedaily.namqp.v08.types.UnsignedShort;
import org.jboss.netty.buffer.ChannelBuffer;

import javax.annotation.Generated;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class MethodFactory {

    private MethodFactory() {
    }

    public static Method get(UnsignedShort classId, UnsignedShort methodId, ChannelBuffer channelBuffer) {

        if (classId.equals(Connection.CLASS_ID)) {        

            if (methodId.equals(ConnectionStart.METHOD_ID))
                return new ConnectionStart(channelBuffer);

            if (methodId.equals(ConnectionSecure.METHOD_ID))
                return new ConnectionSecure(channelBuffer);

            if (methodId.equals(ConnectionTune.METHOD_ID))
                return new ConnectionTune(channelBuffer);

            if (methodId.equals(ConnectionOpenOk.METHOD_ID))
                return new ConnectionOpenOk(channelBuffer);

            if (methodId.equals(ConnectionRedirect.METHOD_ID))
                return new ConnectionRedirect(channelBuffer);

            if (methodId.equals(ConnectionClose.METHOD_ID))
                return new ConnectionClose(channelBuffer);

            if (methodId.equals(ConnectionCloseOk.METHOD_ID))
                return new ConnectionCloseOk(channelBuffer);

			throw new UnknownMethodException(classId, methodId);

        } else if (classId.equals(Channel.CLASS_ID)) {        

            if (methodId.equals(ChannelOpenOk.METHOD_ID))
                return new ChannelOpenOk(channelBuffer);

            if (methodId.equals(ChannelFlow.METHOD_ID))
                return new ChannelFlow(channelBuffer);

            if (methodId.equals(ChannelFlowOk.METHOD_ID))
                return new ChannelFlowOk(channelBuffer);

            if (methodId.equals(ChannelClose.METHOD_ID))
                return new ChannelClose(channelBuffer);

            if (methodId.equals(ChannelCloseOk.METHOD_ID))
                return new ChannelCloseOk(channelBuffer);

			throw new UnknownMethodException(classId, methodId);

        } else if (classId.equals(Exchange.CLASS_ID)) {        

            if (methodId.equals(ExchangeDeclareOk.METHOD_ID))
                return new ExchangeDeclareOk(channelBuffer);

            if (methodId.equals(ExchangeDeleteOk.METHOD_ID))
                return new ExchangeDeleteOk(channelBuffer);

			throw new UnknownMethodException(classId, methodId);

        } else if (classId.equals(Queue.CLASS_ID)) {        

            if (methodId.equals(QueueDeclareOk.METHOD_ID))
                return new QueueDeclareOk(channelBuffer);

            if (methodId.equals(QueueBindOk.METHOD_ID))
                return new QueueBindOk(channelBuffer);

            if (methodId.equals(QueuePurgeOk.METHOD_ID))
                return new QueuePurgeOk(channelBuffer);

            if (methodId.equals(QueueDeleteOk.METHOD_ID))
                return new QueueDeleteOk(channelBuffer);

			throw new UnknownMethodException(classId, methodId);

        } else if (classId.equals(Basic.CLASS_ID)) {        

            if (methodId.equals(BasicQosOk.METHOD_ID))
                return new BasicQosOk(channelBuffer);

            if (methodId.equals(BasicConsumeOk.METHOD_ID))
                return new BasicConsumeOk(channelBuffer);

            if (methodId.equals(BasicCancelOk.METHOD_ID))
                return new BasicCancelOk(channelBuffer);

            if (methodId.equals(BasicReturn.METHOD_ID))
                return new BasicReturn(channelBuffer);

            if (methodId.equals(BasicDeliver.METHOD_ID))
                return new BasicDeliver(channelBuffer);

            if (methodId.equals(BasicGetOk.METHOD_ID))
                return new BasicGetOk(channelBuffer);

            if (methodId.equals(BasicGetEmpty.METHOD_ID))
                return new BasicGetEmpty(channelBuffer);

			throw new UnknownMethodException(classId, methodId);

        } else if (classId.equals(Tx.CLASS_ID)) {        

            if (methodId.equals(TxSelectOk.METHOD_ID))
                return new TxSelectOk(channelBuffer);

            if (methodId.equals(TxCommitOk.METHOD_ID))
                return new TxCommitOk(channelBuffer);

            if (methodId.equals(TxRollbackOk.METHOD_ID))
                return new TxRollbackOk(channelBuffer);

			throw new UnknownMethodException(classId, methodId);

        } else
            throw new UnknownClassException(classId);
        
    }

}  
