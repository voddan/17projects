package iris.data.visualise.manually

import java.io.File
import javafx.application.Application
import javafx.geometry.Orientation
import javafx.scene.chart.NumberAxis
import tornadofx.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Measurment(val a: Double,
                      val b: Double,
                      val c: Double,
                      val d: Double,
                      val type: Int) {
    companion object {
        private val _types = mutableListOf<String>()
        val types: List<String> = _types

        fun parse(str: String): Measurment {
            val args = str.split(',')
            val a = args[0].toDouble()
            val b = args[1].toDouble()
            val c = args[2].toDouble()
            val d = args[3].toDouble()

            val t = args[4]
            val type = if( t in _types) _types.indexOf(t) else {
                _types.add(t)
                _types.lastIndex
            }

            return Measurment(a, b, c, d, type)
        }
    }
}


fun main(args: Array<String>) {
    val lines = File("C:\\Users\\Daniil_Vodopian\\Documents\\DataScience\\17projects\\01.iris.data\\iris.data.txt").readLines()
    val measurments = lines.filterNot { it.isEmpty() }.map { Measurment.parse(it) }

    measurments.forEach { println(it) }
}