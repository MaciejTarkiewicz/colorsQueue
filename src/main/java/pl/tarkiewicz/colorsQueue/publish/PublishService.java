package pl.tarkiewicz.colorsQueue.publish;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import pl.tarkiewicz.colorsQueue.common.ColorConverter;
import pl.tarkiewicz.colorsQueue.config.Client;
import pl.tarkiewicz.colorsQueue.observer.MyObserver;
import pl.tarkiewicz.colorsQueue.validation.RequestValidation;

@Singleton
public class PublishService {

    private final Client client;
    private final RequestValidation requestValidation;
    private final ColorConverter colorConverter;

    public PublishService(Client client, RequestValidation requestValidation, ColorConverter colorConverter, MyObserver myObserver) {
        this.client = client;
        this.requestValidation = requestValidation;
        this.colorConverter = colorConverter;
    }

    public void sentMessage(List<PublishDto> publishDtoList, MyObserver myObserver) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        requestValidation.validation(publishDtoList).forEach(element -> {
                    multiThreadSent(element, executorService, myObserver);
                }
        );
        executorService.shutdown();
    }

    public void multiThreadSent(PublishDto publishDto, ExecutorService executorService, MyObserver myObserver) {
        executorService.submit(() -> {
            Optional.ofNullable(colorConverter.apply(publishDto)).ifPresent(event -> {
                client.sentMessage(event);
                myObserver.finish(event.getColor());
            });

        });
    }

}
