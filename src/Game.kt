import java.util.*

class Game(val width: Int, val height: Int){
    private var snake = Snake()
    private var food = Food(Position(0, 0))
    private val r = Random()
    private var score = 0
    private var bestScore = 0

    init {
        reset()
    }

    private fun reset() {
        snake.reset()
        this.bestScore = maxOf(this.bestScore, this.score)
        this.score = 0
        placeFood()
    }

    fun draw(renderer: ISnakeRenderer){
        snake.draw(renderer)
        food.draw(renderer)
        renderer.drawScore(this.score, this.bestScore)
    }

    var dir = Direction.Right

    fun update(){
        snake.dir = this.dir
        snake.move()
        checkForCollisions()
    }

    private fun checkForCollisions(){
        if (snake.intersectsGrid(Size(width, height)) || snake.isSelfIntersecting()){
            reset()
            return
        }

        if (snake.intersects(food.pos)) {
            this.score++
            placeFood()
            snake.addBodySegment()
        }
    }

    private fun placeFood() {
        var x = r.nextInt(this.width - 1) // Random x/y positions
        var y = r.nextInt(this.height - 1)
        this.food.pos = Position(x, y)
    }
}
