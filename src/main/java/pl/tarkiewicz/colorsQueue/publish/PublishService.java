package pl.tarkiewicz.colorsQueue.publish;

import java.util.List;
import java.util.Objects;

import io.micronaut.context.annotation.Context;
import pl.tarkiewicz.colorsQueue.common.ColorConverter;
import pl.tarkiewicz.colorsQueue.config.Client;
import pl.tarkiewicz.colorsQueue.validation.RequestValidation;

@Context
public class PublishService {

    private Client client;
    private RequestValidation requestValidation;
    private ColorConverter colorConverter;

    public PublishService(Client client, RequestValidation requestValidation, ColorConverter colorConverter) {
        this.client = client;
        this.requestValidation = requestValidation;
        this.colorConverter = colorConverter;
    }

    public void sentMessage(List<PublishDto> publishDtoList) {
        requestValidation.validation(publishDtoList).stream()
                .map(colorConverter::apply)
                .filter(Objects::nonNull)
                .forEach(client::sentMessage);
    }

}
