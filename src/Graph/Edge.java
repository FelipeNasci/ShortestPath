package Graph;

public class Edge {
    private int value;  //Peso da aresta
    private Vertex vertex;

    public Edge(Vertex vertex, int value){
        this.vertex = vertex;
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }
    
    
}
