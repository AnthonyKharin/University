public class ConnectivityMatrix implements GraphRepresentation {

    private int[][] matrix = new int[][]{
            {0, 7, 9, -1, -1, 14},
            {7, 0, 10, 15, -1, -1},
            {9, 10, 0, 11, -1, 2},
            {-1, 15, 11, 0, 6, -1},
            {-1, -1, -1, 6, 0, 9},
            {14, -1, 2, -1, 9, 0}};

    @Override
    public int countVertex() {
        return matrix.length;
    }

    @Override
    public int weight(int i, int j) {
        return matrix[i][j];
    }
}
