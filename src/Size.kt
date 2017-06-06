data class Size(val width: Int, val height: Int){
    fun isOutside(pos: Position): Boolean {
        return (pos.x < 0) || (pos.x >= width) || (pos.y < 0) || (pos.y >= height)
    }
}

