
import Graph.DirectedGraph;
import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;
import ShortPathAlgorithm.Action;
import ShortPathAlgorithm.ShortPath;
import java.util.ArrayList;

public class Dijkstra extends Action implements ShortPath {

    private final ArrayList<Integer> distance;      //Lista de distancia entre vertices
    private final ArrayList<Integer> father;        //Lista com o pai de cada vertice
    private final ArrayList<Integer> valueEdge;     //Lista com valores das arestas

    private final int inf = 100000;
    private int aux;                                //numero de vertices do grafo

    public Dijkstra() {
        super();
        this.forest = new DirectedGraph();
        this.distance = new ArrayList();
        this.father = new ArrayList();
        this.valueEdge = new ArrayList<>();
    }

    @Override
    public Graph MST(Graph graph) {
        try {

            int vertice;
            Vertex v2;

            getForest(graph);
            inicialize(graph);
            aux = forest.getLength();

            Vertex v1 = graph.getVertex(0);

            distance.set(v1.getId(), 0);    //Distancia de v1 para v1 eh igual a 0
            father.set(v1.getId(), 0);      //O primeiro vertice nao tem pai, ou ele mesmo

            while (aux > 0) {

                //por meio desta variavel podemos 
                //identificar o vertice e seu pai
                vertice = shorDistance();       //aresta que possui menor distancia 

                v2 = graph.getVertex(vertice);
                relax(v2);
                visited.get(v2.getId()).setVisited(true);   //Marca o vertice como visitado

                Vertex vInitial = forest.getVertex(father.get(vertice));
                Vertex vFinal = forest.getVertex(vertice);
                int value = valueEdge.get(vertice);

                //Se os vertices nao formam ciclo, conecte-os
                if (!findSet(vInitial.getId()).equals(findSet(vFinal.getId()))) {

                    //Atualiza lista para evitar ciclo
                    updateList(vInitial.getId(), vFinal.getId());

                    //Adiciona uma aresta na floresta
                    union(vInitial, vFinal, value);
                }
                aux--;
            }

        } catch (Exception e) {
            System.err.println("Problema na MST Djkistra");
        }

        //showTable();

        return forest;
    }

    private void inicialize(Graph graph) {
        for (int i = 0; i < graph.getLength(); i++) {
            distance.add(inf);
            father.add(-1);
            visited.get(i).setVisited(false);
            valueEdge.add(0);
        }
    }

    private void relaxEdge(Edge edge) {

        try {
            Vertex v1 = edge.getBackVertex();
            Vertex v2 = edge.getNextVertex();
            int value = edge.getValue();

            int dist1 = distance.get(v1.getId());
            int dist2 = distance.get(v2.getId());

            if (dist1 + value < dist2) {
                //distancia de v1 para v2 = v1 + peso_aresta
                distance.set(v2.getId(), dist1 + value);
                //pai de v2 eh v1
                father.set(v2.getId(), v1.getId());
                //Adiciona o valor da aresta original
                valueEdge.set(v2.getId(), value);
            }
        } catch (Exception e) {
            System.out.println("erro em relaxEdge()");
        }
    }

    private void relax(Vertex vertex) {
        try {
            for (int i = 0; i < vertex.lengthEdge(); i++) {
                relaxEdge(vertex.getEdge(i));
            }
        } catch (Exception e) {
            System.err.println("erro em relax()");
        }
    }

    //retorna a posicao do vertice que possui menor distancia
    private int shorDistance() {
        try {

            int lower = inf;
            int pos = 0;

            for (int i = 0; i < distance.size(); i++) {

                if (!visited.get(i).isVisited() && distance.get(i) < lower) {

                    lower = distance.get(i);
                    pos = i;

                }
            }
            return pos;
        } catch (Exception e) {
            System.err.println("erro em shortDistance");
        }
        return 0;
    }

    private void showTable() {
        System.out.println("\t Pai \t Distancia");
        for (int i = 0; i < distance.size(); i++) {
            System.err.print(i + "\t");
            System.err.print(father.get(i) + " \t ");
            System.err.println(distance.get(i));

        }
    }
}
