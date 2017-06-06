class Food(var pos: Position) {

    fun draw(renderer: ISnakeRenderer){
        renderer.drawFood(this.pos)
    }
}
