package working.docweb

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Especie)
class EspecieSpec extends Specification {

    def especie

    def setup() {
        especie = new Especie()
    }

    def cleanup() {
    }

    void "Nome da Espécie Documental não pode ser vazio"() {
        when: "quando o nome da espécie documental é vazio"
        especie.nome = ''
        especie.validate()

        then: "então a espécie não é considerada válida"
        especie.errors?.hasFieldErrors("nome")

        when: "quando o nome da espécie documental não for vazio"
        especie.nome = "Nome válido"
        especie.validate()

        then: "então a espécie documental é considerado válido"
        !especie.errors?.hasFieldErrors("nome")
    }
}
