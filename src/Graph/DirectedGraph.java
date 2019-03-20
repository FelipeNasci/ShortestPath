package Graph;

import java.util.LinkedList;

public class DirectedGraph extends Graph {

    public DirectedGraph() {
        super();
        this.edge = new LinkedList<Edge>();
        this.vertex = new LinkedList<Vertex>();
        this.length = 0;

    }

    //Referencia v1 <-> v2 e vice-versa
    //Adiciona uma aresta na lista
    public void addEdge(Vertex v1, Vertex v2, int value) {
        try {
            
            v1.addVertex(v2, value);    //Referencia v2 em v1

            //Insere a areta ordenando por peso
            int i = 0;
            while (i < edge.size()) {
                if (edge.get(i).getValue() > value)
                    break;
                i++;
            }

            edge.add(i, new Edge(v1, v2, value));

            if (matrix == null) activeMatrix();
            
            //Adiciona os vertices na matriz de adjacencia
            addMatrix(v1.getId(), v2.getId(), value);

        } catch (Exception e) {
            System.out.println("Problemas em addEdge(Vertex Vertex Value)");
        }
    }

    //Apos add os vertices, ativar a matriz de adjacencia
    public void addMatrix(int v1, int v2, int value) {
        matrix[v1][v2] = value;
    }
}
