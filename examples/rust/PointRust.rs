trait Point {
    fn x(&self) -> u32;
    fn y(&self) -> u32;
    fn new(&self, x: u32, y: u32) -> Self;
    fn move_it(&self, dx: u32, dy: u32) -> Self where Self: std::marker::Sized {
        return self.new(self.x() + dx, self.y() + dy);
    }
    fn equal(&self, p: &Self) -> bool {
        return self.x() == p.x() && self.y() == p.y();
    }
}

trait PointColore where Self: Point {
    fn c(&self) -> u32;
    fn equal(&self, p: &Self) -> bool {
        return self.c() == p.c() && Point::equal(self, p);
    }
}

struct YPoint { x: u32, y: u32 }

impl Point for YPoint {
    fn x(&self) -> u32 { return self.x; }
    fn y(&self) -> u32 { return self.y; }
    fn new(&self, x: u32, y: u32) -> Self {
        return YPoint { x, y };
    }
}

struct YPointColore { p: YPoint, c: u32 }

impl Point for YPointColore {
    fn x(&self) -> u32 { return self.p.x; }
    fn y(&self) -> u32 { return self.p.x; }
    fn new(&self, x: u32, y: u32) -> Self {
        return YPointColore { p: YPoint { x, y }, c: self.c() };
    }
}

impl PointColore for YPointColore {
    fn c(&self) -> u32 { return self.c; }
}

fn main() {
    let p1 = YPointColore { p: YPoint { x: 0, y: 0 }, c: 0 };
    let p2 = p1.move_it(0, 0);

    // let _ = p1.equal(&p2); // Ambiguité lors de la compilation
}