
import Graph.*;
import Search.Search;
import Search.DepthSearch;
import ShortPathAlgorithm.*;
import ShortPathAlgorithm.ShortPath;

public class Use {

    public static void main(String args[]) throws InterruptedException {

        System.err.println("Kruskal:");
        testKruskal();
        
        System.err.println("Prim:");
        testPrim();
        
        System.err.println("Dijkstra:");
        testDijkstra();
    }

    public static void testKruskal() {

        Graph graph = new Graph();          //Grafo vazio

        ShortPath minimalPath;
        Graph tree;
        Search search = new DepthSearch();  //Tipo de busca

        //Instanciando vertices
        Vertex A = new Vertex(0, "A");
        Vertex B = new Vertex(1, "B");
        Vertex C = new Vertex(2, "C");
        Vertex D = new Vertex(3, "D");
        Vertex E = new Vertex(4, "E");
        Vertex F = new Vertex(5, "F");
        Vertex G = new Vertex(6, "G");
        Vertex H = new Vertex(7, "H");
        Vertex I = new Vertex(8, "I");

        //adicionando os vertices ao grafo
        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);
        graph.addVertex(D);
        graph.addVertex(E);
        graph.addVertex(F);
        graph.addVertex(G);
        graph.addVertex(H);
        graph.addVertex(I);

        //adicionando as arestas ao grafo
        //dois vertices e um peso
        graph.addEdge(A, B, 4);
        graph.addEdge(A, H, 8);

        graph.addEdge(B, C, 8);
        graph.addEdge(B, H, 11);

        graph.addEdge(C, D, 7);
        graph.addEdge(C, I, 2);
        graph.addEdge(C, F, 4);

        graph.addEdge(D, E, 9);
        graph.addEdge(D, F, 14);

        graph.addEdge(E, F, 10);
        graph.addEdge(F, G, 2);
        graph.addEdge(G, H, 1);
        graph.addEdge(G, I, 6);
        graph.addEdge(H, I, 7);

        //graph.showMatrix();
        minimalPath = new Kruskal();
        tree = minimalPath.MST(graph);

        Action.sumEdge(tree);
        //tree.showVertices();

    }

    public static void testPrim() {

        Graph graph = new Graph();          //Grafo vazio

        ShortPath minimalPath;
        Graph tree;
        Search search = new DepthSearch();  //Tipo de busca

        //Instanciando vertices
        Vertex A = new Vertex(0, "A");
        Vertex B = new Vertex(1, "B");
        Vertex C = new Vertex(2, "C");
        Vertex D = new Vertex(3, "D");
        Vertex E = new Vertex(4, "E");
        Vertex F = new Vertex(5, "F");
        Vertex G = new Vertex(6, "G");
        Vertex H = new Vertex(7, "H");
        Vertex I = new Vertex(8, "I");

        //adicionando os vertices ao grafo
        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);
        graph.addVertex(D);
        graph.addVertex(E);
        graph.addVertex(F);
        graph.addVertex(G);
        graph.addVertex(H);
        graph.addVertex(I);

        //adicionando as arestas ao grafo
        //dois vertices e um peso
        graph.addEdge(A, B, 4);
        graph.addEdge(A, H, 8);

        graph.addEdge(B, C, 8);
        graph.addEdge(B, H, 11);

        graph.addEdge(C, D, 7);
        graph.addEdge(C, I, 2);
        graph.addEdge(C, F, 4);

        graph.addEdge(D, E, 9);
        graph.addEdge(D, F, 14);

        graph.addEdge(E, F, 10);
        graph.addEdge(F, G, 2);
        graph.addEdge(G, H, 1);
        graph.addEdge(G, I, 6);
        graph.addEdge(H, I, 7);

        //graph.showMatrix();
        minimalPath = new Prim();
        tree = minimalPath.MST(graph);

        Action.sumEdge(tree);
        //tree.showVertices();
    }

    public static void testDijkstra() {

        Graph graph = new DirectedGraph();          //Grafo vazio
        Graph path;
        ShortPath SP = new Dijkstra();

        //Instanciando vertices
        Vertex A = new Vertex(0, "A");
        Vertex B = new Vertex(1, "B");
        Vertex C = new Vertex(2, "C");
        Vertex D = new Vertex(3, "D");
        Vertex E = new Vertex(4, "E");
        Vertex F = new Vertex(5, "F");

        //adicionando os vertices ao grafo
        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);
        graph.addVertex(D);
        graph.addVertex(E);
        graph.addVertex(F);

        //adicionando as arestas ao grafo
        //dois vertices e um peso
        graph.addEdge(A, B, 10);
        graph.addEdge(A, C, 5);

        graph.addEdge(B, D, 1);

        graph.addEdge(C, D, 8);
        graph.addEdge(C, B, 3);
        graph.addEdge(C, F, 4);

        graph.addEdge(D, E, 4);
        graph.addEdge(D, F, 4);

        graph.addEdge(F, E, 6);

        path = SP.MST(graph);
        
        Action.sumEdge(path);
    }

    public static void normalTest() {
        Graph graph = new Graph();          //Grafo vazio
        Search search = new DepthSearch();  //Tipo de busca

        Vertex A = new Vertex(0, "A");
        Vertex B = new Vertex(1, "B");
        Vertex C = new Vertex(2, "C");

        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);

        graph.addEdge(A, B, 500);

        //System.out.println(A.lengthEdge());
        System.err.println(A.getEdge(0).value);
        System.err.println(B.getEdge(0).value);

        System.out.println(graph.hasEdge(A, C));
    }
}
