package ShortPathAlgorithm;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;
import Search.VisitList;
import java.util.ArrayList;
import java.util.LinkedList;

public class Prim implements ShortPath {

    Graph forest;

    private final LinkedList<Edge> listEdge;
    private final ArrayList<VisitList> visitedList;

    public Prim() {
        listEdge = new LinkedList<>();
        forest = new Graph();
        visitedList = new ArrayList<>();
    }

    @Override
    public Graph MST(Graph graph) {

        getForest(graph);       //Obtem a floresta

        //adicionar na lista todas as arestas do vertice inicial
        
        //faz a pesquisa com o vetice inicial
        //envia o vertice da floresta
        search(graph, forest.getVertex(0));

        while (listEdge.size() > 0){
            
            Vertex vInitial = listEdge.get(0).getBackVertex();
            Vertex vFinal = listEdge.get(0).getNextVertex();
            int value = listEdge.get(0).getValue();
            
            if (!findSet( vInitial.getId() ).equals(findSet( vFinal.getId() ))) {

                //Atualiza lista para evitar ciclo
                updateList(vInitial.getId(), vFinal.getId());

                //Adiciona uma aresta na floresta
                union(vInitial, vFinal, value);
            }

            listEdge.remove(0);
            
            search(graph, vFinal);
        }
        
        sumEdge(forest);
        return forest;

    }

    public void search(Graph graph, Vertex vertex) {
        //Envia o grafo original
        //Envia o id do vertice recem descoberto da floresta
        addEdge(graph, vertex.getId());
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

    //Adiciona aresta na lista de prioridade
    public void addEdge(Graph graph, int vId) {

        //Vertice do grafo original
        Vertex vertex = graph.getVertex(vId);

        //Vertices da floresta
        Vertex v1 = visitedList.get(vId).getVertex();
        Vertex v2;

        int value, i, vertex_id;

        for (int k = 0; k < vertex.lengthEdge(); k++) {

            //obtem o id do vertice do grafo original
            vertex_id = vertex.getEdge(k).getNextVertex().getId();

            //Encontra o vertice na floresta atraves 
            //do id do vertice original
            v2 = visitedList.get(vertex_id).getVertex();

            //valor da aresta
            value = vertex.getEdge(k).getValue();

            i = 0;
            //Insere a aresta ordenando por peso            
            while (i < listEdge.size()) {
                if (listEdge.get(i).getValue() > value) {
                    break;
                }
                i++;
            }

            
            if (!findSet( v1.getId() ).equals(findSet( v2.getId() )))
                listEdge.add(i, new Edge(v1, v2, value));
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

            listEdge.add(new Edge(v1, v2, value));
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

    private String findSet(int u) {
        return visitedList.get(u).getLabel();
    }

    private void union(Vertex u, Vertex v, int value) {
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
        for (int i = 0; i < listEdge.size(); i++) {

            System.out.print(listEdge.get(i).getBackVertex().getLabel() + " <- ");
            System.out.print(listEdge.get(i).getValue());
            System.out.println(" -> " + listEdge.get(i).getNextVertex().getLabel());
        }
    }

    //Retorna a soma dos pesos de todas as arestas da arvore geradora
    public void sumEdge(Graph graph) {

        int count = 0;
        for (int i = 0; i < graph.getLengthEdge(); i++) {
            count += graph.getEdge(i).getValue();
        }
        System.out.println("A soma dos pesos das arestas eh: " + count);
    }

}
