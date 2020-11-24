import java.awt.image.BufferedImage
import scala.swing._
import scala.io.StdIn.readLine

import outils._
import fractals._
import pals._
import renderer._


object GuiProgramOne {
  def main(args: Array[String]) {

    val size = Size(700, 700)
    val length = size.length
    val width = size.width
    val zabs_l = 2.5
    val zabs_w = 2.5
    val seuil = 4
    val iterMax = 100

    def prog() : Int = {

      val image = new BufferedImage(length, width, BufferedImage.TYPE_INT_RGB)
      val g = image.createGraphics()

      val panel = new Panel {  
      override def paint(g:Graphics2D) {g.drawImage(image,0,0,null)}
      preferredSize = new Dimension(length, width) 
        }
      val frame = new MainFrame{
      title = "Essai panel"
      contents = panel
        }

      val input = readLine("[1] MandelBrot Set \n[2] Julia Set\n[0] Quit\n")

      if(input == "1") {
        val rend = new Renderer(image, frame, length, width, iterMax, seuil)
        rend.mandel_gene_rec()

        //To keep for non parralel fractal rendering
        val panelfin = new Panel{  
        override def paint(g:Graphics2D){
        g.drawImage(image,0,0,null)
            }
        preferredSize = new Dimension(length, width) 
          }
        frame.contents = panelfin
        frame.open() 

        prog()
        }
      else if(input == "2") {
        val c = { 
        val juliac = readLine("Which Julia set do you want to see (1 to 7)\n")
        if (juliac == "1") new Complex(0.285,0.01)
        else if (juliac == "2")new Complex(0.285, 0.013)
        else if (juliac == "3")new Complex(- 0.8, 0.156)
        else if (juliac == "4")new Complex(-0.4 ,0.6)
        else if (juliac == "5")new Complex(0.355 ,0.355)
        else if (juliac == "6")new Complex(-0.4 ,-0.59)
        else if (juliac == "7")new Complex(-0.54 ,0.54)
        else new Complex(0,0)}

        val rend = new Renderer(image, frame, length, width, iterMax, seuil, c)
        rend.julia_gene_rec()

        //To keep for non parralel fractal rendering
        val panelfin = new Panel{  
        override def paint(g:Graphics2D){
        g.drawImage(image,0,0,null)
            }
        preferredSize = new Dimension(length, width) 
          }
        frame.contents = panelfin
        frame.open() 

        prog()
          }
      else if(input == "0") {print("Bye \n"); return 0}
      else {print("Value out of range ! \n"); prog()}
    }
    prog()
  }
}

