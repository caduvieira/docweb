package docweb

import working.docweb.Especie
import working.docweb.TipoDocumento

class BootStrap {

    def init = { servletContext ->

        //Carga inicial de Tipos de documentos
        if(!TipoDocumento.count()) {
            new TipoDocumento(nome: "Tipo de Documento 001").save(failOnError: true)
            new TipoDocumento(nome: "Tipo de Documento 002").save(failOnError: true)
            new TipoDocumento(nome: "Tipo de Documento 003").save(failOnError: true)
            new TipoDocumento(nome: "Tipo de Documento 004").save(failOnError: true)
            new TipoDocumento(nome: "Tipo de Documento 005").save(failOnError: true)
        }

        //Carga inicial de Espécie Documental
        if(!Especie.count()) {
            new Especie(nome: "Aviso").save(failOnError: true)
            new Especie(nome: "Declaração").save(failOnError: true)
            new Especie(nome: "Despacho").save(failOnError: true)
            new Especie(nome: "Memorando").save(failOnError: true)
            new Especie(nome: "Ofício").save(failOnError: true)
        }
    }
    def destroy = {
    }
}
