package ShortPathAlgorithm;

import Graph.Graph;
import Graph.Vertex;

public class Prim implements ShortPath {

    int[][] matrix;
    Graph graph;

    //Lista dos vertices disponiveis
    boolean[] Q;
    int size_Q;

    @Override
    public Graph MST(Graph graph) {

        this.matrix = graph.matrix.clone();
        this.graph = graph;

        int nulo = -1, inf = Integer.MAX_VALUE, raiz = 0;

        int[] distancia = new int[matrix.length];
        int[] pai = new int[matrix.length];

        //Lista temporaria contendo todos os vertices
        Q = new boolean[matrix.length];
        size_Q = Q.length;

        for (int vertice = 0; vertice < distancia.length; vertice++) {
            distancia[vertice] = inf;     //Distancia do vertice ao pai
            pai[vertice] = nulo;          //indica o vertice filho
            Q[vertice] = true;            //Diz se o vertice está na lista
        }

        distancia[raiz] = 0;
        pai[raiz] = 0;

        Vertex u, v;
        int peso, vertice;

        while (size_Q > 0) {

            u = extractMin(Q, distancia);

            for (int i = 0; i < u.lengthEdge(); i++) {
                v = u.getEdge(i).nextVertex;
                peso = u.getEdge(i).value;
                vertice = v.id;

                if (Q[vertice] == true && peso < distancia[vertice]) {
                    pai[vertice] = u.id;
                    distancia[vertice] = peso;
                }

            }

        }

        //sumEdge(forest(pai, distancia));
        return forest(pai, distancia);

    }

    private Vertex extractMin(boolean[] Q, int[] distancia) {

        int menor = Integer.MAX_VALUE, vertice;
        Vertex vertex = null;

        /**
         * *
         * Q é uma lista temporaria que contem todos os vertices O vetor
         * distancia contem a distancia de um vertice para outro
         *
         */
        for (vertice = 0; vertice < distancia.length; vertice++) {

            if (distancia[vertice] < menor && Q[vertice] == true) {
                menor = distancia[vertice];
                vertex = graph.getVertex(vertice);
            }

        }

        Q[vertex.id] = false;       //Remove o vertice da lista
        size_Q--;                   //Diminui o tamanho da lista

        return vertex;
    }

    private Graph forest(int[] pai, int[] distancia) {

        Vertex[] vertex = new Vertex[pai.length];
        Graph forest = new Graph();
        
        //inicializa os vertices e adicionando-os no grafo
        for (int vertice = 0; vertice < pai.length; vertice++) {
            vertex[vertice] = new Vertex(vertice);
            forest.addVertex(vertex[vertice]);
        }

        for (int vertice = 0; vertice < pai.length; vertice++) {
            
            Vertex A = vertex[vertice];
            Vertex B = vertex[pai[vertice]];
            
            //Se o vertice A for diferente de B adicione-os no grafo
            if(A.id != B.id)
                forest.addEdge(A, B, distancia[vertice]);
        }

        return forest;
    }
}
