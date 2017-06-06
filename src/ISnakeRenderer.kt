interface ISnakeRenderer {
    fun drawFood(pos: Position)
    fun drawBodyPart(pos: Position)
    fun drawScore(score: Int, bestScore: Int)
}
