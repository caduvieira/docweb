package working.docweb

class Documento {

    enum Status {
        RASCUNHO, ORIGINAL, COPIA
    }

    enum TipoMeio {
        DIGITAL, NAO_DIGITAL, HIBRIDO
    }

    enum Genero {
        AUDIOVISUAL, TEXTUAL, CARTOGRAFICO, ICONOGRAFICO, MULTIMIDIA
    }

    enum NivelAcesso {
        PUBLICO, RESTRITO, SIGILOSO
    }

    /***
     * Número ou código alfanumérico atribuído ao documento no ato de sua produção
     */
    String numero

    /***
     * Número ou código alfanumérico atribuído ao documento no ato do protocolo
     */
    String protocolo

    /***
     * Indicação do grau de formalização do documento
     */
    Status status

    /***
     * Elemento de descrição que nomeia o documento ou processo/dossiê
     */
    String titulo

    /***
     * Identificação do meio do documento/volume/processo/dossiê: digital, não digital ou híbrido
     */
    TipoMeio meio

    /***
     * Indica o gênero documental, ou seja, a configuração da informação no documento de acordo com o sistema de signos utilizado na comunicação do documento
     */
    Genero genero

    /***
     * Exposição concisa do conteúdo do documento, processo ou dossiê.
     */
    String descricao

    /***
     * Indica o tipo documental, ou seja, a configuração da espécie documental de acordo com a atividade que a gerou
     */
    TipoDocumento tipo

    /***
     * Idioma(s) em que é expresso o conteúdo do documento
     */
    String idioma

    /***
     * Indica se o documento tem anexos
     */
    Boolean possuiAnexo

    /***
     * Indicação dos níveis de acesso ao documento a partir da classificação da informação quanto ao grau de sigilo e restrição de acesso
     */
    NivelAcesso nivelAcesso

    /***
     * Registro cronológico (data e hora) e tópico (local) da produção do documento
     */
    Date dataProducao

    /***
     * Local de armazenamento atual do documento.
     * Pode ser um lugar (depósito, estante, repositório digital) ou uma notação física
     */
    String localizacao

    static constraints = {
        protocolo nullable: true, validator: { val, doc -> Documento.validarProtocolo(val, doc) }
        titulo blank: false, size: 3..100
        descricao nullable: true, size: 0..255
        tipo nullable: true
        idioma nullable: true
    }

    /***
     * Validação do número de protocolo do documento
     * @return
     */
    static boolean validarProtocolo(String protocolo, Documento documento) {
        assert documento != null
        return (documento.status == Status.RASCUNHO) || (protocolo != null && protocolo != '')
    }
}
