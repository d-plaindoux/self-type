protocol Point {
    var x : Int { get }
    var y : Int { get }

    init(x: Int, y:Int)

    func equal(p:Self) -> Bool
    func move(dx: Int, dy: Int) -> Self
}

protocol ColoredPoint where Self: Point {
    var c : Int { get }

    init(x: Int, y:Int, c : Int)
}

extension Point {
    func equal(p:Self) -> Bool {
        return x == p.x && y == p.y
    }

    func move(dx: Int, dy: Int) -> Self {
        return type(of: self).init(x: x + dx, y : y + dy)
    }
}

extension ColoredPoint {
    func equal(p:Self) -> Bool {
        return c == p.c && x == p.x && y == p.y
    }

    func move(dx: Int, dy: Int) -> Self {
        return type(of: self).init(x: x + dx, y : y + dy, c : c)
    }
}

class FPoint : Point {
    let x: Int
    let y: Int

    required init(x: Int, y:Int) {
        self.x = x
        self.y = y
    }
}

class FColoredPoint : FPoint, ColoredPoint {
    let c : Int

    required init(x: Int, y: Int) {
        self.c = 0
        super.init(x:x, y: y)
    }

    required init(x: Int, y:Int, c: Int) {
        self.c = c
        super.init(x:x, y: y)
    }
}

func main() {
    let p = FPoint(x: 0, y: 0)
    let pc = FColoredPoint(x: 0, y: 0, c: 1)
    p.equal(p: pc)

    let np = p.move(dx: 1, dy: 2)
    np.equal(p: p)

    let npc = pc.move(dx: 1, dy: 2)
    pc.equal(p: npc)
}
