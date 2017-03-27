package working.docweb

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EspecieController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Especie.list(params), model:[especieCount: Especie.count()]
    }

    def show(Especie especie) {
        respond especie
    }

    def create() {
        respond new Especie(params)
    }

    @Transactional
    def save(Especie especie) {
        if (especie == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (especie.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond especie.errors, view:'create'
            return
        }

        especie.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'especie.label', default: 'Especie'), especie.id])
                redirect especie
            }
            '*' { respond especie, [status: CREATED] }
        }
    }

    def edit(Especie especie) {
        respond especie
    }

    @Transactional
    def update(Especie especie) {
        if (especie == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (especie.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond especie.errors, view:'edit'
            return
        }

        especie.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'especie.label', default: 'Especie'), especie.id])
                redirect especie
            }
            '*'{ respond especie, [status: OK] }
        }
    }

    @Transactional
    def delete(Especie especie) {

        if (especie == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        especie.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'especie.label', default: 'Especie'), especie.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'especie.label', default: 'Especie'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
