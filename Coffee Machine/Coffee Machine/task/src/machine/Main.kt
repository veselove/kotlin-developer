package machine

fun main() {
     val machine = CoffeeMachine(
          waterLeft = 400,
          milkLeft = 540,
          coffeeLeft = 120,
          cupsLeft = 9,
          moneyLeft = 550
     )
     machine.start()
}
