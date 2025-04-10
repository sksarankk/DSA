package topologicalSort;

import java.util.*;

public class TopSort {
    static class Edge{
        int from;
        int to;
        public Edge(int from, int to){
            this.from = from;
            this.to = to;
        }
    }
    public int[] findTopSort(Map<Integer,List<Edge>> g){
        int n = g.size();
        boolean[] visited = new boolean[n];
        int[] ordering = new int[n];
        int i = n-1;
        for(int at = 0; at < n; n++){
            if(!visited[at]){
              dfs(i, at, visited, ordering, g);
            }
        }
        return ordering;
    }
    public int dfs(int i, int at, boolean[] visited, int[] ordering, Map<Integer, List<Edge>> graph){
        visited[at] = true;
        List<Edge> edges = graph.get(at);
        for(Edge edge : edges){
            if(!visited[edge.to]){
                i = dfs(i, edge.to, visited, ordering, graph);
            }
        }
        ordering[i] = at;
        return i -1;
    }
    public static void main(String[] args) {
        Map<Integer,List<Edge>> graph = new HashMap<>();
        addDirectedEdge(graph, 0, 1);
        addDirectedEdge(graph, 0, 2);
        addDirectedEdge(graph, 1, 3);
        addDirectedEdge(graph, 2, 3);
        addDirectedEdge(graph, 3, 4);
        addDirectedEdge(graph, 3, 5);
        addDirectedEdge(graph, 5, 6);
        addDirectedEdge(graph, 4, 7);
        addDirectedEdge(graph, 7, 6);
        addDirectedEdge(graph, 7, 8);
        graph.put(8, new ArrayList<>());
        graph.put(6, new ArrayList<>());
        TopSort topSort = new TopSort();
        int[] ordering = topSort.findTopSort(graph);
        System.out.println(Arrays.toString(ordering));
    }
    private static void addDirectedEdge(Map<Integer, List<Edge>> graph, int from, int to) {
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(from, to));
    }
}
