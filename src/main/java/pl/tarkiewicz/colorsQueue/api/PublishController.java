package pl.tarkiewicz.colorsQueue.api;

import java.util.List;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import pl.tarkiewicz.colorsQueue.publish.Message;
import pl.tarkiewicz.colorsQueue.publish.PublishDto;
import pl.tarkiewicz.colorsQueue.publish.PublishService;
import pl.tarkiewicz.colorsQueue.publish.ResponseMessage;

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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseMessage
                    .failure(Message.builder().publish(false).build());

        }

    }

}
