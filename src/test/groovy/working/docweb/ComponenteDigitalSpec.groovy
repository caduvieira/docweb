package working.docweb

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ComponenteDigital)
class ComponenteDigitalSpec extends Specification {

    ComponenteDigital componente

    def setup() {
        componente = new ComponenteDigital();
    }

    def cleanup() {
    }

    void "Componente digital não deve ser válido"() {
        when: "Quando as propriedades do componente digital assumirem domínios inválidos"
        componente.documento = null
        componente.nomeOriginal = ""
        componente.formato = ""
        componente.armazenamento = ""
        componente.fixidade = ""

        then: "Então o componente digital não será válido"
        !componente.validate()
        componente.errors.hasFieldErrors("documento")
        componente.errors.hasFieldErrors("nomeOriginal")
        componente.errors.hasFieldErrors("formato")
        componente.errors.hasFieldErrors("armazenamento")
        componente.errors.hasFieldErrors("fixidade")
    }

    void "Componente digital deve ser válido"() {
        when: "Quando as propriedades do componente digital assumirem domínios válidos"
        componente.documento = new Documento()
        componente.nomeOriginal = "arquivo1.pdf"
        componente.formato = "pdf"
        componente.armazenamento = "[repositorio]/2017/01/01/29831028309182903.pdf"
        componente.fixidade = "a6c6v7f839a6c6v7f839a6c6v7f839a6c6v7f839a6c6v7f839a6c6v7f8394444"

        then: "Então o componente digital será válido"
        componente.validate()
    }
}
