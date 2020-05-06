package pl.tarkiewicz.colorsQueue.common;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import io.micronaut.context.annotation.Context;
import pl.tarkiewicz.colorsQueue.events.ColorEvent;
import pl.tarkiewicz.colorsQueue.publish.PublishDto;
import pl.tarkiewicz.colorsQueue.validation.RequestValidation;

@Context
public class ColorConverter {

    Logger logger = Logger.getLogger(RequestValidation.class.getName());

    private final Mapper mapper;

    public ColorConverter(Mapper mapper) {
        this.mapper = mapper;
    }

    public ColorEvent apply(PublishDto publishDto) {
        Map<String, String> colorsMap = Map.of(
                "RED", mapper.getRed(),
                "BLUE", mapper.getBlue(),
                "GREEN", mapper.getGreen()
        );

        return mapColor(publishDto, colorsMap).orElseGet(this::colorNotExist);
    }

    private Optional<ColorEvent> mapColor(PublishDto publishDto, Map<String, String> colorsMap) {
        return colorsMap.entrySet().stream()
                .map(toColor(publishDto))
                .filter(Objects::nonNull)
                .map(this::createColorQueueLoad)
                .collect(toSingleton());
    }

    private ColorEvent colorNotExist() {
        logger.warning("Color doesn't exist!");
        return null;
    }

    private Function<Map.Entry<String, String>, String> toColor(PublishDto publishDto) {
        return element -> {
            if (element.getValue().equals(publishDto.getColor())) {
                return element.getKey();
            } else {
                return null;
            }

        };
    }

    private ColorEvent createColorQueueLoad(String color) {
        return new ColorEvent(color);
    }

    private static <T> Collector<T, ?, Optional<T>> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> list.size() == 1 ? Optional.of(list.get(0)) : Optional.empty()
        );
    }

}

