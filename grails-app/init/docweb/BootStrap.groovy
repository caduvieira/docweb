package docweb

import working.docweb.ComponenteDigital
import working.docweb.Documento
import working.docweb.Especie
import working.docweb.TipoDocumento

class BootStrap {

    def init = { servletContext ->

        def tipoDoc
        def especie

        //Carga inicial de Tipos de documentos
        if(!TipoDocumento.count()) {
            tipoDoc = new TipoDocumento(nome: "Tipo de Documento 001").save(failOnError: true)
            new TipoDocumento(nome: "Tipo de Documento 002").save(failOnError: true)
            new TipoDocumento(nome: "Tipo de Documento 003").save(failOnError: true)
            new TipoDocumento(nome: "Tipo de Documento 004").save(failOnError: true)
            new TipoDocumento(nome: "Tipo de Documento 005").save(failOnError: true)
        }

        //Carga inicial de Espécie Documental
        if(!Especie.count()) {
            especie = new Especie(nome: "Aviso").save(failOnError: true)
            new Especie(nome: "Declaração").save(failOnError: true)
            new Especie(nome: "Despacho").save(failOnError: true)
            new Especie(nome: "Memorando").save(failOnError: true)
            new Especie(nome: "Ofício").save(failOnError: true)
        }

        //Carga inicial de Documentos
        if(!Documento.count()) {
            new Documento(
                    numero: 'Carta: AB/11.000/2008',
                    protocolo: '0000000.00000000/0000-00',
                    status: Documento.Status.RASCUNHO,
                    nivelAcesso: Documento.NivelAcesso.PUBLICO,
                    titulo: 'Título do Documento',
                    meio: Documento.TipoMeio.DIGITAL,
                    genero: Documento.Genero.TEXTUAL,
                    descricao: 'Descrição do documento',
                    tipo: tipoDoc,
                    especie: especie,
                    idioma: 'pt-BR',
                    possuiAnexo: false,
                    dataProducao: new Date(),
                    localizacao: 'Depósito 201, estante 8, prateleira 2'
            ).save(failOnError: true)
        }
    }
    def destroy = {
    }
}
