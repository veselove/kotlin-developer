import java.lang.Math.random

class Player(val id: Int, val name: String, val hp: Int) {
    companion object {
        fun create(name: String): Player{
            var newPlayer = Player(random().toInt(), name, 100)
            return newPlayer
        }
    }
}