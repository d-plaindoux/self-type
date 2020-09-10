class point (x:int) (y:int) =
    object (self : 'self)
        val x = x
        val y = y
        method x = x
        method y = y

        method init x y = {< x = x ; y = y >}
        method move dx dy = self#init (x + dx) (y + dy)
        method equal (p:'self) = x = p#x && y = p#y
    end;;

class point_colore (x:int) (y:int) (c:int) =
    object (self: 'self)
        inherit point x y as super

        val c = c
        method c = c

        method init x y = {< x = x ; y = y; c = c >}
        method equal (p:'self) = c = p#c && super#equal p
    end;;
