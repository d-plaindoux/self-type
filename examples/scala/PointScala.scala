// package test

trait Point[T <: Point[T]] { this: T =>
  val x, y: Int

  def init(x: Int, y: Int): T
  def move(dx: Int, dy: Int): T = init(x + dx, y + dy)
  def equal(p: T): Boolean = x == p.x && y == p.y
}

trait PointColore[T <: PointColore[T]] extends Point[T] {  this: T =>
  val c: Int

  override def equal(p: T): Boolean = c == p.c && super.equal(p)
}

class YPoint(val x: Int, val y: Int) extends Point[YPoint] {
  override def init(x: Int, y: Int): YPoint = new YPoint(x, y)
}

class YPointColore(val x: Int, val y: Int, val c: Int) extends PointColore[YPointColore] {
  override def init(x: Int, y: Int): YPointColore = new YPointColore(x, y, c)
}

def equal[P <: Point[P]](p1: P, p2: P): Boolean = p1.equal(p2)

def main(): Unit = {
  val p = new YPoint(0, 0)
  val pc =new YPointColore(0, 0, 0)

  equal(p, p)
  equal(pc, pc)
  // equal(p, pc)   -- rejected
  // equal(pc, p)   -- rejected
}
