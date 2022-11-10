fun main() {
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)

    amanda.showProfile()
    atiqah.showProfile()
}

class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
        // Fill in code
        val refer =
            if (referrer != null) {
                "who likes to ${referrer?.hobby}"
            } else {
                "Doesn't have a referrer."
            }
        println("Name: $name\nAge: $age\nLikes to $hobby. $refer")
    }
}
