import java.util.ArrayList;
import java.util.Comparator;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;

public class Graph {
    //instance variables
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    private int size;

    public Graph(){
        //default constructor
        this(0);
    }

    public Graph(int n){
        //constructor
        this(n, 0.0);
    }

    public Graph(int n, double probability){
        //constructor
        Random picker = new Random();
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        this.size = n;

        for (int i = 0; i < n; i++){
            vertices.add(new Vertex());
        }
        for (Vertex vertex1: vertices){
            for (Vertex vertex2: vertices){
                if (!vertex2.equals(vertex1) && vertex2.getEdgeTo(vertex1) == null && picker.nextDouble() <= probability){
                    addEdge(vertex2, vertex1, 1.0);
                }
            }
        }
    }

public Graph( String filename ) {
    //constructor

    	try {
    		//Setup for reading the file
    		FileReader fr = new FileReader(filename);
    		BufferedReader br = new BufferedReader(fr);

    		//Get the number of vertices from the file and initialize that number of verticies
			vertices = new ArrayList() ;
        	Integer numVertices = Integer.valueOf( br.readLine().split( ": " )[ 1 ] ) ;
			for ( int i = 0 ; i < numVertices ; i ++ ) {
				vertices.add( new Vertex() );
			}

			//Read in the edges specified by the file and create them
        	edges = new ArrayList() ;
			String header = br.readLine(); //We don't use the header, but have to read it to skip to the next line
			//Read in all the lines corresponding to edges
        	String line = br.readLine();
       		while(line != null){
       			//Parse out the index of the start and end vertices of the edge
 	           	String[] arr = line.split(",");
 	           	Integer start = Integer.valueOf( arr[ 0 ] ) ;
 	           	Integer end = Integer.valueOf( arr[ 1 ] ) ;
 	           	
 	           	//Make the edge that starts at start and ends at end with weight 1
 	           	Edge edge = new Edge( vertices.get( start ) , vertices.get( end ) , 1. ) ;
 				//Add the edge to the set of edges for each of the vertices
 				vertices.get( start ).addEdge( edge ) ;
				vertices.get( end ).addEdge( edge ) ;
				//Add the edge to the collection of edges in the graph
            	this.edges.add( edge );
            	
            	//Read the next line
            	line = br.readLine();
            }
        	// call the close method of the BufferedReader:
        	br.close();
        	System.out.println( this.edges );
      	}
      	catch(FileNotFoundException ex) {
        	System.out.println("Graph constructor:: unable to open file " + filename + ": file not found");
      	}
      	catch(IOException ex) {
        	System.out.println("Graph constructor:: error reading file " + filename);
      	}
	}

    public int size(){
        //method to get the size
        return this.size;
    }

    public ArrayList<Vertex> getVertices(){
        //method to get the vertices
        return this.vertices;
    }

    public ArrayList<Edge> getEdges(){
        //method to get the edges
        return this.edges;
    }

    public Vertex addVertex(){
        //method to add a vertex
        Vertex v = new Vertex();
        this.vertices.add(v);
        this.size++;
        return v;
    }

    public Edge addEdge(Vertex u, Vertex v, double distance){
        //method to add an edge
        Edge newEdge = new Edge(u, v, distance);
        u.addEdge(newEdge);
        v.addEdge(newEdge);
        edges.add(newEdge);
        return newEdge;
    }

    public Edge getEdge(Vertex u, Vertex v){
        //method to get an edge
        for (Edge edge: edges){
            if (edge.other(v).equals(u)){
                return edge;
            }
        }
        return null;
    }


    public boolean remove(Vertex vertex){
        //method to remove a vertex
        if (vertices.contains(vertex)){
            vertices.remove(vertex);
            for (Edge edge: vertex.incidentEdges()){
                edge.other(vertex).removeEdge(edge);
            }
            size --;
            return true;
        }
        return false;
    }


    public boolean remove(Edge edge) {
        //method to remove an edge
        if (edge == null) {
            return false;
        }
    
        Vertex[] vertices = edge.vertices();
        for (Vertex vertex : vertices) {
            vertex.removeEdge(edge);
        }
    
        edges.remove(edge);
    
        return true;
    }

    public HashMap<Vertex, Double> distanceFrom(Vertex source) {
        HashMap<Vertex, Double> distances = new HashMap<>();
        if (source == null || !vertices.contains(source)) {
            // Handle invalid source vertex
            return distances;
        }
    
        distances.put(source, 0.0);
        for (Vertex vertex : vertices) {
            if (!vertex.equals(source)) {
                distances.put(vertex, Double.POSITIVE_INFINITY);
            }
        }
    
        Comparator<Vertex> distanceComparator = Comparator.comparingDouble(distances::get);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(distanceComparator);
        priorityQueue.offer(source);
    
        HashSet<Vertex> visited = new HashSet<>();
        visited.add(source);
    
        while (!priorityQueue.isEmpty()) {
            Vertex currentVertex = priorityQueue.poll();
            visited.add(currentVertex);
    
            for (Edge edge : currentVertex.incidentEdges()) {
                double newDistance = distances.get(currentVertex) + edge.distance();
    
                Vertex otherVertex = edge.other(currentVertex);
                if (otherVertex == null) {
                    continue; // Skip null vertices
                }
    
                if (!visited.contains(otherVertex) && newDistance < distances.get(otherVertex)) {
                    distances.put(otherVertex, newDistance);
                    priorityQueue.offer(otherVertex);
                }
            }
        }
        return distances;
    }
}
