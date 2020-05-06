package pl.tarkiewicz.colorsQueue.publish;

import io.micronaut.http.HttpResponse;

public class ResponseMessage {

    public static HttpResponse<Message> success(Message message) {
        message.setPublished(true);
        return HttpResponse.ok(message);
    }

    public static HttpResponse<Message> failure(Message message) {
        message.setPublished(false);
        return HttpResponse.badRequest(message);
    }

}
