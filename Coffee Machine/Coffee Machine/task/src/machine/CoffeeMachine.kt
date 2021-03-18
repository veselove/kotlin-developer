package machine

class CoffeeMachine(
    var waterLeft: Int = 0,
    var milkLeft: Int = 0,
    var coffeeLeft: Int = 0,
    var cupsLeft: Int = 0,
    var moneyLeft: Int = 0
) {

    private var state: MachineState = MachineState.MAIN_MENU

    fun start() {
        do {
            handleCurrentState()
        } while (state != MachineState.EXIT)
    }

    private fun switchState(state: MachineState) {
        this.state = state
    }

    private fun handleInput(input: String) {
        when (state) {
            MachineState.MAIN_MENU ->
                when (input) {
                    "buy" -> switchState(MachineState.CHOOSE_COFFEE)
                    "fill" -> switchState(MachineState.FILL_MACHINE)
                    "take" -> takeMoney()
                    "remaining" -> printStatus()
                    "exit" -> switchState(MachineState.EXIT)
                }
            MachineState.CHOOSE_COFFEE -> {
                if (input != "back") {
                    buyCoffee(CoffeeType.findByID(input.toInt()))
                }
                switchState(MachineState.MAIN_MENU)
            }
        }
    }

    private fun handleCurrentState() {
        when (state) {
            MachineState.MAIN_MENU -> handleMenuAction()
            MachineState.CHOOSE_COFFEE -> handleCoffeeBuyAction()
            MachineState.FILL_MACHINE -> {
                handleFillAction()
                switchState(MachineState.MAIN_MENU)
            }
        }
    }

    private fun handleMenuAction() {
        val input = readLine("Write action (buy, fill, take, remaining, exit): ")
        handleInput(input)
    }

    private fun handleCoffeeBuyAction() {
        val input = readLine("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        handleInput(input)
    }

    private fun handleFillAction() {
        val water = readLine("Write how many ml of water do you want to add: ").toInt()
        val milk = readLine("Write how many ml of milk do you want to add: ").toInt()
        val coffee = readLine("Write how many grams of coffee beans do you want to add: ").toInt()
        val cups = readLine("Write how many disposable cups of coffee do you want to add: ").toInt()
        waterLeft += water
        milkLeft += milk
        coffeeLeft += coffee
        cupsLeft += cups
    }

    private fun printStatus() {
        println("\nThe coffee machine has:")
        println("$waterLeft of water")
        println("$milkLeft of milk")
        println("$coffeeLeft of coffee beans")
        println("$cupsLeft of disposable cups")
        println("$moneyLeft of money")
    }

    private fun takeMoney() {
        println("\nI gave you $$moneyLeft")
        moneyLeft = 0
    }

    private fun buyCoffee(type: CoffeeType) {
        if(canBuyCoffee(type.waterNeeded, type.milkNeeded, type.coffeeNeeded)) {
            waterLeft -= type.waterNeeded
            milkLeft -= type.milkNeeded
            coffeeLeft -= type.coffeeNeeded
            moneyLeft += type.moneyCost
            cupsLeft--
        }
    }

    private fun canBuyCoffee(waterNeeded: Int, milkNeeded: Int, coffeeNeeded: Int): Boolean {
        return when {
            waterLeft - waterNeeded < 0 -> {
                println("Sorry, not enough water!")
                false
            }
            milkLeft - milkNeeded < 0 -> {
                println("Sorry, not enough milk!")
                false
            }
            coffeeLeft - coffeeNeeded < 0 -> {
                println("Sorry, not enough coffee!")
                false
            }
            else -> {
                println("I have enough resources, making you a coffee!")
                true
            }
        }
    }

    private fun readLine(prompt: String): String {
        print("\n$prompt")
        val line = readLine()!!
        val correctInput = state.canHandleAction(line)
        return if(!correctInput) {
            println("Wrong input! Try again.")
            readLine(prompt)
        } else line
    }
}