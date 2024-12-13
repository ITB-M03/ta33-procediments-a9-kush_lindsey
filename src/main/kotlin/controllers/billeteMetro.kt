package org.example.controllers
import java.util.*
const val billeteSenzillo : Float = 2.40f
const val tcasual : Float = 11.35f
const val tusual : Float = 40.00f
const val tfamiliar : Float = 10.00f
const val tjove : Float = 80.00f
fun main() {
    val scan = inicioscan()
    val billete = tipoBillete(scan)
    val zona = tipoZona(scan)
    val precio = costeBillete()
    finalscan(scan)
}
fun inicioscan(): Scanner {
    return Scanner(System.`in`).useLocale(Locale.UK)
}
fun tipoBillete(scan: Scanner){
    println("Quin bitllet desitja adquirir?")
    println("1- Bitllet Senzill")
    println("2 - TCasual")
    println("3 - TUsual")
    println("4 - TFamiliar")
    println("5 - TJove")
    val scan=Scanner(System.`in`)
    val tipoBillete = scan.nextInt()
    when(tipoBillete){
        1-> tipoZona(scan)
        2 -> tipoZona(scan)
        3 ->tipoZona(scan)
        4 -> tipoZona(scan)
        5 -> tipoZona(scan)
    }
}

fun tipoZona(scan: Scanner){
    println("Quina zona vol viatjar?")
    println("1")
    println("2")
    println("3")
    val scan=Scanner(System.`in`)
    val tipoZona = scan.nextInt()


}
fun costeBillete(){

}



fun finalscan(scan: Scanner) {
    scan.close()
}