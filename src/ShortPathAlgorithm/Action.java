/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShortPathAlgorithm;

import Graph.Graph;
import Graph.Vertex;
import Search.VisitList;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class Action {

    protected Graph forest;
    protected ArrayList<VisitList> visited;

    public Action() {
        forest = new Graph();
        visited = new ArrayList();
    }

    //Obtem uma floresta - Vertices do grafo original
    protected void getForest(Graph graph) {
        int id;
        String label;

        for (int i = 0; i < graph.getLength(); i++) {

            id = graph.getVertex(i).getId();
            label = graph.getVertex(i).getLabel();

            Vertex vertex = new Vertex(id, label);

            //A floresta contem vertices diferentes do grafo original
            forest.addVertex(vertex);
            visited.add(new VisitList(vertex, label));
        }
    }

    //Retorna o rotudo de um vertice (auxilia a verificacao para evitar ciclo)
    protected String findSet(int vertex) {
        return visited.get(vertex).getLabel();
    }

    //Une 02 vertices por meio de uma aresta valorada
    protected void union(Vertex u, Vertex v, int value) {
        forest.addEdge(u, v, value);
    }

    //A ideia eh utilizar a lista para evitar ciclo
    protected void updateList(int v1, int v2) {

        String label = visited.get(v1).getLabel();

        for (int i = 0; i < visited.size(); i++) {
            if (visited.get(i).getLabel().equals(label)) {
                visited.get(i).setLabel(visited.get(v2).getLabel());
            }
        }

    }

    //Exibe a lista de visitados
    protected void showList() {
        for (int i = 0; i < visited.size(); i++) {
            System.err.print(visited.get(i).getId() + "\t");
            System.err.println(visited.get(i).getLabel());
        }
    }

    //Retorna a soma dos pesos de todas as arestas da arvore geradora
    public int sumEdge(Graph graph) {

        int count = 0;
        for (int i = 0; i < graph.getLengthEdge(); i++) {
            count += graph.getEdge(i).getValue();
        }

        System.out.println("A soma dos pesos das arestas eh: " + count);
        return count;
    }

}
