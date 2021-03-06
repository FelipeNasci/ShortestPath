package Graph;

import java.util.LinkedList;

public class Vertex {

    public int id;
    public LinkedList<Edge> listEdge;
    public String getLabel;

    public Vertex(int id) {
        this.id = id;
        this.getLabel = "Vertice nao rotulado";
        listEdge = new LinkedList<>();
    }

    //Construtor vertice com rotulo
    public Vertex(int id, String label) {
        this(id);
        this.getLabel = label;
    }

    protected void addVertex(Vertex vertex, int value) {

        //Nos garante que o conjunto de arestas estaja ordenado
        int i = 0;

        while (i < listEdge.size()) {
            if (listEdge.get(i).value > value) {
                break;
            }
            i++;
        }

        //Se liga a outro vertice por meio de uma aresta ponderada
        listEdge.add(i, new Edge(this, vertex, value));
    }

    //Exibe todos os outros vertices conectados
    protected void showVertices() {

        for (int i = 0; i < listEdge.size(); i++) {
            System.out.println(id + " <-- "
                    + listEdge.get(i).value
                    + " --> " + listEdge.get(i).getNextVertex().id);
        }
    }



    public Edge getEdge(int i) {

        if (lengthEdge() > 0) {
            return listEdge.get(i);
        }

        return null;
    }

    public int lengthEdge() {
        return listEdge.size();
    }

}
