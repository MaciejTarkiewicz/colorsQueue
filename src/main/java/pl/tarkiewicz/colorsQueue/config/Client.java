package pl.tarkiewicz.colorsQueue.config;

import io.micronaut.configuration.rabbitmq.annotation.Binding;
import io.micronaut.configuration.rabbitmq.annotation.RabbitClient;
import pl.tarkiewicz.colorsQueue.events.ColorEvent;

@RabbitClient("micronaut")
public interface Client  {

    @Binding("colors")
    void sentMessage(ColorEvent color);

}
