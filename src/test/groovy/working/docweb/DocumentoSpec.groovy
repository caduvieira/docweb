package working.docweb

import ch.qos.logback.core.status.Status
import grails.test.mixin.TestFor
import spock.lang.Specification

import javax.swing.text.Document

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Documento)
class DocumentoSpec extends Specification {

    def doc

    def setup() {
        doc = new Documento()
    }

    def cleanup() {
    }

    void "Número de Protocolo somente pode ser vazio se o documento está na situação RASCUNHO"() {
        when: "quando o status do documento for RASCUNHO"
        doc.status = Documento.Status.RASCUNHO

        and: "e o número de protocolo for vazio"
        doc.protocolo = null
        doc.validate()

        then: "então o documento não é considerado válido"
        !doc.errors?.hasFieldErrors("protocolo")


        when: "quando o status do documento diferente de ORIGINAL"
        doc.status = Documento.Status.ORIGINAL

        and: "e o número de protocolo for vazio"
        doc.protocolo = null
        doc.validate()

        then: "então o documento não é considerado válido"
        doc.errors?.hasFieldErrors("protocolo")


        when: "quando o status do documento diferente de COPIA"
        doc.status = Documento.Status.COPIA

        and: "e o número de protocolo for vazio"
        doc.protocolo = null
        doc.validate()

        then: "então o documento não é considerado válido"
        doc.errors?.hasFieldErrors("protocolo")
    }

    void "Status do documento não deve ser nulo"() {
        when:
        doc.status = null
        doc.validate()

        then:
        doc.errors?.hasFieldErrors("status")
    }

    void "Tipo do Meio do documento não deve ser nulo"() {
        when:
        doc.meio = null
        doc.validate()

        then:
        doc.errors?.hasFieldErrors("meio")
    }

    void "Validação do Título do documento"() {

        when: "quando documento possui título com 3 ou mais caracteres"
        doc.titulo = "abc"
        doc.validate()

        then: "então é considerado um título válido"
        !doc.errors?.hasFieldErrors("titulo")

        when: "quando documento possui título com 100 caracteres ou menos"
        doc.titulo = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        doc.validate()

        then: "então é considerado um título válido"
        !doc.errors?.hasFieldErrors("titulo")

        when:
        doc.titulo = null
        doc.validate()

        then:
        doc.errors?.hasFieldErrors("titulo")

        when:
        doc.titulo = ""
        doc.validate()

        then:
        doc.errors?.hasFieldErrors("titulo")

        when:
        doc.titulo = "ab"
        doc.validate()

        then:
        doc.errors?.hasFieldErrors("titulo")

        when:
        doc.titulo = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaX"
        doc.validate()

        then:
        doc.errors?.hasFieldErrors("titulo")
    }

    void "Validação da Descrição do documento"() {

        when: "quando documento possui uma descrição com 3 ou mais caracteres"
        doc.descricao = "abc"
        doc.validate()

        then: "então é considerado uma descricao válida"
        !doc.errors?.hasFieldErrors("descricao")

        when: "quando documento possui descricao com 255 caracteres ou menos"
        doc.descricao = '''aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'''
        doc.validate()

        then: "então é considerado uma descricao válida"
        !doc.errors?.hasFieldErrors("descricao")

        when:
        doc.descricao = '''aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaX'''
        doc.validate()

        then:
        doc.errors?.hasFieldErrors("descricao")
    }
}
