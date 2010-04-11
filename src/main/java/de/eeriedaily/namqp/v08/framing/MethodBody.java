package de.eeriedaily.namqp.v08.framing;

import de.eeriedaily.namqp.v08.AbstractTransmittableContainer;
import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.methods.Method;
import de.eeriedaily.namqp.v08.methods.MethodFactory;
import de.eeriedaily.namqp.v08.types.Octet;
import de.eeriedaily.namqp.v08.types.UnsignedShort;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class MethodBody extends AbstractTransmittableContainer implements FrameBody {

    public static final Octet FRAME_ID = new Octet(1);

    private final UnsignedShort classId;
    private final UnsignedShort methodId;
    private final Method method;

    public MethodBody(Method method) {
        this.classId = method.getMethodClassId();
        this.methodId = method.getMethodMethodId();
        this.method = method;
    }

    public MethodBody(ChannelBuffer channelBuffer) {
        this.classId = new UnsignedShort(channelBuffer);
        this.methodId = new UnsignedShort(channelBuffer);
        this.method = MethodFactory.get(classId, methodId, channelBuffer);
    }

    public Octet getFrameId() {
        return FRAME_ID;
    }

    @SuppressWarnings({"unchecked"})
    public <T extends Method> T getMethod() {
        return (T) method;
    }

    @Override
    protected Transmittable[] getTransmittables() {
        return all(classId, methodId, method);
    }

    @Override
    public String toString() {
        return "MethodBody{" +
                "classId=" + classId +
                ", methodId=" + methodId +
                ", method=" + method +
                '}';
    }
    
}
