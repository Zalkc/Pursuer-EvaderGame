public abstract class AbstractPlayerAlgorithm{

    //attributes
    public Graph graph;
    public Vertex vertex;
    public AbstractPlayerAlgorithm(Graph graph){
        //constructor
        this.graph = graph;
        this.vertex = new Vertex();
    }

    public Graph getGraph(){
        //method to get the graph
        return this.graph;
    }

    public Vertex getCurrentVertex(){
        //method to get the current vertex
        return this.vertex;
    }

    public void setCurrentVertex(Vertex vertex){
        //method to set the current vertex
        this.vertex = vertex;
    }

    public abstract Vertex chooseStart(); //choose a random vertex to start from

    public abstract Vertex chooseStart(Vertex other); //choose a vertex for the other player to start from

    public abstract Vertex chooseNext(Vertex otherPlayer);//choose the next vertex to move to

}