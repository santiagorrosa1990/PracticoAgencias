package practicoagencias

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class GeneralControllerSpec extends Specification implements ControllerUnitTest<GeneralController> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }


    void "get to index"(){
        when:
        request.method = 'GET'
        controller.index()

        then:
        response.status == 200
    }

    void "get to buscar"(){
        when:
        request.method = 'GET'
        controller.buscar()

        then:
        response.status == 200
    }

    void "test filtro agencias ticket"(){
        when:
        params.medipago = "pagofacil"
        controller.buscar()

        then:
        response.mediopago == 'pagofacil'
    }
}
