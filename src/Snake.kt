class Snake() {
    private var parts: MutableList<BodyPart> = ArrayList()
    private var pendingSegments = 0

    init{
        this.reset()
    }

    fun reset() {
        this.dir = Direction.Right
        this.parts.clear()
        parts.add(BodyPart(Position(5, 0), Direction.Right))
        parts.add(BodyPart(Position(4, 0), Direction.Right))
        parts.add(BodyPart(Position(3, 0), Direction.Right))
    }

    var dir: Direction = Direction.Right
        set(value) {
            if (this.dir == Direction.Left && value == Direction.Right) {
                return
            }

            if (this.dir == Direction.Right && value == Direction.Left) {
                return
            }

            if (this.dir == Direction.Up && value == Direction.Down) {
                return
            }

            if (this.dir == Direction.Down && value == Direction.Up) {
                return
            }

            field = value
        }

    fun draw(renderer: ISnakeRenderer){
        for (p in parts){
            renderer.drawBodyPart(p.pos)
        }
    }

    fun intersectsGrid(gridSize: Size): Boolean {
        for(p in parts){
            if (gridSize.isOutside(p.pos)){
                return true
            }
        }
        return false
    }

    fun intersects(pos: Position): Boolean {
        for(p in parts){
            if (pos.isSame(p.pos)){
                return true
            }
        }
        return false
    }

    fun move(){
        var newPart: BodyPart? = null
        if (this.pendingSegments > 0){
            val last = parts.last()
            newPart = BodyPart(last.pos.copy(), last.dir)
            this.pendingSegments--
        }

        this.parts[0].dir = this.dir

        for (i in (this.parts.size-1) downTo 0){
            when (this.parts[i].dir){
                Direction.Left -> this.parts[i].pos = this.parts[i].pos.offset(-1, 0)
                Direction.Right -> this.parts[i].pos = this.parts[i].pos.offset(1, 0)
                Direction.Up -> this.parts[i].pos = this.parts[i].pos.offset(0, -1)
                Direction.Down -> this.parts[i].pos = this.parts[i].pos.offset(0, 1)
            }

            if (i > 0){
                this.parts[i].dir = this.parts[i-1].dir
            }
        }

        if (newPart != null){
            this.parts.add(newPart)
        }
    }

    fun addBodySegment(){
        this.pendingSegments++
    }

    fun isSelfIntersecting(): Boolean {
        for (i in this.parts.indices){
            for (j in this.parts.indices){
                if (i != j){
                    val p1 = this.parts[i]
                    val p2 = this.parts[j]

                    if (p1.pos.isSame(p2.pos)){
                        return true
                    }
                }
            }
        }

        return false
    }

    private data class BodyPart(var pos: Position, var dir: Direction)
}
