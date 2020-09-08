package test

interface Point<Self : Point<Self>> {
    val x: Int;
    val y: Int

    fun new(x: Int, y: Int): Self

    fun move(dx: Int, dy: Int) = new(x + dx, y + dy)
    fun equal(s: Point<Self>) = s.x == x && s.y == y
}

interface PointColore<Self : PointColore<Self>> : Point<Self> {
    val c: Int

    fun equal(s: PointColore<Self>) = s.c == c && super.equal(s)
}

// Recursive definition

class YPoint(override val x: Int, override val y: Int) : Point<YPoint> {
    override fun new(x: Int, y: Int): YPoint = YPoint(x, y)
}

class YPointColore(override val x: Int, override val y: Int, override val c: Int) :
        PointColore<YPointColore> {
    override fun new(x: Int, y: Int): YPointColore = YPointColore(x, y, c)
}

fun <P : Point<P>> List<P>.move(dx: Int, dy: Int): List<P> =
        this.map { it.move(dx, dy) }

fun <P : Point<P>> equal(p1: P, p2: P): Boolean =
        p1.equal(p2)

fun List<Point<*>>.move2(dx: Int, dy: Int): List<Point<*>> =
        this.map { it.move(dx, dy) }

fun main() {
    val p = YPoint(0, 0)
    val cp = YPointColore(0, 0, 0)
    val l = listOf(p, cp).move2(1, 2)

    cp.equal(cp)
}
