package working.docweb

import grails.test.mixin.*
import spock.lang.*

@TestFor(ComponenteDigitalController)
@Mock(ComponenteDigital)
class ComponenteDigitalControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        //assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
        params["documento"] = new Documento()
        params["nomeOriginal"] = "arquivo1.pdf"
        params["caracteristicasTecnicas"] = "PDF version 1.0"
        params["formato"] = "pdf"
        params["armazenamento"] = "[repositorio]/2017/01/01/29831028309182903.pdf"
        params["fixidade"] = "a6c6v7f839a6c6v7f839a6c6v7f839a6c6v7f839a6c6v7f839a6c6v7f8394444"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.componenteDigitalList
            model.componenteDigitalCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.componenteDigital!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def componenteDigital = new ComponenteDigital()
            componenteDigital.validate()
            controller.save(componenteDigital)

        then:"The create view is rendered again with the correct model"
            model.componenteDigital!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            componenteDigital = new ComponenteDigital(params)

            controller.save(componenteDigital)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/componenteDigital/show/1'
            controller.flash.message != null
            ComponenteDigital.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def componenteDigital = new ComponenteDigital(params)
            controller.show(componenteDigital)

        then:"A model is populated containing the domain instance"
            model.componenteDigital == componenteDigital
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def componenteDigital = new ComponenteDigital(params)
            controller.edit(componenteDigital)

        then:"A model is populated containing the domain instance"
            model.componenteDigital == componenteDigital
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/componenteDigital/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def componenteDigital = new ComponenteDigital()
            componenteDigital.validate()
            controller.update(componenteDigital)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.componenteDigital == componenteDigital

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            componenteDigital = new ComponenteDigital(params).save(flush: true)
            controller.update(componenteDigital)

        then:"A redirect is issued to the show action"
            componenteDigital != null
            response.redirectedUrl == "/componenteDigital/show/$componenteDigital.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/componenteDigital/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def componenteDigital = new ComponenteDigital(params).save(flush: true)

        then:"It exists"
            ComponenteDigital.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(componenteDigital)

        then:"The instance is deleted"
            ComponenteDigital.count() == 0
            response.redirectedUrl == '/componenteDigital/index'
            flash.message != null
    }
}
