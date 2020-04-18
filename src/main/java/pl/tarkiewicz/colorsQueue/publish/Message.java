package pl.tarkiewicz.colorsQueue.publish;

public class Message {

    private boolean published;

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private boolean published;

        Builder publish(boolean published) {
            this.published = published;
            return this;
        }

        Message build() {
            Message message = new Message();
            message.setPublished(published);
            return message;
        }
    }

}
