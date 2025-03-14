/*
file name:      TestEdgesAndVertexClass.java
Authors:        Alexander Zhelonkin
last modified:  12/8/23

How to run:     java TestEdgesAndVertexClass
*/

public class TestEdgesAndVertexClass {
    public static void main(String[] args) {
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Edge e1 = new Edge(v1, v2, 1.0);

        if(e1.distance() == 1.0){
            System.out.println("e1.distance() == 1.0");
        }
        else{
            System.out.println("e1.distance() != 1.0");}
        if(e1.other(v1) == v2){
            System.out.println("e1.other(v1) == v2");
        }
        else{
            System.out.println("e1.other(v1) != v2");}
        if(e1.other(v2) == v1){
            System.out.println("e1.other(v2) == v1");
        }
        else{
            System.out.println("e1.other(v2) != v1");
        }
        if(e1.vertices()[0] == v1){
            System.out.println("e1.vertices()[0] == v1");
        }
        else{
            System.out.println("e1.vertices()[0] != v1");
        }
        if(e1.vertices()[1] == v2){
            System.out.println("e1.vertices()[1] == v2");
        }
        else{
            System.out.println("e1.vertices()[1] != v2");
        }
        if(v1.getEdgeTo(v2) == e1){
            System.out.println("v1.getEdgeTo(v2) == e1");
        }
        else{
            System.out.println("v1.getEdgeTo(v2) != e1");
        }
        v1.addEdge(e1);
        if(v1.getEdgeTo(v2) == e1){
            System.out.println("v1.getEdgeTo(v2) == e1");
        }
        else{
            System.out.println("v1.getEdgeTo(v2) != e1");
        }

        v1.removeEdge(e1);
        v2.removeEdge(e1);


        if(v1.getEdgeTo(v2) == null){
            System.out.println("v1.getEdgeTo(v2) == null");
        }
        else{
            System.out.println("v1.getEdgeTo(v2) != null");
        }
        if(v2.getEdgeTo(v1) == null){
            System.out.println("v2.getEdgeTo(v1) == null");
        }
        else{
            System.out.println("v2.getEdgeTo(v1) != null");
        }
       if(e1.vertices()[0] == null){
            System.out.println("e1.vertices()[0] == null");
        }
        else{
            System.out.println("e1.vertices()[0] != null");
        }
        if(e1.vertices()[1] == null){
            System.out.println("e1.vertices()[1] == null");
        }
        else{
            System.out.println("e1.vertices()[1] != null");
       }
    }
}
