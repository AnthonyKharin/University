import java.util.List;

public interface GraphRepresentation {
    int countVertex();

    int weight(int i, int j);

    default List<Edge> getEdges(){
        return null;
    }
}
