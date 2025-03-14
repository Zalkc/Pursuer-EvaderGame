import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class RandomPlayer extends AbstractPlayerAlgorithm{

    //instance variables
    Random rand = new Random();
    List<Vertex> vertices;
    public RandomPlayer(Graph graph) {
        //constructor
        super(graph);
        vertices = new ArrayList<>();
        for (Vertex vertex : graph.getVertices()) {
            vertices.add(vertex);
        }
    }


    public Vertex chooseStart() {
        //method to choose a random vertex to start from
        Vertex out = vertices.get(rand.nextInt(vertices.size()));
        this.vertex = out;
        return out;
    }

    public Vertex chooseStart(Vertex other){
        //method to choose a vertex for the other player to start from
        this.vertex = vertices.get(rand.nextInt(vertices.size()));
        Vertex out = vertex;
        while (out == other){
            out = vertices.get(rand.nextInt(vertices.size()));
        }

        this.vertex = out;
        return out;
    }

    public Vertex chooseNext(Vertex otherPlayer){
        //method to choose the next vertex to move to
        Vertex out = vertex;
        while (this.vertex.equals(out)){
            out = vertex.adjacentVertices().get(rand.nextInt(vertex.adjacentVertices().size()));
        }
        this.vertex = out;
        return out;
    }
}