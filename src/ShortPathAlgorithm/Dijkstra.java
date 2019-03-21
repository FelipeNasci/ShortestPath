package ShortPathAlgorithm;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;
import java.util.ArrayList;
import java.util.LinkedList;

public class Dijkstra extends Operations implements ShortPath {

    private final ArrayList<Integer> distance;
    private final ArrayList<Integer> father;
    private int numberVertex;

    private final int infinit;

    public Dijkstra() {
        this.forest = new Graph();
        this.visitedList = new ArrayList();

        this.distance = new ArrayList<>();
        this.father = new ArrayList<>();
        this.infinit = 10000000;
    }

    @Override
    public Graph MST(Graph graph) {

        getForest(graph);       //Obtem a floresta
        inicialize(graph, graph.getVertex(0));

        //Relaxa todas as arestas ligada ao vertice
        relax(graph, graph.getVertex(1));

        int low, d, value;

        while (numberVertex > 0) {

            low = lowerDist();              //Obtem o vertice de menor distancia
            d = distance.get(low);

            Vertex vInitial = forest.getVertex(father.get(low));
            Vertex vFinal = forest.getVertex(low);
            value = getValueEdge(graph, vInitial.getId(), vFinal.getId());

            if (!findSet(vInitial.getId()).equals(findSet(vFinal.getId()))) {

                //Atualiza lista para evitar ciclo
                updateList(vInitial.getId(), vFinal.getId());

                //Adiciona uma aresta na floresta
                union(vInitial, vFinal, 0);
            }
            relax(graph, vFinal);
        }

        //System.out.println(sumEdge(forest));
        showList();
        return null;
    }

    //procura todas as ligacoes de um vertice
    private void relax(Graph graph, Vertex vertex) {

        try {
            numberVertex--;

            //enquanto tiver ligacao percorra as arestas
            for (int i = 0; i < vertex.lengthEdge(); i++) {
                relaxEdge(graph, vertex.getEdge(i));
            }
        } catch (Exception er) {
            System.err.println("Problemas em Relax");
        }

    }

    private void inicialize(Graph graph, Vertex vIncial) {
        try {
            for (int i = 0; i < graph.getLength(); i++) {
                distance.add(infinit);      //Distancia de todos os pontos eh infinito
                father.add(i, -1);          //Nenhum nó tem pai
            }

            distance.set(vIncial.getId(), 0);
            father.add(vIncial.getId(), 0);
            numberVertex = forest.getLength();
        } catch (Exception e) {
            System.err.println("Problema em inicialize");
        }
    }

    private void relaxEdge(Graph graph, Edge edge) {

        try {
            Vertex v1 = edge.getBackVertex();
            Vertex v2 = edge.getNextVertex();
            int value = edge.getValue();

            int dist1 = distance.get(v1.getId());
            int dist2 = distance.get(v2.getId());

            if (dist1 + value < dist2 || dist2 == infinit) {
                //distancia de v1 para v2 = v1 + valor_aresta
                distance.set(v2.getId(), dist1 + value);
                //pai de v2 eh v1
                father.set(v2.getId(), dist1);
            }
        } catch (Exception e) {
            System.err.println("Problemas em relaxEdge()");
        }
    }

    private int lowerDist() {

        int lower = 1000000000;
        int pos = 0;

        for (int i = 0; i < distance.size(); i++) {

            if (distance.get(i) < lower && distance.get(i) > 0) {
                lower = distance.get(i);
                pos = i;
            }
        }

        return pos;
    }

    private int getValueEdge(Graph graph, int v1, int v2) {

        Vertex v_1 = graph.getVertex(v1);

        for (int i = 0; i < v_1.lengthEdge(); i++) {
            if (v2 == v_1.getEdge(i).getNextVertex().getId()) {
                return v_1.getEdge(i).getValue();
            }
        }
        return 0;
    }
}
