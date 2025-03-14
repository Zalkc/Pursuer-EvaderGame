import java.util.Random;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class MoveAwayPlayerAlgorithm extends AbstractPlayerAlgorithm{

    Random rand = new Random();

    public MoveAwayPlayerAlgorithm(Graph graph) {
        //constructor
        super(graph);
        vertex = ((ArrayList<Vertex>)graph.getVertices()).get(rand.nextInt(graph.size()));
    }

    public Vertex chooseStart() {
        //method to choose a random vertex to start from
        List<Vertex> vertices = new ArrayList<>();
        for (Vertex vertex : graph.getVertices()) {
            vertices.add(vertex);
        }
        Vertex best = vertices.get(rand.nextInt(vertices.size()));
        this.vertex = best;
        return best;
    }

    public Vertex chooseStart(Vertex other) {
        // Method to choose a vertex for the other player to start from
        HashMap<Vertex, Double> distances = graph.distanceFrom(other);
    
        Vertex best = null;
        for (Vertex vertex : graph.getVertices()) {
            if (!vertex.equals(other) && (best == null || distances.get(vertex) > distances.get(best))) {
                best = vertex;
            }
        }
    
        this.vertex = best;
        return best;
    }
    

    public Vertex chooseNext(Vertex otherPlayer) {
        //method to choose the next vertex to move to
        HashMap<Vertex, Double> distances = graph.distanceFrom(otherPlayer);
        double maxDistance = Double.NEGATIVE_INFINITY;
        Vertex next = null;
        if (distances != null) {
            for (Vertex v : vertex.adjacentVertices()) {
                if (v != null && distances.containsKey(v) && !v.equals(otherPlayer)) {
                    Double distance = distances.get(v);
                    if (distance != null && distance > maxDistance) {
                        maxDistance = distance;
                        next = v;
                    }
                }
            }
        }
        vertex = next;
        return vertex;
    }
    
    
}
