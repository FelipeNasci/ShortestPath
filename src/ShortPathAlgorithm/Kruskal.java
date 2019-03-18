package ShortPathAlgorithm;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kruskal implements ShortPath {

    private Graph forest;
    LinkedList<Edge> edge;

    public Kruskal() {
        edge = new LinkedList<>();
        forest = new Graph();
    }

    @Override
    public void run(Graph graph) {

        getForest(graph);       //Obtem a floresta
        getEdgeGraph(graph);    //obtem a lista de arestas do grafo

        int v1, v2, value;
        for (int i = 0; i < forest.getLength(); i++){
            System.err.println(forest.getEdge(i));
            
            
        }
        
            forest.showVertices();
  
        
        
        
    }
    
    
    //Obtem todas as arestas do grafo
    private void getEdgeGraph(Graph graph) {
        for (int i = 0; i < graph.getLengthEdge(); i++) {
            edge.add(graph.getEdge(i));
        }
    }

    //Obtem uma floresta
    private void getForest(Graph graph) {
        int id;
        String label;

        for (int i = 0; i < graph.getLength(); i++) {
            id = graph.getVertex(i).getId();
            label = graph.getVertex(i).label;

            forest.addVertex(new Vertex(id, label));
        }
    }

    
    
    
    
    private void showConections() {
        for (int i = 0; i < edge.size(); i++) {

            System.out.print(edge.get(i).getBackVertex().label + " <- ");
            System.out.print(edge.get(i).getValue());
            System.out.println(" -> " + edge.get(i).getNextVertex().label);
        }
    }

    

    public void addEdge(Graph graph) {

        int count = 0;
        for (int i = 0; i < graph.getLength(); i++) {
            count += graph.getEdge(i).getValue();
        }
        System.out.println(count);
    }

}
