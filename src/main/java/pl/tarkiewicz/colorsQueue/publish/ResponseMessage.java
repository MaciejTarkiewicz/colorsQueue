package pl.tarkiewicz.colorsQueue.publish;

import io.micronaut.http.HttpResponse;

public class ResponseMessage {

    public static HttpResponse<?> success(Message message) {
        message.setPublisher(true);
        return HttpResponse.ok(message);
    }

    public static HttpResponse<?> failure(Message message) {
        message.setPublisher(false);
        return HttpResponse.badRequest(message);
    }

}
