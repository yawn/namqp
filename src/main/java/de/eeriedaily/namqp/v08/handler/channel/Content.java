package de.eeriedaily.namqp.v08.handler.channel;

import de.eeriedaily.namqp.v08.framing.ContentBody;
import de.eeriedaily.namqp.v08.framing.ContentHeader;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class Content {

    private final ContentHeader contentHeader;
    private final ContentBody contentBody;

    public Content(ContentHeader contentHeader, ContentBody contentBody) {
        this.contentBody = contentBody;
        this.contentHeader = contentHeader;
    }

    public ContentBody getContentBody() {
        return contentBody;
    }

    public ContentHeader getContentHeader() {
        return contentHeader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Content content = (Content) o;

        if (!contentBody.equals(content.contentBody)) return false;
        if (!contentHeader.equals(content.contentHeader)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contentHeader.hashCode();
        result = 31 * result + contentBody.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Content{" +
                "contentBody=" + contentBody +
                ", contentHeader=" + contentHeader +
                '}';
    }
    
}
