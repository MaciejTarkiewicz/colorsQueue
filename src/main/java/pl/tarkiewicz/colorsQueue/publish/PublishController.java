package pl.tarkiewicz.colorsQueue.publish;

import java.util.List;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/publish")
public class PublishController {

    private final PublishService publishService;

    public PublishController(PublishService publishService) {
        this.publishService = publishService;
    }

    @Post("/")
    public HttpResponse<?> publish(@Body List<PublishDto> publishDto) {
        try {
            publishService.sentMessage(publishDto);
            return ResponseMessage
                    .success(Message.builder().publish(true).build());
        } catch (RuntimeException e) {
            return ResponseMessage
                    .failure(Message.builder().publish(false).build());

        }

    }

}
