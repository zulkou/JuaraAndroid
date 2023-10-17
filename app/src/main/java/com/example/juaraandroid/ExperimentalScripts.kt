package com.example.juaraandroid

//SmartDevice class
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(val name: String, val category: String) {

    var deviceStatus = "online"
        protected set

    open val deviceType = "unknown"

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)
    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    override fun turnOn() {
        super.turnOn()
        println(
            "$name is turned on. Speaker volume set to $speakerVolume and channel number is " +
                    "set to $channelNumber."
        )
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }

    fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }

    fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber.")
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart Light"
    private var brightnessLevel by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name is turned on. The brightness level is $brightnessLevel.")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("$name turned off")
    }

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }
}

class SmartHome(val smartTvDevice: SmartTvDevice, val smartLightDevice: SmartLightDevice) {

    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }

    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    private var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}

//fun main() {
//    val smartHome = SmartHome(
//        SmartTvDevice(deviceName = "Android TV", deviceCategory = "Entertainment"),
//        SmartLightDevice(deviceName = "Google light", deviceCategory = "Utility")
//    )
//
//    smartHome.turnOnTv()
//    smartHome.turnOnLight()
//    println("Total number of devices currently turned on: ${smartHome.deviceTurnOnCount}")
//    println()
//
//    smartHome.increaseTvVolume()
//    smartHome.changeTvChannelToNext()
//    smartHome.increaseLightBrightness()
//    println()
//
//    smartHome.turnOffAllDevices()
//    println("Total number of devices currently turned on: ${smartHome.deviceTurnOnCount}.")
//}

//Practice Kotlin Fundamentals
// 1. Mobile Notifications
//fun main() {
//    val morningNotification = 51
//    val eveningNotification = 135
//
//    printNotificationSummary(morningNotification)
//    printNotificationSummary(eveningNotification)
//}


fun printNotificationSummary(numberOfMessages: Int) {
    if (numberOfMessages < 100) {
        println("You have $numberOfMessages notifications.")
    } else {
        println("Your phone is blowing up! You have 99+ notifications.")
    }
}
// 2. Movie-ticket price
//fun main() {
//    val child = 5
//    val adult = 28
//    val senior = 87
//
//    val isMonday = true
//
//    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
//    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
//    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
//}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    return when {
        age <= 12 -> 15
        age <= 60 -> if(isMonday) 25 else 30
        age <= 100 -> 20
        else -> -1
    }
}

// 3. Temperature converter
//fun main() {
//    printFinalTemperature(
//        initialMeasurement = 27.0,
//        initialUnit = "Celsius",
//        finalUnit = "Fahrenheit",
//        conversionFormula = { it * 9 / 5 + 32 }
//    )
//    printFinalTemperature(
//        initialMeasurement = 350.0,
//        initialUnit = "Kelvin",
//        finalUnit = "Celsius",
//        conversionFormula = { it - 273.15 }
//    )
//    printFinalTemperature(
//        initialMeasurement = 10.0,
//        initialUnit = "Fahrenheit",
//        finalUnit = "Kelvin",
//        conversionFormula = { (it - 32) * 5 / 9 + 273.15 }
//    )
//}

fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}


// 4. Song Catalog
class Song(val title: String, val artist: String, val yearPublished: Int, val playCount: Int) {
    val isPopular: Boolean
        get() = playCount >= 1000

    fun printDescription() {
        println("$title, performed by $artist, was released in $yearPublished.")
    }
}

//fun main() {
//    val favSong = Song(
//        title = "N95",
//        artist = "Kendrick Lamar",
//        year = 2022,
//        playCount = 322_000_000
//    )
//
//    favSong.printDescription()
//    println(favSong.isPopular)
//}

// 5. Internet Profile
//fun main() {
//    val amanda = Person("Amanda", 33, "play tennis", null)
//    val atiqah = Person("Atiqah", 28, "climb", amanda)
//
//    amanda.showProfile()
//    atiqah.showProfile()
//}


class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
        println(
            "Name: $name \n" +
            "Age: $age \n" +
            if(hobby != null) {
                "Likes to $hobby."
            } else {
                "who doesn't have a hobby."
            } +
            if(referrer != null) {
                "Has a referrer named ${referrer.name}," +
                        if(referrer.hobby != null) {
                            " who likes to ${referrer.hobby}."
                        } else {
                            " who doesn't have a hobby."
                        }
            } else {
                "Doesn't have a referrer."
            } +
            "\n"
        )
    }
}

// 6. Foldable phone
open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }

    fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone(var isFolded: Boolean = true): Phone() {
    override fun switchOn() {
        if (!isFolded) {
            isScreenLightOn = true
        }
    }

    fun fold() {
        isFolded = true
    }

    fun unfold() {
        isFolded = false
    }
}

//fun main() {
//    val foldablePhone = FoldablePhone()
//
//    foldablePhone.switchOn()
//    foldablePhone.checkPhoneScreenLight()
//    foldablePhone.unfold()
//    foldablePhone.switchOn()
//    foldablePhone.checkPhoneScreenLight()
//}

// 7. Special auction
//fun main() {
//    val winningBid = Bid(5000, "Private Collector")
//
//    println("Item A is sold at ${auctionPrice(winningBid, 2000)}.")
//    println("Item B is sold at ${auctionPrice(null, 3000)}.")
//}

class Bid(val amount: Int, val bidder: String)

fun auctionPrice(bid: Bid?, minimumPrice: Int): Int {
    return bid?.amount ?: minimumPrice
}

// Quiz Logic
class FillInTheBlankQuestion(
    val questionText: String,
    val answer: String,
    val difficulty: String
)
class TrueOrFalseQuestion(
    val questionText: String,
    val answer: Boolean,
    val difficulty: String
)
class NumericQuestion(
    val questionText: String,
    val answer: Int,
    val difficulty: String
)
data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)
enum class Difficulty {
    EASY, MEDIUM, HARD
}
interface ProgressPrintable {
    val progressText: String
    fun printProgressBar()
}
class Quiz {
    val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
    val question3 = Question<Int>("How many days are there between full moons?", 28, Difficulty.HARD)

    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }
    val progressText: String
        get() = "${answered} of ${total} answered"
    fun printProgressBar() {
        repeat(Quiz.answered) { print("▓") }
        repeat(Quiz.total - Quiz.answered) { print("▒") }
        println()
        println(progressText)
    }
    fun printQuiz() {
        question1.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question2.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question3.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
    }
}

//fun main() {
//    Quiz().apply {
//        printQuiz()
//    }
//}

//Task 1
data class Event(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int,
)
enum class Daypart {
    MORNING, AFTERNOON, EVENING
}

class ToDoEvent() {
    val event1 = Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0)
    val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
    val event3 = Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
    val event4 = Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
    val event5 = Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10)
    val event6 = Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)

    val events = mutableListOf<Event>(event1, event2, event3, event4, event5, event6)

    val shortEvents = events.filter { it.durationInMinutes < 60 }

    val groupedEvents = events.groupBy { it.daypart }

    val durationOfEvent = if (events[0].durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }
    fun toDoEventClassPrint() {
        println("You have ${shortEvents.size} short events.")

        groupedEvents.forEach { (daypart, events) ->
            println("$daypart: ${events.size} events")
        }

        println("Last event of the day: ${events.last().title}")

        println("Duration of first event of the day: $durationOfEvent")
    }
}

fun main() {

}