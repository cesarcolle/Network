package communication.response;

import java.io.Serializable;

/**
 * @version 29/04/16.
 */
public abstract class Response implements Serializable {
    public abstract String getContent();
    public abstract void setContent(Object content);
}