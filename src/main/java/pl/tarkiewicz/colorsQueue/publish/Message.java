package pl.tarkiewicz.colorsQueue.publish;

public class Message {

    private boolean published;

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private boolean published;

        public Builder publish(boolean published) {
            this.published = published;
            return this;
        }

        public Message build() {
            Message message = new Message();
            message.published = this.published;
            return message;
        }
    }

}
