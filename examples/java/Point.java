package test;

public class PointJava {

    private PointJava() {
    }

    interface Point<Self extends Point<Self>> {
        int x();

        int y();

        Self init(int x, int y);

        default Self move(int dx, int dy) {
            return init(x() + dx, y() + dy);
        }

        default boolean equal(Self s) {
            return s.x() == x() && s.y() == y();
        }
    }

    interface PointColore<Self extends PointColore<Self>> extends Point<Self> {
        int c();

        default boolean equal(Self s) {
            return s.c() == c() && Point.super.equal(s);
        }
    }

    // Recursive definition

    static class YPoint implements Point<YPoint> {
        final int x, y;

        public YPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int x() {
            return x;
        }

        @Override
        public int y() {
            return y;
        }

        @Override
        public YPoint init(int x, int y) {
            return new YPoint(x, y);
        }
    }

    static class YPointColore implements PointColore<YPointColore> {
        final int x, y, c;

        public YPointColore(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        public int x() {
            return x;
        }

        public int y() {
            return y;
        }

        public int c() {
            return c;
        }

        @Override
        public YPointColore init(int x, int y) {
            return new YPointColore(x, y, c());
        }
    }

    static <S extends Point<S>> boolean equal(S p1, S p2) {
        return p1.equal(p2);
    }

    public static void main(String[] args) {
        final Point p1 = new YPoint(0, 0);
        final PointColore p2 = new YPointColore(0, 0, 0);

        equal(p1, p2);
    }
}