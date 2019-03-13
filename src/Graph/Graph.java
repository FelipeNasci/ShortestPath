package Graph;

import java.util.LinkedList;

public class Graph {

    private LinkedList<Vertex> vertex;
    
    public Graph (){
        vertex = new LinkedList<Vertex>();
    }

    public void addVertex(Vertex vertex) {
        this.vertex.add(vertex);
    }
    
    public void addEdge(Vertex v1, Vertex v2, int value){
        v1.addVertex(v2, value);
        v2.addVertex(v1, value);    //Esta linha pode ser omitida caso o grafo seja dirigido
    }
    
    //Retorna um vertice com um id como argumento
    public Vertex getVertex(int id) {

        for (int i = 0; i < vertex.size(); i++) {
            if(vertex.get(i).getId() == id)
                return vertex.get(i);
        }

        return null;
    }

    public void showVertices() throws InterruptedException {
        for (int i = 0; i < vertex.size(); i++) {
            System.err.println(getVertex(i).getId());
            getVertex(i).showVertices();
            Thread.sleep(100);
        }
    }
}
