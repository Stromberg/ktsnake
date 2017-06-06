import javax.swing.*;

fun main(args: Array<String>): Unit {
    val f = JFrame("Snake Game")
    f.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    val game = Game(20, 20)
    val panel = SnakePanel(game)
    f.contentPane.add(panel)
    f.addKeyListener(panel)

    f.pack()
    f.isVisible = true
}