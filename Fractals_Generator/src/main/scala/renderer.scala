package renderer

import scala.swing._
import java.awt.image.BufferedImage

import outils._
import fractals._
import pals._
import scala.annotation.tailrec

class Renderer(val img : BufferedImage, val frame : MainFrame, val length : Int, val width : Int, val iterMax : Int, val seuil: Int, val c : Complex = new  Complex(0, 0)){
     def mandel_gene(){
        for {
            x <- 0 until length
            y <- 0 until width
        }{
            val p = new Pixel(x, y)
            val z = new Complex(p.rescaled_length(l=length), p.rescaled_width(w=width))
            val mandel = new MandelBrot(iterMax, seuil, z)
            val pal = new Palette(mandel.iter_colorM(z))
            img.setRGB(x, y, pal.ElectricPalette()) 

            // PARALLEL DISPLAY

            /* val panelupdate = new Panel{  
            override def paint(g:Graphics2D){g.drawImage(img,0,0,null)}
            preferredSize = new Dimension(length,width)
                }; 
            frame.contents = panelupdate; 
            frame.open()  */
        }
    }   

    def julia_gene(){
        for {
            x <- 0 until length
            y <- 0 until width
        }{
            val p = new Pixel(x, y)
            val z = new Complex(p.rescaled_length(l=length), p.rescaled_width(w=width))
            val julia = new Julia(iterMax, seuil, z, c)
            val pal = new Palette(julia.iter_colorJ(z))
            img.setRGB(x, y, pal.DeepBluePalette()) 

            //PARALLEL DISPLAY 

            /* val panelupdate = new Panel{  
            override def paint(g:Graphics2D){g.drawImage(img,0,0,null)}
            preferredSize = new Dimension(length,width)
                }; 
            frame.contents = panelupdate; 
            frame.open() */
        }
    }  
    
    @tailrec
    final def mandel_gene_rec(x : Int = 0, y : Int = 0){
        if (x < length) {
            if (y < width) {
                val p = new Pixel(x, y)
                val z = new Complex(p.rescaled_length(l=length), p.rescaled_width(w=width))
                val mandel = new MandelBrot(iterMax, seuil, z)
                val pal = new Palette(mandel.iter_colorM(z))
                img.setRGB(x, y, pal.PinkyPalette()) 
                mandel_gene_rec(x, y+1)
            }
            else mandel_gene_rec(x+1, 0)
        }
    }

    @tailrec
    final def julia_gene_rec(x : Int = 0, y : Int = 0){
        if (x < length) {
            if (y < width) {
                val p = new Pixel(x, y)
                val z = new Complex(p.rescaled_length(l=length), p.rescaled_width(w=width))
                val julia = new Julia(iterMax, seuil, z, c)
                val pal = new Palette(julia.iter_colorJ(z))
                img.setRGB(x, y, pal.ElectricPalette()) 
                julia_gene_rec(x, y+1)
            }
            else julia_gene_rec(x+1, 0)
        }
    }
}
