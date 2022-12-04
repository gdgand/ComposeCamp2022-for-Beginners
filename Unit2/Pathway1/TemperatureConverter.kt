fun main() {
    // Fill in the code.
    val fromCtoF = { C: Double ->
        C * 9 / 5 + 32
    }

    val fromKtoC = { K: Double ->
        K - 273.15
    }

    val fromFtoK = { F: Double ->
        (F + 459.67) * 5 / 9
    }
    printFinalTemperature(27.0, "Celsius", "Fahrenheit", fromCtoF)
    printFinalTemperature(350.0, "Kelvin", "Celsius", fromKtoC)
    printFinalTemperature(10.0, "Fahrenheit", "Kelvin", fromFtoK)

}

fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement =
        String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}
