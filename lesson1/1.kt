interface GUIFactory {
    fun createButton(): Button
    fun createCheckbox(): Checkbox
}

class WinFactory : GUIFactory {
    override fun createButton(): Button = WinButton()
    override fun createCheckbox(): Checkbox = WinCheckbox()
}

class MacFactory : GUIFactory {
    override fun createButton(): Button = MacButton()
    override fun createCheckbox(): Checkbox = MacCheckbox()
}

interface Button {
    fun paint()
}

interface Checkbox {
    fun paint()
}

class WinButton : Button {
    override fun paint() {
        println("Render a button in a Windows style")
    }
}

class MacButton : Button {
    override fun paint() {
        println("Render a button in a macOS style")
    }
}

class WinCheckbox : Checkbox {
    override fun paint() {
        println("Render a checkbox in a Windows style")
    }
}

class MacCheckbox : Checkbox {
    override fun paint() {
        println("Render a checkbox in a macOS style")
    }
}

fun main() {
    val winFactory: GUIFactory = WinFactory()
    val button: Button = winFactory.createButton()
    button.paint()  
}
