package org.example.controllers
import java.util.*
data class PrecioBilletes(
    var zonas : String,
    var billeteSenzillo : Float,
    var tcasual : Float ,
    var tusual : Float ,
    var tfamiliar : Float ,
    var tjove : Float
)
fun main() {
    val billetes : Array<PrecioBilletes> = arrayOf(
        PrecioBilletes(
            "zona1",
            2.40f,
            11.35f,
            40.00f,
            10.00f,
            80.00f
        ),
        PrecioBilletes(
            "zona2",
            2.40f*1.3125f,
            11.35f*1.3125f,
            40.00f*1.3125f,
            10.00f*1.3125f,
            80.00f*1.3125f
        ),
        PrecioBilletes(
            "zona3",
            2.40f*1.8443f,
            11.35f*1.8443f,
            40.00f*1.8443f,
            10.00f*1.8443f,
            80.00f*1.8443f
        )
    )
    val scan = inicioscan()
    val billete = tipoBillete(scan)
    val zona = tipoZona(scan)
    val precio = costeBillete(zona,billete,billetes)
    finalscan(scan)
}
fun inicioscan(): Scanner {
    return Scanner(System.`in`).useLocale(Locale.UK)
}
fun tipoBillete(scan: Scanner):String{
    println("Quin bitllet desitja adquirir?")
    println("1- Bitllet Senzill")
    println("2 - TCasual")
    println("3 - TUsual")
    println("4 - TFamiliar")
    println("5 - TJove")
    val scan=Scanner(System.`in`)
    val tipoBillete = scan.nextInt().toString()
    scan.nextLine().lowercase()
    return tipoBillete
}

fun tipoZona(scan: Scanner):String{
    println("Quina zona vol viatjar?")
    println("1")
    println("2")
    println("3")
    val scan=Scanner(System.`in`)
    var tipoZona = scan.nextInt().toString()
    return tipoZona
}
fun costeBillete(zonas: String, billete:String, billetes: Array<PrecioBilletes>){
    var calculoPrecio = 0f
    for (i in billetes.indices) {
        if (zonas == "zona1" && billete == "billete senzillo" || billete == "tcasual" || billete == "tfamiliar" || billete == "tjove") {

        } else if (zonas == "zona2" && billete == "billete senzillo" || billete == "tcasual" || billete == "tfamiliar" || billete == "tjove") {

        } else if (zonas == "zona3" && billete == "billete senzillo" || billete == "tcasual" || billete == "tfamiliar" || billete == "tjove") {

        }
        else{
            println("ERROR")
        }
        println(calculoPrecio)
    }
}
fun mostrarTicket(){

}
fun pagar(){

}

fun finalscan(scan: Scanner) {
    scan.close()
}
