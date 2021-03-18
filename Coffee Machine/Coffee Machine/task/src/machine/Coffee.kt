package machine

enum class CoffeeType(
    val ID: Int = 0,
    val waterNeeded: Int = 0,
    val milkNeeded: Int = 0,
    val coffeeNeeded: Int = 0,
    val moneyCost: Int = 0
) {
    ESPRESSO(ID = 1, waterNeeded = 250, coffeeNeeded = 16, moneyCost = 4),
    LATTE(ID = 2, waterNeeded = 350, milkNeeded = 75, coffeeNeeded = 20, moneyCost = 7),
    CAPPUCCINO(ID = 3, waterNeeded = 200, milkNeeded = 100, coffeeNeeded = 12, moneyCost = 6),
    NULL();

    companion object {
        fun findByID(ID: Int): CoffeeType {
            for (type in values()) {
                if (type.ID == ID) {
                    return type
                }
            }
            return NULL
        }
    }
}