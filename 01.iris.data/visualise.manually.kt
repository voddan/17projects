package iris.data.visualise.manually

import java.io.File
import javafx.application.Application
import javafx.collections.*
import javafx.geometry.Orientation
import javafx.scene.chart.*
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
    val file = File("01.iris.data/iris.data.txt")
    val lines = file.readLines()
    val measurments = lines.filterNot { it.isEmpty() }.map { Measurment.parse(it) }

    measurments.forEach { println(it) }

    Application.launch(MyApp::class.java, *args)

}

class MyApp : App(MyView::class)

class MyView(measurments: List<Measurment>) : View() {
    override val root = vbox {
        tableview<Measurment> {
            column("a", Measurment::a)
            column("b", Measurment::b)
            column("c", Measurment::c)
            column("d", Measurment::d)
            column("TYPE", Measurment::type)

            items.setAll(measurments)
        }

        areachart("area chart", NumberAxis(), NumberAxis()) {
            for(m in measurments) {

                series("") {

                }


            }
        }
    }
}

fun <X, Y, ChartType : XYChart<X, Y>> ChartType.data(x: X, y: Y,
                                                     seriesName: String,
                                                     op: ((XYChart.Data<X, Y>).() -> Unit)? = null): XYChart.Data<X, Y> {
    val = data.find { it.name == seriesName }

    val series = XYChart.Series<X, Y>()
    series.name = name
    if (elements != null) series.data = elements
    if (op != null) op(series)
    data.add(series)
    return series
}

fun <X, Y> XYChart.Series<X, Y>.data(x: X, y: Y, extra: Any? = null, op: ((XYChart.Data<X, Y>).() -> Unit)? = null) = XYChart.Data<X, Y>(x, y).apply {
    if (extra != null) extraValue = extra
    data.add(this)
    if (op != null) op(this)
}
