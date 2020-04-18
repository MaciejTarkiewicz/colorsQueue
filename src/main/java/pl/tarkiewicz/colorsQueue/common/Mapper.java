package pl.tarkiewicz.colorsQueue.common;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("mapping")
public class Mapper {

    private String red;
    private String green;
    private String blue;

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getGreen() {
        return green;
    }

    public void setGreen(String green) {
        this.green = green;
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }
}
