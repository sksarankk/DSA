package bfs;

import java.util.*;

public class BfsShortestpath {
    private final Map<Integer, List<Integer>> g;
    private final int n;
    public BfsShortestpath(Map<Integer, List<Integer>> graph){
        this.g = graph;
        this.n = g.size();
    }
    public ArrayList<Integer> bfs(int s, int e){
        return reconstructPath(s, e, solve(s));
    }
    public Integer[] solve(int s){
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        boolean[] visited = new boolean[n];
        visited[s] = true;
        Integer[] prev = new Integer[n];
        while(!q.isEmpty()){
            int node = q.poll();
            List<Integer> neighbours = g.get(node);
            for( int next : neighbours){
                if(!visited[next]){
                    q.offer(next);
                    visited[next] = true;
                    prev[next] = node;
                }
            }
        }
        return prev;
    }
    public ArrayList<Integer> reconstructPath(int s, int e, Integer[] prev){
        ArrayList<Integer> path = new ArrayList<>();
        for(Integer at = e; at != null; at = prev[at]) path.add(at);
        Collections.reverse(path);
        if(path.get(0) == s) return path;
        path.clear();
        return path;

    }
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        addEdge(0, 1, graph);
        addEdge(1,2, graph);
        addEdge(2,5, graph);
        addEdge(2,3,graph);
        addEdge(3,4,graph);
        addEdge(4,5,graph);
        BfsShortestpath bfs = new BfsShortestpath(graph);
        System.out.println(bfs.bfs(0,5));

    }
    private static void addEdge(int u, int v, Map<Integer, List<Integer>> graph) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u); // Undirected
    }
}
