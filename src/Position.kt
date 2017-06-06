data class Position(val x: Int, val y: Int){

    fun isSame(other: Position): Boolean {
        return this.x == other.x && this.y == other.y
    }

    fun offset(dx: Int, dy: Int): Position {
        return this.copy(this.x + dx, this.y + dy)
    }
}