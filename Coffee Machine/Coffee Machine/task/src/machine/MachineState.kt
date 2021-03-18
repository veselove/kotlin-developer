package machine

enum class MachineState(private val validPattern: String = "") {
    MAIN_MENU("buy|fill|take|remaining|exit"),
    CHOOSE_COFFEE("[1-3]|back"),
    FILL_MACHINE("\\d+"),
    EXIT();

    fun canHandleAction(action: String) = action.matches(Regex(validPattern))
}