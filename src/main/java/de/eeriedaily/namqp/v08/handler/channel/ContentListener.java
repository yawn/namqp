package de.eeriedaily.namqp.v08.handler.channel;

import de.eeriedaily.namqp.v08.types.UnsignedShort;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public interface ContentListener {

    public void contentReceived(UnsignedShort channel, Content content);

}
