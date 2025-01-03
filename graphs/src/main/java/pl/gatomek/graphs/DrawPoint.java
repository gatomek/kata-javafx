package pl.gatomek.graphs;

record DrawPoint( double x, double y) {
    public static DrawPoint of( double x, double y) {
        return new DrawPoint( x, y);
    }
}
