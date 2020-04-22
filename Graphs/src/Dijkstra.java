import java.util.*;

public class Dijkstra {

    private GraphRepresentation graph;

    private int countVertex;
    private int startVertex;

    private int[] path;
    private int[] distance;

    public Dijkstra(GraphRepresentation graph, int startVertex) {
        this.graph = graph;
        this.countVertex = graph.countVertex();
        this.startVertex = startVertex;
    }

    //O(1) - best case
    //O(n*g(n)) - worst case
    public int minDistanceBetweenVertex(int endVertex) {
        if (distance == null) {
            solve();
        }
        return distance[endVertex - 1];
    }


    //O(n) - average case
    //O(n*g(n)) - worst case
    public List<Integer> pathBetweenVertex(int endVertex) {
        if (path == null) {
            solve();
        }

        LinkedList<Integer> pathStack = new LinkedList<>();
        for (int i = endVertex - 1; i != startVertex - 1; ) {
            pathStack.addFirst(i + 1);
            i = path[i];
        }
        pathStack.addFirst(startVertex);

        return pathStack;
    }

    //O(1) - best case
    //O(n*g(n)) - worst case
    public int[] minDistanceToAllOtherVertex() {
        if (path == null) {
            solve();
        }
        return distance;
    }

    //O(n*g(n))
    private void solve() {
        int startIndex = startVertex - 1;
        distance = new int[countVertex];
        path = new int[countVertex];

        boolean[] seen = new boolean[countVertex];

        Queue<Integer> order = new ArrayDeque<>(countVertex);

        order.add(startIndex);

        for (int i = 0; i < countVertex; i++) { //O(n)
            distance[i] = i == startIndex ? 0 : Integer.MAX_VALUE;
            path[i] = i;
            seen[i] = false;
        }

        //O(1 * 1 * 1 * (n*g(n)) * 1 * 1 * 1) = O(n*g(n))
        while (!order.isEmpty()) { //O(1)
            int tempVertex = order.poll(); //O(1)
            if (!seen[tempVertex]) { //O(1)
                //Check the edges
                for (int i = 0; i < countVertex; i++) { //O(n*g(n))

                    int weight = graph.weight(tempVertex, i); //O(g(1))

                    if (tempVertex != i && weight != -1) { // O(1)
                        //Check the length of edge
                        if (weight + distance[tempVertex] < distance[i]) { //O(1)
                            distance[i] = weight + distance[tempVertex];
                            path[i] = tempVertex;
                            if (!seen[i]) { //O(1)
                                order.add(i);
                            }
                        }
                    }
                }
                seen[tempVertex] = true;
            }
        }
    }
}
