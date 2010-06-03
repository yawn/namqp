package de.eeriedaily.namqp.v08;

import de.eeriedaily.namqp.v08.framing.MethodBody;
import de.eeriedaily.namqp.v08.handler.channel.Channel;
import de.eeriedaily.namqp.v08.handler.channel.Content;
import de.eeriedaily.namqp.v08.handler.channel.ContentListener;
import de.eeriedaily.namqp.v08.methods.Method;
import de.eeriedaily.namqp.v08.methods.basic.BasicConsume;
import de.eeriedaily.namqp.v08.methods.basic.BasicConsumeOk;
import de.eeriedaily.namqp.v08.methods.channel.ChannelOpen;
import de.eeriedaily.namqp.v08.methods.channel.ChannelOpenOk;
import de.eeriedaily.namqp.v08.methods.connection.ConnectionOpenOk;
import de.eeriedaily.namqp.v08.methods.queue.QueueBind;
import de.eeriedaily.namqp.v08.methods.queue.QueueBindOk;
import de.eeriedaily.namqp.v08.methods.queue.QueueDeclare;
import de.eeriedaily.namqp.v08.methods.queue.QueueDeclareOk;
import de.eeriedaily.namqp.v08.types.Bit;
import de.eeriedaily.namqp.v08.types.FieldTable;
import de.eeriedaily.namqp.v08.types.ShortString;
import de.eeriedaily.namqp.v08.types.UnsignedShort;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class Example {

    public static ContentListener contentListener;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Executor executor = Executors.newCachedThreadPool();

        ChannelFactory factory =
                new NioClientSocketChannelFactory(executor, executor);

        ClientBootstrap bootstrap = new ClientBootstrap(factory);

        final Client client = new Client(bootstrap, new Configuration());

        ConnectionOpenOk future = client.connect().get();

        Channel channel_1 = client.getChannel(UnsignedShort.valueOf(1));

        Method response;

        response = channel_1.write(new ChannelOpen(ShortString.EMPTY), ChannelOpenOk.class).get();
        System.out.println("response = " + response);

        response = channel_1.write(new MethodBody(new QueueDeclare(UnsignedShort.valueOf(0),
                new ShortString("my_queue"),
                new Bit(false),
                new Bit(false),
                new Bit(false),
                new Bit(true),
                new Bit(false),
                new FieldTable())), QueueDeclareOk.class).get();

        System.out.println("response = " + response);

        response = channel_1.write(new BasicConsume(UnsignedShort.valueOf(0),
                new ShortString("wee"),
                new ShortString("tag"),
                new Bit(false),
                new Bit(true),
                new Bit(false),
                new Bit(false)), BasicConsumeOk.class).get();

        System.out.println("response = " + response);

        response = channel_1.write(new QueueBind(UnsignedShort.valueOf(0),
                new ShortString("wee"),
                ShortString.EMPTY,
                new ShortString("foo"),
                new Bit(false),
                new FieldTable()), QueueBindOk.class).get();

        System.out.println("response = " + response);

        contentListener = new ContentListener() {

            public void contentReceived(UnsignedShort channel, Content content) {
                System.out.println("content = " + content);
            }

        };

        channel_1.addContentListener(contentListener);

    }

}
