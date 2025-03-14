import java.util.Random;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class MoveTowardsPlayerAlgorithm extends AbstractPlayerAlgorithm{

    Random rand = new Random();

    public MoveTowardsPlayerAlgorithm(Graph graph){
        //constructor
        super(graph);
        vertex = ((ArrayList<Vertex>)graph.getVertices()).get(rand.nextInt(graph.size()));
    }

    public Vertex chooseStart(){
        //method to choose a random vertex to start from
        List<Vertex> vertices = new ArrayList<>();
        for (Vertex vertex : graph.getVertices()) {
            vertices.add(vertex);
        }
        Vertex best = vertices.get(rand.nextInt(vertices.size()));
        this.vertex = best;
        return best;
    }

    public Vertex chooseStart(Vertex other){
        //method to choose a vertex for the other player to start from
        HashMap<Vertex, Double> distances = graph.distanceFrom(other);
        double minDistance = Double.POSITIVE_INFINITY;
        Vertex best = null;
    
        for(Vertex v : graph.getVertices()){
            if(v != other){
                double dist = distances.getOrDefault(v, Double.POSITIVE_INFINITY);
                if(dist < minDistance){
                    minDistance = dist;
                    best = v;
                }
            }
        }
        vertex = best;
        return best;
    }
    

    public Vertex chooseNext(Vertex otherPlayer){
        //method to choose the next vertex to move to
        HashMap<Vertex, Double> distances = graph.distanceFrom(otherPlayer);
        double minDistance = Double.POSITIVE_INFINITY;
        Vertex next = null;
    
        for(Vertex v : vertex.adjacentVertices()){
            double dist = distances.getOrDefault(v, Double.POSITIVE_INFINITY);
            if(dist < minDistance){
                minDistance = dist;
                next = v;
            }
        }
        vertex = next;
        return next;
    }
    
    
}