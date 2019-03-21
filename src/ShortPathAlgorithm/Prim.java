package ShortPathAlgorithm;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;
import java.util.ArrayList;
import java.util.LinkedList;

public class Prim extends Operations implements ShortPath {

    public Prim() {
        this.priorityListEdge = new LinkedList<>();
        this.forest = new Graph();
        this.visitedList = new ArrayList<>();
    }

    @Override
    public Graph MST(Graph graph) {

        getForest(graph);       //Obtem a floresta

        //Escolhe um vertice arbitrario do grafo original
        //pesquisa de todos os outros vertices a quem ele se liga
        //Adiciona essas ligacoes em uma lista de arestas
        search(graph, forest.getVertex(0));

        while (priorityListEdge.size() > 0){
            
            Vertex vInitial = priorityListEdge.get(0).getBackVertex();
            Vertex vFinal = priorityListEdge.get(0).getNextVertex();
            int value = priorityListEdge.get(0).getValue();
            
            //Verifica se os vertices foram marcados
            if (!findSet( vInitial.getId() ).equals(findSet( vFinal.getId() ))) {

                //Atualiza lista para evitar ciclo
                updateList(vInitial.getId(), vFinal.getId());

                //Adiciona uma aresta na floresta
                union(vInitial, vFinal, value);
            }

            priorityListEdge.remove(0);
            
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
            while (i < priorityListEdge.size()) {
                if (priorityListEdge.get(i).getValue() > value) {
                    break;
                }
                i++;
            }

            
            if (!findSet( v1.getId() ).equals(findSet( v2.getId() )))
                priorityListEdge.add(i, new Edge(v1, v2, value));
        }

    }

}
