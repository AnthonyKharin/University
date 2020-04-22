import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Vector implements GraphRepresentation {

    //int[][] values = new int[][]{
    //        {1, 2, 7},
    //        {1, 5, 10},
    //        {3, 3, 14},
    //        {2, 6, 9},
    //        {3, 4, 15},
    //        {3, 7, 18},
    //        {4, 8, 21},
    //        {5, 6, 8},
    //        {5, 9, 41},
    //        {6, 7, 11},
    //        {6, 9, 44},
    //        {7, 4, 5},
    //        {7, 8, 15},
    //        {7, 10, 16},
    //        {8, 10, 17},
    //        {10, 9, 5},
    //        {10, 5, 50},
    //        {1, 3, 40}};


    int[][] values = new int[][]{
            {7, 1, 25},
            {7, 2, 36},
            {7, 3, 20},
            {7, 4, 15},
            {7, 5, 3},
            {7, 6, 17},
            {1, 2, 28},
            {2, 3, 23},
            {3, 4, 14},
            {4, 5, 6},
            {5, 6, 5},
            {6, 1, 1},
            {5, 8, 4},
            {4, 9, 9},
            {8, 9, 16}};

    @Override
    public int countVertex() {
        TreeSet<Integer> vertexes = new TreeSet<>();
        for (int[] value : values) {
            vertexes.add(value[0]);
            vertexes.add(value[1]);
        }
        return vertexes.size();
    }

    @Override
    public int weight(int i, int j) {
        i++;
        j++;
        for (int[] value : values) {
            if (value[0] == i && value[1] == j ||
                    value[0] == j && value[1] == i) {
                return value[2];
            }
        }
        return -1;
    }

    @Override
    public List<Edge> getEdges() {
        List<Edge> result = new ArrayList<>(values.length);
        for (int[] arr : values) {
            result.add(new Edge(arr[0], arr[1], arr[2]));
        }
        return result;
    }
}
