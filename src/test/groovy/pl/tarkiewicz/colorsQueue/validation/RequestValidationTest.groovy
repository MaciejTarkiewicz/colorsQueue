package pl.tarkiewicz.colorsQueue.validation

import pl.tarkiewicz.colorsQueue.publish.PublishDto
import spock.lang.Specification
import spock.lang.Subject

class RequestValidationTest extends Specification {

    @Subject
    RequestValidation requestValidation = new RequestValidation()

    void "should filter color is not null or empty"() {
        given:
        def list = [publishDtoCreator("", true), publishDtoCreator("0,0,255", true),
                    publishDtoCreator("test", false), publishDtoCreator(null, true)]
        when:

        def result = requestValidation.validation(list)

        then:

        result.size() == 1

    }

    static def publishDtoCreator(String color, boolean publish) {
        def publishDto = new PublishDto()
        publishDto.color = color
        publishDto.publish = publish
        publishDto
    }
}
