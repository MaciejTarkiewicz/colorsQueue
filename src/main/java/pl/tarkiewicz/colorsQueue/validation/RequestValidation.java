package pl.tarkiewicz.colorsQueue.validation;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import io.micronaut.context.annotation.Context;
import pl.tarkiewicz.colorsQueue.publish.PublishDto;

@Context
public class RequestValidation {

    private Logger logger;

    public RequestValidation() {
        logger = Logger.getLogger(RequestValidation.class.getName());
    }

    public List<PublishDto> validation(List<PublishDto> publishDtoList) {
        return publishDtoList.stream()
                .filter(PublishDto::isPublish)
                .filter(this::checkColorIsNotNull)
                .filter(this::isEmpty)
                .collect(Collectors.toList());
    }

    private boolean isEmpty(PublishDto publishDto) {
        if (publishDto.getColor().isEmpty()) {
            logger.warning("Color isEmpty!");
        }
        return !publishDto.getColor().isEmpty();
    }

    private boolean checkColorIsNotNull(PublishDto publishDto) {
        if (publishDto.getColor() == null) {
            logger.warning("Color is Null!");
        }
        return publishDto.getColor() != null;
    }

}
