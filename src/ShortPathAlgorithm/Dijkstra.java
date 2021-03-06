
import Graph.DirectedGraph;
import Graph.Graph;
import Graph.Vertex;
import ShortPathAlgorithm.ShortPath;

public class Dijkstra implements ShortPath {

    private final int nulo = -1, inf = Integer.MAX_VALUE;
    private int[] distancia, pai, S;
    private boolean[] Q;
    int size_Q, fim_S;
    Graph graph;

    @Override
    public Graph MST(Graph graph) {

        this.graph = graph;
        this.distancia = new int[graph.matrix.length];
        this.pai = new int[graph.matrix.length];
        this.Q = new boolean[graph.matrix.length];
        this.S = new int[graph.matrix.length];

        inicializeSingleSource(0);

        Vertex u, v;
        int peso;

        while (size_Q > 0) {

            //Extrai o vertice com menor peso e insere-o em uma lista de solucao
            u = extractMin(Q, distancia);
            
            S[fim_S] = u.id;    //Lista de solucao
            fim_S++;            //posicao de insercao do proximo elemento

            //Visita todos os adjacentes ao vertice u e relaxa as arestas
            for (int i = 0; i < u.lengthEdge(); i++) {
                v = u.getEdge(i).nextVertex;
                peso = u.getEdge(i).value;

                relax(u.id, v.id, peso);

            }
        }

        return path(S,  distancia);
    }

    private void inicializeSingleSource(int raiz) {

        for (int vertice = 0; vertice < distancia.length; vertice++) {
            this.distancia[vertice] = inf;     //Distancia do vertice ao pai
            this.pai[vertice] = nulo;          //indica o vertice filho
            this.Q[vertice] = true;
        }

        size_Q = Q.length;
        fim_S = 0;

        distancia[raiz] = 0;           //fonte = raiz
        pai[raiz] = 0;

    }

    private void relax(int vertice_u, int vertice_v, int peso) {

        if (distancia[vertice_v] > distancia[vertice_u] + peso) {
            distancia[vertice_v] = distancia[vertice_u] + peso;
            pai[vertice_v] = vertice_u;
        }

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

    private Graph path(int[] S, int[] distancia) {

        Vertex[] vertex = new Vertex[distancia.length];
        Graph path = new DirectedGraph();

        //inicializa os vertices e adicionando-os no grafo
        for (int vertice = 0; vertice < distancia.length; vertice++) {
            vertex[vertice] = new Vertex(vertice);
            path.addVertex(vertex[vertice]);
        }

        for (int vertice = 1; vertice < S.length; vertice++) {

            Vertex A = vertex[ S[vertice - 1] ];
            Vertex B = vertex[ S[vertice] ];

            path.addEdge(A, B, distancia[B.id]);

        }

        return path;
    }
}
