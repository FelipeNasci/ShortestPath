package ShortPathAlgorithm;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;
import Search.VisitList;
import java.util.ArrayList;
import java.util.LinkedList;

public class Kruskal implements ShortPath {

     Graph forest;
    private final LinkedList<Edge> edge;
    private final ArrayList<VisitList> visitedList;

    public Kruskal() {
        edge = new LinkedList<>();
        forest = new Graph();
        visitedList = new ArrayList<>();
    }

    @Override
    public Graph MST (Graph graph) {

        getForest(graph);       //Obtem a floresta
        getEdgeGraph(graph);    //obtem a lista de arestas do grafo original

        int u, v, value;

        while (edge.size() > 0) {

            u = edge.get(0).getBackVertex().getId();    //vertice inicial
            v = edge.get(0).getNextVertex().getId();    //vertice final
            value = edge.get(0).getValue();             //valor da aresta

            //FindSet compara o rotulo do vertice na lista
            //Se os rotulos forem diferentes, os vertices
            //serao conectados na floresta
            if( !findSet(u).equals(findSet(v))  ){
                updateList(u, v);           //atualiza a lista com os vertices
                union(u, v, value);         //adiciona os vertices a floresta
            }

            edge.remove(0);                 //remove a aresta da lista
        }
        
        sumEdge(forest);                    //Soma o peso das arestas
        
    return forest;
        
    }

    //A ideia eh utilizar a lista para evitar ciclo
    private void updateList(int v1, int v2) {

        String label = visitedList.get(v1).getLabel();

        for (int i = 0; i < visitedList.size(); i++) {
            if (visitedList.get(i).getLabel().equals(label)) {
                visitedList.get(i).setLabel(visitedList.get(v2).getLabel());
            }
        }

    }

    //Obtem todas as arestas do grafo
    private void getEdgeGraph(Graph graph) {

        for (int i = 0; i < graph.getLengthEdge(); i++) {

            int id = graph.getEdge(i).getBackVertex().getId();
            String label = graph.getEdge(i).getBackVertex().getLabel();
            Vertex v1 = new Vertex(id, label);

            id = graph.getEdge(i).getNextVertex().getId();
            label = graph.getEdge(i).getNextVertex().getLabel();
            Vertex v2 = new Vertex(id, label);

            int value = graph.getEdge(i).getValue();

            edge.add(new Edge(v1, v2, value));
        }
    }

    //Obtem uma floresta
    private void getForest(Graph graph) {
        int id;
        String label;

        for (int i = 0; i < graph.getLength(); i++) {

            id = graph.getVertex(i).getId();
            label = graph.getVertex(i).getLabel();

            Vertex vertex = new Vertex(id, label);

            //A floresta contem vertices diferentes do grafo original
            forest.addVertex(vertex);
            visitedList.add(new VisitList(vertex, label));
        }
    }

    private String findSet(int u){
        return visitedList.get(u).getLabel();
    }
    
    private void union(int u, int v, int value){
        forest.addEdge(u, v, value);
    }
    //Exibe a lista de visitados
    
    private void showList() {
        for (int i = 0; i < visitedList.size(); i++) {
            System.err.print(visitedList.get(i).getId() + "\t");
            System.err.println(visitedList.get(i).getLabel());
        }
    }

    private void showConections() {
        for (int i = 0; i < edge.size(); i++) {

            System.out.print(edge.get(i).getBackVertex().getLabel() + " <- ");
            System.out.print(edge.get(i).getValue());
            System.out.println(" -> " + edge.get(i).getNextVertex().getLabel());
        }
    }

    //Retorna a soma dos pesos de todas as arestas da arvore geradora
    public void sumEdge(Graph graph) {

        int count = 0;
        for (int i = 0; i < graph.getLengthEdge(); i++) {
            count += graph.getEdge(i).getValue();
        }
        System.out.println(count);
    }

}
