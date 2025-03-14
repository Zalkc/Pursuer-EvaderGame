import java.util.Random;
import java.util.HashMap;
import java.util.List;

public class ExtensionAlgorithm extends AbstractPlayerAlgorithm {
    private Random picker;

    public ExtensionAlgorithm(Graph graph) {
        //constructor
        super(graph);
        picker = new Random();
        vertex = graph.getVertices().get(picker.nextInt(graph.size()));
    }

    public Vertex chooseStart(){
        //method to choose a random vertex to start from
        vertex = graph.getVertices().get(picker.nextInt(graph.size()));
        return vertex;
    }

    public Vertex chooseStart(Vertex other){
        //method to choose a vertex for the other player to start from
        HashMap<Vertex, Double> distances = graph.distanceFrom(other);
        Vertex minVertex = null;

        for(Vertex vertex : graph.getVertices()){
            if(vertex != other && (minVertex == null || distances.get(vertex) < distances.get(minVertex))){
                minVertex = vertex;
            }
        }

        vertex = minVertex;
        return minVertex;
    }

    public Vertex chooseNext(Vertex otherPlayer){
        //method to choose the next vertex to move to
        if(vertex.adjacentVertices().contains(otherPlayer)){
            vertex = otherPlayer;
            return vertex;
        }
        HashMap<Vertex, Double> distances = graph.distanceFrom(otherPlayer);
        Vertex nextVertex = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for(Vertex v : vertex.adjacentVertices()){
            double dist = distances.getOrDefault(v, Double.POSITIVE_INFINITY);
            if (dist < minDistance) {
                minDistance = dist;
                nextVertex = v;
            }
        }
    
        if(nextVertex != null && distances.get(nextVertex) > 1){
            vertex = nextVertex;
        }
        else{
            List<Vertex> adjacentVertices = vertex.adjacentVertices();
            if (!adjacentVertices.isEmpty()) {
                vertex = adjacentVertices.get(picker.nextInt(adjacentVertices.size()));
            }
        }
        return vertex;
    }
    
}
