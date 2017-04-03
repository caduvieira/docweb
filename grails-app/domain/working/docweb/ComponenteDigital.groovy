package working.docweb

class ComponenteDigital {

    ComponenteDigital(Documento documento){
        this.documento = documento
    }

    /***
     * Referência para o documento ao qual o componente digital pertence
     */
    Documento documento

    /***
     * Nome original do componente digital no momento em que foi inserido no repositório,
     * antes de ser renomeado com o identificador do repositório.
     */
    String nomeOriginal

    /***
     * Propriedades técnicas de um componente digital, aplicáveis à maioria dos formatos,
     * tais como: nível de composição, tamanho, software de criação e inibidores.
     */
    String caracteristicasTecnicas

    /***
     * Identificação do formato de arquivo do componente digital
     *
     *
     */
    String formato

    /***
     * Informações sobre a localização e o suporte do componente digital, bem como os recursos necessários para
     * armazenamento permanente.
     */
    String armazenamento

    /***
     * Informações sobre o ambiente de software necessário para apresentar e/ou usar os componentes digitais,
     * incluindo a aplicação e o sistema operacional.
     */
    String ambienteSofware

    /***
     * Informações sobre os componentes de hardware necessários para operar o software referenciado em 5.6,
     * incluindo periféricos.
     */
    String ambienteHardware

    /***
     * Informações utilizadas para verificar se o componente digital sofreu mudanças não documentadas
     */
    String fixidade

    /***
     * Tamanho, em bytes, do espaço ocupado pela componente digital no sistema de arquivo
     */
    Long tamanho = 0

    static belongsTo = [documento: Documento]

    static constraints = {
        nomeOriginal blank: false, size: 3..100
        formato blank: false
        armazenamento blank: false
        fixidade blank: false, size: 64..64
        caracteristicasTecnicas nullable: true
        ambienteSofware nullable: true
        ambienteHardware nullable: true
    }

}
