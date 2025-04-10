package topologicalSort;

import java.util.*;

public class KahnsTopSort {
    public int[] findTopologicalOrdering(Map<Integer, List<Integer>> graph) {
        int n = graph.size();
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) {
            for (Integer next : graph.get(i)) {
                degree[next] = degree[next] + 1;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) q.offer(i);
        }
        int index = 0;
        int[] order = new int[n];
        while (!q.isEmpty()) {
            int at = q.poll();
            order[index++] = at;
            for (Integer next : graph.get(at)) {
                degree[next] = degree[next] - 1;
                if (degree[next] == 0) q.offer(next);
            }
        }
        if (index != n) return new int[0];
        return order;
    }
    public static void main(String[] args) {
        Map<Integer,List<Integer>> graph = new HashMap<>();
        addDirectedEdge(graph, 0, 1);
        addDirectedEdge(graph, 2, 0);
        addDirectedEdge(graph, 1, 3);
        addDirectedEdge(graph, 3, 2);
        addDirectedEdge(graph, 3, 4);
        addDirectedEdge(graph, 3, 5);
        addDirectedEdge(graph, 5, 6);
        addDirectedEdge(graph, 4, 7);
        addDirectedEdge(graph, 7, 6);
        addDirectedEdge(graph, 7, 8);
        graph.put(8, new ArrayList<>());
        graph.put(6, new ArrayList<>());
        KahnsTopSort kahnsTopSort = new KahnsTopSort();
        int[] ordering = kahnsTopSort.findTopologicalOrdering(graph);
        System.out.println(Arrays.toString(ordering));
    }
    private static void addDirectedEdge(Map<Integer, List<Integer>> graph, int from, int to) {
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }
}
