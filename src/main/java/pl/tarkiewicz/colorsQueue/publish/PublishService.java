package pl.tarkiewicz.colorsQueue.publish;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import pl.tarkiewicz.colorsQueue.common.ColorConverter;
import pl.tarkiewicz.colorsQueue.events.ColorEvent;
import pl.tarkiewicz.colorsQueue.config.Client;
import pl.tarkiewicz.colorsQueue.validation.RequestValidation;

@Singleton
public class PublishService {

    private final Client client;
    private final RequestValidation requestValidation;
    private final ColorConverter colorConverter;

    public PublishService(Client client, RequestValidation requestValidation, ColorConverter colorConverter) {
        this.client = client;
        this.requestValidation = requestValidation;
        this.colorConverter = colorConverter;
    }

    public void sentMessage(List<PublishDto> publishDtoList) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        requestValidation.validation(publishDtoList).forEach(element ->
                multiThread(element, executorService)
        );
        executorService.shutdown();
    }

    public void multiThread(PublishDto publishDto, ExecutorService executorService) {
        executorService.submit(() -> {
            ColorEvent colorEvent = colorConverter.apply(publishDto);
            Optional.ofNullable(colorEvent).ifPresent(client::sentMessage);
        });
    }

}
