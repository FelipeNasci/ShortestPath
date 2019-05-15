package Graph;

public class Edge {

    public int id;
    public int value;  //Peso da aresta
    public final Vertex backVertex;
    public final Vertex nextVertex;

    public Edge(Vertex backVertex, Vertex nextVertex, int value) {
        this.backVertex = backVertex;   //anterior
        this.nextVertex = nextVertex;   //proximo
        this.value = value;
    }



    public  Vertex getBackVertex() {
        if(backVertex != null)
            return backVertex;
        return null;
    }

    public Vertex getNextVertex() {
        if(nextVertex != null)
            return nextVertex;
        return null;
    }
 
}
