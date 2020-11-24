package fractals

import outils._

case class Complex(val re:Double, val im:Double)

class MandelBrot(val iterMax : Int, val seuil: Int, val z : Complex, val c : Complex = new Complex(0, 0)) {
  def compute_iterM(za : Double = z.re, zb : Double= z.im, ca : Double = c.re, cb : Double = c.im, i : Int = 0) : Int = {
      if (ca*ca - cb*cb > seuil) i // exceed the theshold so not of Mandelbrot set
      else if (i >= iterMax) iterMax // still under a yhreshold, so is of Mandelbrot set
      else compute_iterM(za, zb, ca*ca - cb*cb + za, 2*ca*cb + zb,i + 1)
    }  
  def iter_colorM(z: Complex) : Int = {
        val i = compute_iterM(za = z.re, zb = z.im, i = 0)
        val colorIdx = 
          if (i < iterMax) (255 - (i * 255) / iterMax).toInt
          else 0
        return colorIdx
    }
}

class Julia(val iterMax : Int, val seuil: Int, val z : Complex, val c : Complex) {
  def compute_iterJ(za : Double = z.re, zb : Double= z.im, ca : Double = c.re, cb : Double = c.im, i : Int = 0) : Int = {
        if (za*za - zb*zb > seuil) i
        else if (i >= iterMax) iterMax
        else compute_iterJ(za*za - zb*zb + ca,  2*za*zb + cb,ca ,cb ,i + 1)
    }  

    def iter_colorJ(z : Complex) : Int = {
        val i = compute_iterJ(za = z.re, zb=z.im, i = 0)
        val colorIdx = 
          if (i < iterMax) (255-((i * 255) / iterMax)).toInt
          else 0
        return colorIdx
    }
} 