
/**
 * Paquete que contiene los controladores del sistema de venta de billetes.
 * @author Kush
 */
package controllers

import java.util.*

data class PrecioBilletes(
    val zonas: String,
    val billeteSenzillo: Float,
    val tcasual: Float,
    val tusual: Float,
    val tfamiliar: Float,
    val tjove: Float
)

/**
 * Función principal del programa.
 * Permite al usuario comprar billetes interactuando mediante un menú.
 */
fun main() {
    val billetes: Array<PrecioBilletes> = arrayOf(
        PrecioBilletes("Zona 1", 2.40f, 11.35f, 40.00f, 10.00f, 80.00f),
        PrecioBilletes(
            "Zona 2",
            2.40f * 1.3125f,
            11.35f * 1.3125f,
            40.00f * 1.3125f,
            10.00f * 1.3125f,
            80.00f * 1.3125f
        ),
        PrecioBilletes(
            "Zona 3",
            2.40f * 1.8443f,
            11.35f * 1.8443f,
            40.00f * 1.8443f,
            10.00f * 1.8443f,
            80.00f * 1.8443f
        )
    )

    val scanner = Scanner(System.`in`).useLocale(Locale.UK)
    val salir = false
    while (salir == false) {
        var procesos = 0
        var precioTotal = 0f
        var billetesComprados = ""
        do {
            val billete = tipoBillete(scanner)
            val zona = tipoZona(scanner)
            val precioBillete = calcularCoste(billetes, zona, billete)
            billetesComprados += "$billete - $zona - Preu: " + (precioBillete * 100).toInt() / 100.0 + "€" + "\n"
            precioTotal += precioBillete
            val respuesta: Boolean = repetirProceso(scanner)
            procesos++
        } while (procesos < 3 && respuesta)

        pagar(precioTotal, scanner)
        mostrarTicket(billetesComprados)
    }

    scanner.close()
}

/**
 * Solicita al usuario el tipo de billete que desea comprar.
 *
 * @param scanner Objeto para leer la entrada del usuario.
 * @return El tipo de billete seleccionado.
 */
fun tipoBillete(scanner: Scanner): String {
    println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _")
    println("Quin bitllet desitja adquirir?")
    println("1- Bitllet Senzill")
    println("2- TCasual")
    println("3- TUsual")
    println("4- TFamiliar")
    println("5- TJove")

    val opcion = scanner.nextInt()
    return when (opcion) {
        1 -> "Bitllet Senzill"
        2 -> "TCasual"
        3 -> "TUsual"
        4 -> "TFamiliar"
        5 -> "TJove"
        else -> {
            println("Opció no vàlida. S'agafarà Bitllet Senzill per defecte.")
            "Bitllet Senzill"
        }
    }
}

/**
 * Solicita al usuario la zona de viaje.
 *
 * @param scan Objeto para leer la entrada del usuario.
 * @return La zona seleccionada.
 */
fun tipoZona(scan: Scanner): Int {
    println("Quina zona vol viatjar?")
    println("1 - Zona 1")
    println("2 - Zona 2")
    println("3 - Zona 3")
    val zona = scan.nextInt()
    scan.nextLine()
    return when (zona) {
        in 1..3 -> zona
        else -> {
            println("Zona no vàlida. S'agafarà Zona 1 per defecte.")
            1
        }
    }
}

/**
 * Calcula el coste del billete según la zona y el tipo de billete seleccionado.
 *
 * @param billetes Array que contiene los precios de las diferentes zonas.
 * @param zona La zona seleccionada.
 * @param tipoBillete El tipo de billete seleccionado.
 * @return El precio del billete.
 */
fun calcularCoste(billetes: Array<PrecioBilletes>, zona: Int, tipoBillete: String): Float {
    val precios = billetes[zona - 1]
    return when (tipoBillete) {
        "Bitllet Senzill" -> precios.billeteSenzillo
        "TCasual" -> precios.tcasual
        "TUsual" -> precios.tusual
        "TFamiliar" -> precios.tfamiliar
        "TJove" -> precios.tjove
        else -> 0.0f
    }
}

/**
 * Pregunta al usuario si desea seguir comprando.
 *
 * @param scan Objeto para leer la entrada del usuario.
 * @return `true` si desea continuar, `false` en caso contrario.
 */
fun repetirProceso(scan: Scanner): Boolean {
    println("Vols seguir comprant?[S,N]")
    val pedirRespuesta = scan.nextLine().lowercase()
    var respuesta = false
    if (pedirRespuesta == "s") {
        respuesta = true
    } else if (pedirRespuesta == "n") {
        respuesta = false
    } else {
        println("ERROR")
    }
    return respuesta
}

/**
 * Realiza el proceso de pago interactuando con el usuario.
 *
 * @param precio Precio total de los billetes comprados.
 * @param scanner Objeto para leer la entrada del usuario.
 */
fun pagar(precio: Float, scanner: Scanner) {
    println("Ha comprat un bitllet per un total de " + (precio * 100).toInt() / 100.0 + "€")
    println("Introdueixi monedes o bitllets vàlids en EUROS:")

    val dineroLegal = listOf(0.05, 0.10, 0.20, 0.50, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0, 100.0, 200.0, 500.0)
    var totalPagado = 0.0

    while (totalPagado < precio) {
        val dineroIntroducido = scanner.nextDouble()
        if (dineroLegal.contains(dineroIntroducido)) {
            totalPagado += dineroIntroducido
            println("Total pagat: " + (totalPagado * 100).toInt() / 100.0 + "€")
        } else {
            println("Quantitat no vàlida. Introdueixi una moneda o bitllet vàlid.")
        }
    }

    val cambio = totalPagado - precio
    if (cambio > 0) {
        println("El seu canvi és: " + (cambio * 100).toInt() / 100.0 + "€")
    }
    println("Pagament completat!")
}

/**
 * Muestra el ticket al usuario si así lo solicita.
 *
 * @param billetesComprados String que contiene los billetes comprados.
 */
fun mostrarTicket(billetesComprados: String) {
    println("Vols el teu ticket?[S,N]")
    val scanner = Scanner(System.`in`).useLocale(Locale.UK)
    val respuestaSoN = scanner.nextLine().lowercase()
    if (respuestaSoN == "s") {
        println("_____TICKET_____")
        println(billetesComprados)
        println("________________")
        println("Reculli el teu tiquet. Adeu!!")
    } else if (respuestaSoN == "n") {
    } else {
        println("ERROR")
    }
}
