package pl.gatomek.graphs;

record GraphPoint( double x, double y) {
    public static GraphPoint of( double x, double y) {
        return new GraphPoint( x, y);
    }
}
