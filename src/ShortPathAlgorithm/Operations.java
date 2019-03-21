package ShortPathAlgorithm;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;
import Search.VisitList;
import java.util.ArrayList;
import java.util.LinkedList;

public class Operations {

    protected ArrayList<VisitList> visitedList; //Se vertice foi visitado
    protected LinkedList<Edge> priorityListEdge;//lista prioritaria de aresta
    protected Graph forest;                     //Vertices sem aresta

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
            visitedList.add(new VisitList(vertex, label));
        }
    }

    //Obtem todas as arestas do grafo
    protected void getEdgeGraph(Graph graph) {

        for (int i = 0; i < graph.getLengthEdge(); i++) {

            //Referencia de uma ponta da aresta
            int id = graph.getEdge(i).getBackVertex().getId();
            String label = graph.getEdge(i).getBackVertex().getLabel();
            Vertex v1 = new Vertex(id, label);

            //Referencia da outra ponta da aresta
            id = graph.getEdge(i).getNextVertex().getId();
            label = graph.getEdge(i).getNextVertex().getLabel();
            Vertex v2 = new Vertex(id, label);

            int value = graph.getEdge(i).getValue();

            //Sao utilizados novos vertices, para o
            //novo vertice referenciar apenas 01 outro vertice
            priorityListEdge.add(new Edge(v1, v2, value));
        }
    }

    //Exibe a lista de arestas e suas conexoes
    protected void showConections() {
        for (int i = 0; i < priorityListEdge.size(); i++) {

            System.out.print(priorityListEdge.get(i).getBackVertex().getLabel() + " <- ");
            System.out.print(priorityListEdge.get(i).getValue());
            System.out.println(" -> " + priorityListEdge.get(i).getNextVertex().getLabel());
        }
    }


    //Adiciona aresta na lista de prioridade
    public void addPriorityListEdge(Graph graph, int vId) {

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
            while (i < priorityListEdge.size()) {
                if (priorityListEdge.get(i).getValue() > value) {
                    break;
                }
                i++;
            }

            if (!findSet(v1.getId()).equals(findSet(v2.getId()))) {
                priorityListEdge.add(i, new Edge(v1, v2, value));
            }
        }

    }

    //Retorna o rotudo de um vertice (auxilia a verificacao para evitar ciclo)
    protected String findSet(int vertex) {
        return visitedList.get(vertex).getLabel();
    }

    //Une 02 vertices por meio de uma aresta valorada
    protected void union(Vertex u, Vertex v, int value) {
        forest.addEdge(u, v, value);
    }

    //A ideia eh utilizar a lista para evitar ciclo
    protected void updateList(int v1, int v2) {

        String label = visitedList.get(v1).getLabel();

        for (int i = 0; i < visitedList.size(); i++) {
            if (visitedList.get(i).getLabel().equals(label)) {
                visitedList.get(i).setLabel(visitedList.get(v2).getLabel());
            }
        }

    }

    //Exibe a lista de visitados
    protected void showList() {
        for (int i = 0; i < visitedList.size(); i++) {
            System.err.print(visitedList.get(i).getId() + "\t");
            System.err.println(visitedList.get(i).getLabel());
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
