package outils

case class Size(length : Int, width : Int) 

case class Pixel(val x : Int, val y : Int) {
  def rescaled_length(zabs_l : Double = 2, l : Int) : Double = {
    return (x - l/2)*zabs_l*2/l
  }
  def rescaled_width(zabs_w : Double = 2, w : Int) : Double = {
    return (y - w/2)*zabs_w*2/w
  }
}