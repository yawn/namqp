package de.eeriedaily.namqp.v08.handler.channel;

import de.eeriedaily.namqp.v08.framing.ContentBodyPayload;
import de.eeriedaily.namqp.v08.framing.ContentHeaderPayload;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class Content {

    private final ContentHeaderPayload contentHeaderPayload;
    private final ContentBodyPayload contentBodyPayload;

    public Content(ContentHeaderPayload contentHeaderPayload, ContentBodyPayload contentBodyPayload) {
        this.contentBodyPayload = contentBodyPayload;
        this.contentHeaderPayload = contentHeaderPayload;
    }

    public ContentBodyPayload getContentBody() {
        return contentBodyPayload;
    }

    public ContentHeaderPayload getContentHeader() {
        return contentHeaderPayload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Content content = (Content) o;

        if (!contentBodyPayload.equals(content.contentBodyPayload)) return false;
        if (!contentHeaderPayload.equals(content.contentHeaderPayload)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contentHeaderPayload.hashCode();
        result = 31 * result + contentBodyPayload.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Content{" +
                "contentBodyPayload=" + contentBodyPayload +
                ", contentHeaderPayload=" + contentHeaderPayload +
                '}';
    }
    
}
