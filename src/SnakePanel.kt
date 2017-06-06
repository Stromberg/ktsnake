import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JPanel
import javax.swing.Timer

class SnakePanel(val game: Game) : JPanel(), KeyListener, ActionListener {
    private val circleRadius = 20
    private val physicalSize = Size(game.width*this.circleRadius, game.height*this.circleRadius)

    init {
        setPreferredSize(Dimension(physicalSize.width, physicalSize.height))

        val timer = Timer(250, this)
        timer.initialDelay=1500
        timer.start()
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        val renderer = SnakePanel.Renderer(g, this.physicalSize, this.circleRadius)

        game.draw(renderer)
    }

    override fun keyTyped(e: KeyEvent?) {
    }

    override fun keyPressed(e: KeyEvent?) {
        when (e?.keyCode){
            KeyEvent.VK_UP -> game.dir = Direction.Up
            KeyEvent.VK_DOWN -> game.dir = Direction.Down
            KeyEvent.VK_LEFT -> game.dir = Direction.Left
            KeyEvent.VK_RIGHT -> game.dir = Direction.Right
        }
    }

    override fun keyReleased(e: KeyEvent?) {
    }

    override fun actionPerformed(e: ActionEvent?) {
        game.update()
        repaint()
    }

    class Renderer(val g: Graphics?, val physicalSize: Size, val circleRadius: Int) : ISnakeRenderer {
        override fun drawBodyPart(pos: Position) {
            g?.color = Color.BLACK
            g?.fillOval(pos.x*circleRadius, pos.y*circleRadius, circleRadius, circleRadius)
        }

        override fun drawFood(pos: Position) {
            g?.color = Color.GREEN
            g?.fillOval(pos.x*circleRadius, pos.y*circleRadius, circleRadius, circleRadius)
        }

        override fun drawScore(score: Int, bestScore: Int) {
            val msg = "Score: %d (%d)".format(score, bestScore)
            g?.color = Color.RED
            g?.drawString(msg, physicalSize.width-100, physicalSize.height-20)
        }
    }
}