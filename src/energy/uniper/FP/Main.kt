package energy.uniper.FP

import energy.uniper.FP.util.CarFactory

/**
 * Wir befinden uns im Jahr 2040, futuristische Parkhäuser haben die oberhand gegenüber normalen Parkhäuser übernommen,
 * in dem noch die Menschen ihre Parkplätze bestimmen konnten. Heute werden die Autos in eine garagenartige Öffnung gefahren, das Tor senkt sich
 * und beim wiederöffnen ist das Auto verschwunden. Keiner Weiß, was dann mit den Autos geschieht.
 *
 * Einer internen Miliz ist es allerdings gelungen, die Prozesse des renomierten, futuristischen Autohaus zu stehlen und versuchen dies
 * nun für ihr Konzept des futuristischen Autohaus 2 zu implementieren.
 *
 * Folgendes steht in der gestohlen Datei:
 *
 * 0100101010001010 ...  s jdf lsgd ksfalal aslsddf sdf sdfkksf
 * ... sobald ein Auto in der Garage abgestellt wird, werden folgende angaben ermittelt:
 *
 * * Fahzeugmarke, Fahrzeugtyp, Kennzeichen, Name des Halters, Wert des Autos, Uhrzeit an dem das Auto abgegeben wurde, Uhrzeit der Abholung.
 * * Sobald die Klasse Car implementiert wurde, brauchen wir eine Methode um Fahrzeuge mit den nötigen Informationen zu erhalten,
 * * bitte versucht eine Lösung zu finden.
 * Das Fahrzeug wird nun nach den ermittelten Werten auf folgende Weiße abgestellt:
 * Ist das Fahrzeug über 100.000€ Wert oder ist der Halter eine zur Partei gehörige Persönlichkeit, wird sie auf Ebene 0 abgestellt.
 * Für jeden weitere 10.000€ die das Auto weniger Wert ist, wird das Fahrzeug auf eine Ebene höher abgestellt.
 * Ist das Fahrzeug unter 10.000€ Wert, so wird diese auf der Ebene 10 abgestellt.
 * Ist eine Ebene mit ihren 20 Parkplätzen voll besetzt, so wird eine Ebene nach oben gerutscht, also wird bspw. ein Fahrzeug, das auf Ebene 0 gehört, auf Ebene 1 geparkt und so weiter.
 *
 * Ist das Parkhaus für die höchste Ebene voll, so wird angezeigt, dass der Halter das Auto in diesem Parkhaus nicht abstellen kann, das gleiche gilt, wenn beispielsweise
 * ein Fahrzeug auf Parkebene 9 geparkt werden soll, aber Ebene 9 und 10 voll sind.
 * Desweiteren werdem die Autos nach Kennzeichen sortiert geparkt.
 * Der 1 Parkplatz ist dabei die erste Stelle einer Liste usw.
 * Für jedes Auto ab Ebene 5, will die Partei erfahren wer der Halter des Fahrzeugs ist, und bei der nächsten Kontrollzone, des Inhaber und das Fahzeug kontrollieren,
 * dabei wird beim abstellen die Fahrzeuge mit der Wahrscheinlichkeit Ebene*0.01 mit "KONTROLLE", ansonsten mit "KEINE KONTROLLE" makiert, wobei hier auf die Verschiebungsregel geachtet
 * werden muss. Gehört ein Fahrzeug eigentlich auf Ebene 4, wurde aber Ebene 5 abgestellt, greift diese Regel nicht.
 * Für die Ebene 0 wird jedes Fahrzeug, das zu keinem Politiker gehört, mit der Wahrscheinlichkeit  0.001 mit "KONTROLLE" markiert.
 *
 * Holt einer der Kunden sein Auto ab, so wird
 * der Preis pro Stunde folgend berechnet: Stunde * 1€ * sqrt(Ebene) (Wurzel).
 * Dann wird das Auto aus der Ebene entfernt und in der Garage gestellt. Nach dem der Kunde bezahlt hat,
 * wird das Auto mit "ABGEHOLT" markiert und aus dem Speicher gelöscht.
 *
 * Inzwischen gibt es 3 futuristische Parkhäuser, und das Programm benennt diese mit 1,2,3 und managed alle diese Parkhäuser, mit dem selben Prinzip.
 *
 * Nachdem die Miliz diese brisanten Informationen gesehen hat, bleibt ihnen nichts anderes übrig, als die Idee zu verwerfen und die Regierung zu stürzen....
 */


fun main(args: Array<String>) {
    val factory = CarFactory()
    val listOfCars = factory.createNewCar()

}
