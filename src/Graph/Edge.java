package Graph;

public class Edge {

    private int id;
    private int value;  //Peso da aresta
    private final Vertex backVertex;
    private final Vertex nextVertex;

    public Edge(Vertex backVertex, Vertex nextVertex, int value) {
        this.backVertex = backVertex;   //anterior
        this.nextVertex = nextVertex;   //proximo
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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
