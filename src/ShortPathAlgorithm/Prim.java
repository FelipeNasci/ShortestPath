package ShortPathAlgorithm;

import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;
import java.util.LinkedList;

public class Prim extends Action implements ShortPath {

    private final LinkedList<Edge> priorityListEdge;    //lista prioritaria de aresta

    public Prim() {
        this.priorityListEdge = new LinkedList<>();
        this.forest = new Graph();
    }

    @Override
    public Graph MST(Graph graph) {

        getForest(graph);       //Obtem a florestaz

        //Escolhe um vertice arbitrario do grafo original
        //pesquisa de todos os outros vertices a quem ele se liga
        //Adiciona essas ligacoes em uma lista de arestas
        search(graph.getVertex(0));

        while (priorityListEdge.size() > 0) {

            Vertex vInitial = priorityListEdge.get(0).getBackVertex();
            Vertex vFinal = priorityListEdge.get(0).getNextVertex();
            int value = priorityListEdge.get(0).getValue();

            //Verifica se os vertices foram marcados
            if (!findSet(vInitial.getId()).equals(findSet(vFinal.getId()))) {

                //Atualiza lista para evitar ciclo
                updateList(vInitial.getId(), vFinal.getId());

                //Adiciona uma aresta na floresta
                union(vInitial, vFinal, value);
            }

            priorityListEdge.remove(0);

            search(vFinal);
        }

        sumEdge(forest);
        return forest;

    }

    //percorre todas as arestas ligadas ao vertice
    public void search(Vertex vertex) {

        Edge edge;

        for (int i = 0; i < vertex.lengthEdge(); i++) {
            edge = vertex.getEdge(i);
            addEdge(edge);
        }
    }

    //Adiciona aresta na lista de prioridade
    public void addEdge(Edge edge) {

        int i = 0;
        
        //verifica se v1 e v2 ja foram marcados
        if (findSet(edge.getBackVertex().getId()).equals(findSet(edge.getNextVertex().getId()))) 
            return;
        
        //Insere a aresta ordenando por peso            
        while (i < priorityListEdge.size()) {
            if (priorityListEdge.get(i).getValue() > edge.getValue()) 
                break;
            i++;
        }

        priorityListEdge.add(i, edge);

    }

}
