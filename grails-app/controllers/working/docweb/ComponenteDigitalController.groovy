package working.docweb

import java.security.MessageDigest

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ComponenteDigitalController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ComponenteDigital.list(params), model:[componenteDigitalCount: ComponenteDigital.count()]
    }

    def show(ComponenteDigital componenteDigital) {
        respond componenteDigital
    }

    def create() {
        respond new ComponenteDigital(params)
    }

    @Transactional
    def save(ComponenteDigital componenteDigital) {
        if (componenteDigital == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        def file = request.getFile('myFile')
        if (file != null && !file.empty) {
            componenteDigital.nomeOriginal = file.originalFilename
            componenteDigital.formato = file.contentType
            componenteDigital.tamanho = file.size
            componenteDigital.fixidade = MessageDigest.getInstance("SHA-256").digest(file.bytes).encodeHex().toString()
            componenteDigital.armazenamento = "/tmp/${UUID.randomUUID() as String}"
            file.transferTo(new File(componenteDigital.armazenamento))
        }

        if (!componenteDigital.validate()) {
            transactionStatus.setRollbackOnly()
            respond componenteDigital.errors, view:'create'
            return
        }

        componenteDigital.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'componenteDigital.label', default: 'ComponenteDigital'), componenteDigital.id])
                redirect componenteDigital
            }
            '*' { respond componenteDigital, [status: CREATED] }
        }
    }

    def edit(ComponenteDigital componenteDigital) {
        respond componenteDigital
    }

    @Transactional
    def update(ComponenteDigital componenteDigital) {
        if (componenteDigital == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        //throw new Exception(request.class.toString())
        def file = request.getFile('myFile')
        if (file != null && !file.empty) {
            componenteDigital.nomeOriginal = file.originalFilename
            componenteDigital.formato = file.contentType
            componenteDigital.tamanho = file.size
            componenteDigital.fixidade = MessageDigest.getInstance("SHA-256").digest(file.bytes).encodeHex().toString()
            componenteDigital.armazenamento = "/tmp/${UUID.randomUUID() as String}"
            file.transferTo(new File(componenteDigital.armazenamento))
        }

        if (componenteDigital.validate()) {
            transactionStatus.setRollbackOnly()
            respond componenteDigital.errors, view:'edit'
            return
        }

        componenteDigital.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'componenteDigital.label', default: 'ComponenteDigital'), componenteDigital.id])
                redirect componenteDigital
            }
            '*'{ respond componenteDigital, [status: OK] }
        }
    }

    @Transactional
    def delete(ComponenteDigital componenteDigital) {

        if (componenteDigital == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        componenteDigital.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'componenteDigital.label', default: 'ComponenteDigital'), componenteDigital.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'componenteDigital.label', default: 'ComponenteDigital'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
