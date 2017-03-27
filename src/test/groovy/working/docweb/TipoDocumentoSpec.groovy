package working.docweb

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TipoDocumento)
class TipoDocumentoSpec extends Specification {

    def tipoDocumento

    def setup() {
        tipoDocumento = new TipoDocumento()
    }

    def cleanup() {
    }

    void "Nome do Tipo de Documento não pode ser vazio"() {
        when: "quando o nome do tipo do documento vazio"
        tipoDocumento.nome = ''
        tipoDocumento.validate()

        then: "então o documento não é considerado válido"
        tipoDocumento.errors?.hasFieldErrors("nome")

        when: "quando o nome do tipo de documento não for vazio"
        tipoDocumento.nome = "Nome válido"
        tipoDocumento.validate()

        then: "então o tipo de documento é considerado válido"
        !tipoDocumento.errors?.hasFieldErrors("nome")
    }
}
