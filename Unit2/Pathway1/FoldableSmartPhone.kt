class FoldableSmartPhone(var isFolded: Boolean) : Phone(isScreenLightOn = isFolded) {

    override fun switchOn() {
        if (!isFolded) {
            super.switchOn()
        }
    }

    override fun switchOff() {
        isFolded = true
    }
}


open class Phone(var isScreenLightOn: Boolean = false) {
    open fun switchOn() {
        isScreenLightOn = true
    }

    open fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}
