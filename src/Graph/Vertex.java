package Graph;

import java.util.LinkedList;

public class Vertex {

    private int id;
    private LinkedList<Edge> listEdge;

    public Vertex(int id) {
        this.id = id;
        listEdge = new LinkedList<Edge>();
    }

    public void addVertex(Vertex vertex, int value) {
        //Se liga a outro vertice por meio de uma aresta ponderada
        listEdge.add(new Edge(vertex, value));
    }

    //Exibe todos os outros vertices conectados
    public void showVertices() {
        //System.out.print("Vertice " + getId() + " Se conecta com: ");

        for (int i = 0; i < listEdge.size(); i++) {

            System.out.println(getId() + " <-- " +
                    listEdge.get(i).getValue() +
                    " --> " + listEdge.get(i).getVertex().getId());
            
            //System.out.print("Vertice " + ListVertex.get(i).getId() + " ");
            //System.out.print(" Aresta " + ListVertex.get(i).getEdge().getValue() + " ");

        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
