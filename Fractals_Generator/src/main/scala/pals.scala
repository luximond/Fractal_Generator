package pals


class Color(val i1 : Int, val i2 : Int, val i3 : Int, val x : Int){
  def to_rgb() : Int = {
    return x << i1 | x << i2 | x << i3
  }
}

class Palette(val i : Int){
  def DeepBluePalette() : Int = {
    val col = new Color(4, 10, 4, i)
    return col.to_rgb()
  }
  def ElectricPalette() : Int = {
    val col = new Color(2, 1, 10, i)
    return col.to_rgb()
  }
  def PinkyPalette() : Int = {
    val col = new Color(21, 6, 1, i)
    return col.to_rgb()
  }
}