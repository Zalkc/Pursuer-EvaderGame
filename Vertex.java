import java.util.ArrayList;

public class Vertex {
    //instance variables
    private ArrayList<Edge> edges;

    public Vertex(){
        //constructor
        edges = new ArrayList<Edge>();
    }

    public Edge getEdgeTo(Vertex vertex){
        //method to get the edge to a vertex
        if(vertex == null){
            return null;
        }
        else{
            for(Edge edge : edges){
                if(edge.other(this).equals(vertex)){
                    return edge;
                }
            }
        }
        return null;
    }

    public void addEdge(Edge edge){
        //method to add an edge
        edges.add(edge);
    }

    public boolean removeEdge(Edge edge){
        //method to remove an edge
        if(edges.contains(edge)){
            edges.remove(edge);
        return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<Vertex> adjacentVertices(){
        //method to get the adjacent vertices
        ArrayList<Vertex> adjacentVertices = new ArrayList<Vertex>();
        for(Edge edge : edges){
            adjacentVertices.add(edge.other(this));
        }
        return adjacentVertices;
    }

    public ArrayList<Edge> incidentEdges(){
        return edges;
    }
}