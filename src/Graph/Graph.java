package Graph;

import java.util.LinkedList;

public class Graph {

    private int length;
    
    private int[][] matrix;

    private final LinkedList<Vertex> vertex;
    private final LinkedList<Edge> edge;

    public Graph() {
        vertex = new LinkedList<>();
        edge = new LinkedList<>();
        
        this.length = 0;
    }

    //Retonra o tamanho do grafo (numero de vertices)
    public int getLength() {
        return length;
    }
    
    //------------------------------------------------------
    //              Operacoes com vertices  
    //------------------------------------------------------
    
    //Apenas adiciona o vertice na lista
    public void addVertex(Vertex vertex) {
        this.vertex.add(vertex);
        this.length++;
    }
    
    //Retorna um vertice com um id como argumento
    public Vertex getVertex(int id) {

        for (int i = 0; i < getLength(); i++) {
            if (vertex.get(i).getId() == id) {
                return vertex.get(i);
            }
        }

        return null;
    }

    //Exibe cada vertice do grafo, e seus adjacentes
    public void showVertices() {
        try{
            for (int i = 0; i < getLength(); i++) {
                System.err.println(getVertex(i).getLabel());
                getVertex(i).showVertices();
                Thread.sleep(2000);
            }
        }catch(Exception err){System.err.println("Problemas em showVertices()");}
    }

    
    
    //------------------------------------------------------
    //              Operacoes com Arestas  
    //------------------------------------------------------
    
    
    //Referencia v1 <-> v2 e vice-versa
    //Adiciona uma aresta na lista
    public void addEdge(Vertex v1, Vertex v2, int value) {
        try{
            v1.addVertex(v2, value);    //Referencia v2 em v1
            v2.addVertex(v1, value);    //Esta linha pode ser omitida caso o grafo seja dirigido

            //Insere a areta ordenando por peso
            int i = 0;
            while (i < edge.size()) {
                if (edge.get(i).getValue() > value) break;
                i++;
            }

            edge.add(i, new Edge(v1, v2, value));

            if(matrix == null) activeMatrix();

            //Adiciona os vertices na matriz de adjacencia
            addMatrix(v1.getId(), v2.getId(), value);
            
        }catch(Exception e){System.out.println("Problemas em addEdge(Vertex Vertex Value)");}
    }
   
    public void addEdge(Vertex[] vertex, int[][] matrix) {
        
    }
   
    public void addEdge(int v1, int v2, int value) {
        addEdge(getVertex(v1), getVertex(v2), value);
    }
    
    //Retorna uma aresta
    public Edge getEdge(int id) {

       try{
            if (edge.size() > 0)
                if(edge.get(id) != null)
                    return edge.get(id);
            
       }catch(Exception err){
           System.err.println("Problemas em getEdge()");
       }
       return null;
    }
    
    //Retorna tamanho da lista de aresta
    public int getLengthEdge(){
        return edge.size();
    }
    
    //Verifica se existe aresta entre os pares de vertices 
    public boolean hasEdge(Vertex v1, Vertex v2) {

        if (this.edge.size() > 0) {

            Vertex first = getVertex(v1.getId());
            Vertex last;

            for (int i = 0; i < first.lengthEdge(); i++) {
                last = first.getEdge(i).getNextVertex();    //last = conexao com primeiro vertice
                if (v2.getId() == last.getId()) //verifica se o id dos vertice sao iguais
                {
                    return true;
                }
            }
        }
        return false;
    }    
    
    
    //------------------------------------------------------
    //          Operacoes com Matriz de Adjacencia  
    //------------------------------------------------------    

    private void activeMatrix(){
        matrix = new int[length][length];
    }
    
    private void criaComMatriz(){}
    
    //Apos add os vertices, ativar a matriz de adjacencia
    public void addMatrix(int v1, int v2, int value) {
        matrix[v1][v2] = value;
        matrix[v2][v1] = value;
    }

    //Recebe uma matriz de adjacencia para criar um grafo
    private void setMatrix() {

    }
    
    public void showMatrix(){
        
        if(matrix == null)
            return;
        
        for (int i = 0; i < matrix.length; i++){
            for (int j = i; j < matrix[i].length; j++)
                System.out.print(matrix[i][j] + "\t");
            System.out.println();
        }
    }
    
    
}
