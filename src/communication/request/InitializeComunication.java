package communication.request;

/**
 * @version 29/04/16.
 */
public class InitializeComunication extends Request {
    @Override
    public String getCommand() {
        return "HELO";
    }

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public void setContent(Object content) {

    }
}