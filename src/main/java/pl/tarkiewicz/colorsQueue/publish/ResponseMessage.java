package pl.tarkiewicz.colorsQueue.publish;

public class ResponseMessage {

    final boolean publisher;

    public ResponseMessage(final boolean publisher) {
        this.publisher = publisher;
    }

    public boolean isPublisher() {
        return publisher;
    }
}
