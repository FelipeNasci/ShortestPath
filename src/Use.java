
import Graph.Graph;
import Graph.Vertex;
import Search.Search;
import Search.DepthSearch;


public class Use {

    public static void main(String args[]) throws InterruptedException {
        
        Graph graph = new Graph();
        Search search = new DepthSearch();
        
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
        graph.addEdge(zero, quatro, 90);
                graph.addEdge(zero, quatro, 90);

        search.search(graph);
        //graph.showVertices();
        
    }

}
