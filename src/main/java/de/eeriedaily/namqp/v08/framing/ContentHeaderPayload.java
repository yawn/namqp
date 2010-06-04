package de.eeriedaily.namqp.v08.framing;

import de.eeriedaily.namqp.v08.AbstractTransmittableContainer;
import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.methods.basic.BasicProperty;
import de.eeriedaily.namqp.v08.types.PropertyList;
import de.eeriedaily.namqp.v08.types.Octet;
import de.eeriedaily.namqp.v08.types.UnsignedLongLong;
import de.eeriedaily.namqp.v08.types.UnsignedShort;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class ContentHeaderPayload extends AbstractTransmittableContainer implements Payload {

    public static final Octet FRAME_ID = new Octet(2);

    private final UnsignedShort classId;
    private final UnsignedShort weight;
    private final UnsignedLongLong bodySize;
    private final PropertyList propertyList;

    public ContentHeaderPayload(ChannelBuffer buffer) {

        this.classId = new UnsignedShort(buffer);
        this.weight = new UnsignedShort(buffer);
        this.bodySize = new UnsignedLongLong(buffer);

        int propertyFlags = buffer.getUnsignedShort(0);

        if (classId.equals(BasicProperty.CLASS_ID)) {
            this.propertyList = new PropertyList<BasicProperty>(buffer, BasicProperty.class, BasicProperty.values());
        } else {

            if (propertyFlags != 0)
                throw new UnknownPropertiesException(classId);
            else
                this.propertyList = PropertyList.EMPTY;

        }

    }

    public Octet getFrameId() {
        return FRAME_ID;
    }

    @Override
    protected Transmittable[] getTransmittables() {
        return all(classId, weight, bodySize, propertyList);
    }

    @Override
    public String toString() {
        return "ContentHeaderPayload{" +
                "bodySize=" + bodySize +
                ", classId=" + classId +
                ", weight=" + weight +
                ", propertyList=" + propertyList +
                "} " + super.toString();
    }
    
}
