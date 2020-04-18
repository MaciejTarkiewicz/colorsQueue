package pl.tarkiewicz.colorsQueue.publish;

public class Message {

    private boolean publisher;

    public boolean isPublisher() {
        return publisher;
    }

    public void setPublisher(boolean publisher) {
        this.publisher = publisher;
    }

    static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private boolean publisher;

        Builder publish(boolean publisher) {
            this.publisher = publisher;
            return this;
        }

        Message build() {
            Message message = new Message();
            message.setPublisher(publisher);
            return message;
        }
    }

}
