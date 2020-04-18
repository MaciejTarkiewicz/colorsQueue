package pl.tarkiewicz.colorsQueue.common

import pl.tarkiewicz.colorsQueue.publish.PublishDto
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class ColorConverterTest extends Specification {

    def mapper = new Mapper()

    @Subject
    ColorConverter colorConverter = new ColorConverter(mapper)

    def setup() {
        mapper.red = '255,0,0'
        mapper.blue = '0,0,255'
        mapper.green = '0,255,0'
    }

    @Unroll
    def "should convert ANSI from Request to Color"() {
        given:

        when:

        def expectedResult = colorConverter.apply(publishDtoCreator(color))

        then:

        result == expectedResult.getColor()

        where:
        color     || result
        "255,0,0" || "RED"
        "0,0,255" || "BLUE"
        "0,255,0" || "GREEN"

    }

    def "should not convert ANSI from Request to Color when color is not exist"() {
        given:

        when:
        def expectedResult = colorConverter.apply(publishDtoCreator("color"))

        then:

        expectedResult == null

    }

    static def publishDtoCreator(String color) {
        def publishDto = new PublishDto()
        publishDto.color = color
        publishDto.publish = true
        publishDto
    }

}
