package org.example.controllers

import java.util.*

data class PrecioBilletes(
    val zonas: String,
    val billeteSenzillo: Float,
    val tcasual: Float,
    val tusual: Float,
    val tfamiliar: Float,
    val tjove: Float
)

fun main() {
    val billetes: Array<PrecioBilletes> = arrayOf(
        PrecioBilletes("Zona 1", 2.40f, 11.35f, 40.00f, 10.00f, 80.00f),
        PrecioBilletes("Zona 2", 2.40f*1.3125f, 11.35f*1.3125f, 40.00f*1.3125f, 10.00f*1.3125f, 80.00f*1.3125f),
        PrecioBilletes("Zona 3", 2.40f*1.8443f, 11.35f*1.8443f, 40.00f*1.8443f, 10.00f*1.8443f, 80.00f*1.8443f),
    )

    val scanner = Scanner(System.`in`).useLocale(Locale.UK)

    val billete = tipoBillete(scanner)
    val zona = tipoZona(scanner)
    val precio = calcularCoste(billetes, zona, billete)

    pagar(precio, scanner)
    mostrarTicket(billete, billetes[zona - 1].zonas, precio)
    scanner.close()
}

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

fun tipoZona(scanner: Scanner): Int {
    println("Quina zona vol viatjar?")
    println("1 - Zona 1")
    println("2 - Zona 2")
    println("3 - Zona 3")

    return when (val zona = scanner.nextInt()) {
        in 1..3 -> zona
        else -> {
            println("Zona no vàlida. S'agafarà Zona 1 per defecte.")
            1
        }
    }
}

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

fun mostrarTicket(billete: String, zonas: String, precio: Float) {
    println("_____TICKET_____")
    println("$billete - $zonas - Preu: " + (precio * 100).toInt() / 100.0 + "€")
    println("________________")
    println("Reculli el teu tiquet. Adeu!!")
}
