import java.util.*;

public class ValidArrangement {
    public int[][] validArrangement(int[][] pairs) {
        // Step 1: Build the adjacency list and track in/out degrees
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        Map<Integer, Integer> inOutDegree = new HashMap<>();
        
        // Build graph and count in/out degrees
        for (int[] pair : pairs) {
            adjacencyList.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
            inOutDegree.merge(pair[0], 1, Integer::sum);  // out-degree
            inOutDegree.merge(pair[1], -1, Integer::sum);  // in-degree
        }
        
        // Step 2: Find the start node (node with out-degree greater than in-degree)
        int startNode = pairs[0][0];  // Default to first pair's start node
        for (Map.Entry<Integer, Integer> entry : inOutDegree.entrySet()) {
            if (entry.getValue() == 1) {
                startNode = entry.getKey();  // Find node with out-degree = in-degree + 1
                break;
            }
        }
        
        // Step 3: Perform Hierholzer's Algorithm to find the Eulerian path
        List<Integer> path = new ArrayList<>();
        Deque<Integer> nodeStack = new ArrayDeque<>();
        nodeStack.push(startNode);
        
        // Step 4: Traverse the graph to build the path
        while (!nodeStack.isEmpty()) {
            List<Integer> neighbors = adjacencyList.getOrDefault(nodeStack.peek(), new ArrayList<>());
            if (neighbors.isEmpty()) {
                path.add(nodeStack.pop());  // No more neighbors, backtrack
            } else {
                int nextNode = neighbors.get(neighbors.size() - 1);
                nodeStack.push(nextNode);
                neighbors.remove(neighbors.size() - 1);  // Remove the visited edge
            }
        }
        
        // Step 5: Build the result from the path
        int pathSize = path.size();
        int[][] arrangement = new int[pathSize - 1][2];  // Result array to store pairs
        
        // Fill the arrangement with pairs
        for (int i = pathSize - 1; i > 0; --i) {
            arrangement[pathSize - 1 - i] = new int[]{path.get(i), path.get(i - 1)};
        }
        
        // Step 6: Return the valid arrangement
        return arrangement;
    }

    public static void main(String[] args) {
        ValidArrangement valid = new ValidArrangement();

        // Example 1
        int[][] pairs1 = {{5, 1}, {4, 5}, {11, 9}, {9, 4}};
        System.out.println(Arrays.deepToString(valid.validArrangement(pairs1))); 
        // Output: [[11, 9], [9, 4], [4, 5], [5, 1]]

        // Example 2
        int[][] pairs2 = {{1, 3}, {3, 2}, {2, 1}};
        System.out.println(Arrays.deepToString(valid.validArrangement(pairs2))); 
        // Output: [[1, 3], [3, 2], [2, 1]]

        // Example 3
        int[][] pairs3 = {{1, 2}, {1, 3}, {2, 1}};
        System.out.println(Arrays.deepToString(valid.validArrangement(pairs3))); 
        // Output: [[1, 2], [2, 1], [1, 3]]
    }
}
