package working.docweb

class Especie {

    String nome
    static constraints = {
        nome blank: false, unique: true
    }

    @Override
    String toString() {
        return (nome != null && nome != '') ? nome.toString() : super.toString()
    }
}
