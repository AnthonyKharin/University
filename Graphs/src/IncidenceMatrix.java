public class IncidenceMatrix implements GraphRepresentation {

    private int[][] matrix = new int[][]{
            {7, 9, 0, 0, 0, 14, 0, 0, 0},
            {7, 0, 10, 15, 0, 0, 0, 0, 0},
            {0, 9, 10, 0, 11, 0, 2, 0, 0},
            {0, 0, 0, 15, 11, 0, 0, 0, 6},
            {0, 0, 0, 0, 0, 0, 0, 9, 6},
            {0, 0, 0, 0, 0, 14, 2, 9, 0}
    };

    @Override
    public int countVertex() {
        return matrix.length;
    }

    @Override
    public int weight(int i, int j) {
        int result = -1;
        for (int a = 0; a < matrix[0].length; a++) {
            if (matrix[i][a] != 0 && matrix[j][a] != 0) {
                return matrix[i][a];
            }
        }
        return result;
    }
}
