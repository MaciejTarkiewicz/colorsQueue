package pl.tarkiewicz.colorsQueue.events;

import io.micronaut.core.annotation.Introspected;

@Introspected
public final class ColorEvent {

    private String color;

    public ColorEvent(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

}
