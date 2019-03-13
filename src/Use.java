
import Graph.Graph;
import Graph.Vertex;


public class Use {

    public static void main(String args[]) throws InterruptedException {
        
        Graph graph = new Graph();
        
        Vertex zero = new Vertex(0);
        Vertex um = new Vertex(1);
        Vertex dois = new Vertex(2);
        Vertex tres = new Vertex(3);
        Vertex quatro = new Vertex(4);
        
        
        graph.addVertex(zero);
        graph.addVertex(um);
        graph.addVertex(dois);
        graph.addVertex(tres);
        graph.addVertex(quatro);
        
        graph.addEdge(zero, um, 50);
        graph.addEdge(zero, tres, 150);
        graph.addEdge(um, tres, 70);
        graph.addEdge(um, dois, 20);

        graph.showVertices();
        
    }

}
