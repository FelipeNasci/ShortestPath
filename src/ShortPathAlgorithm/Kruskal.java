package ShortPathAlgorithm;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;
import java.util.ArrayList;
import java.util.LinkedList;

public class Kruskal extends Action implements ShortPath {
    
    private final LinkedList<Edge> edge;

    public Kruskal() {
        edge = new LinkedList<>();
        forest = new Graph();
        visited = new ArrayList<>();
    }

    @Override
    public Graph MST(Graph graph) {

        getForest(graph);       //Obtem a floresta
        getEdgeGraph(graph);    //obtem a lista de arestas do grafo original

        Vertex u, v;
        int value;

        while (edge.size() > 0) {

            u = edge.get(0).getBackVertex();    //vertice inicial
            v = edge.get(0).getNextVertex();    //vertice final
            value = edge.get(0).value;             //valor da aresta

            //FindSet compara o rotulo do vertice na lista
            //Se os rotulos forem diferentes, os vertices
            //serao conectados na floresta
            if (!findSet(u.id).equals(findSet(v.id))) {
                updateList(u.id, v.id);           //atualiza a lista com os vertices
                union(u, v, value);         //adiciona os vertices a floresta
            }

            edge.remove(0);                 //remove a aresta da lista
        }

        return forest;

    }

    

    //Obtem/copia todas as arestas do grafo original
    //estas arestas ja estao ordenadas
    private void getEdgeGraph(Graph graph) {

        for (int i = 0; i < graph.getLengthEdge(); i++) {

            int id = graph.getEdge(i).getBackVertex().id;
            String label = graph.getEdge(i).getBackVertex().getLabel;
            Vertex v1 = new Vertex(id, label);

            id = graph.getEdge(i).getNextVertex().id;
            label = graph.getEdge(i).getNextVertex().getLabel;
            Vertex v2 = new Vertex(id, label);

            int value = graph.getEdge(i).value;

            edge.add(new Edge(v1, v2, value));
        }
    }

}
