package pl.tarkiewicz.colorsQueue.publish;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class PublishDto {

    private boolean publish;
    private String color;

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
