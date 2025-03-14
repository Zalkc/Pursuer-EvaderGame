    public class Edge {
        //instance variables
        private Vertex x;
        private Vertex y;
        private double distance;

        public Edge(Vertex x, Vertex y, double distance){
            //constructor
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.x.addEdge(this);
            this.y.addEdge(this);
        }

        public double distance(){
            //method to get the distance
            return this.distance;
        }

        public Vertex other(Vertex vertex){
            //method to get the other vertex
            if(vertex == null){
                return null;
            }
            if(vertex == this.x){
                return this.y;
            }
            if(vertex == this.y){
                return this.x;
            }
            return null;
        }

        public Vertex[] vertices(){
            //method to get the vertices
            Vertex[] vertices = new Vertex[2];
            vertices[0] = this.x;
            vertices[1] = this.y;
            return vertices;
        }
    }
