import java.util.*;

public class GraphTraversalSearchingDFSandBFS {
    public static Set<Integer> recursionStack = new HashSet<>();
    public static HashMap<Integer, List<Integer>> nodeConnections = new HashMap<>();

    public static Set<Integer> visitedNodes = new HashSet<>();


    public static void exploreForCycles(int currentNode) {
        visitedNodes.add(currentNode);
        recursionStack.add(currentNode);

        List<Integer> connectedNodes =nodeConnections.getOrDefault(currentNode, Collections.emptyList());
        for (int connectedNode : connectedNodes) {
            if (!visitedNodes.contains(connectedNode)) {
                exploreForCycles(connectedNode);
            } else if (recursionStack.contains(connectedNode)) {
                System.out.print("Cycle: ");
                printCycle(connectedNode, currentNode);
                System.out.println();
            }
        }

        recursionStack.remove(currentNode);
    }
    public static void exploreDFS(int currentNode, Set<Integer> visited) {
        if (!visited.contains(currentNode)) {
            visited.add(currentNode);
            System.out.print(currentNode + " ");

            List<Integer> connectedNodes = nodeConnections.getOrDefault(currentNode, Collections.emptyList());
            for (int connectedNode : connectedNodes) {
                exploreDFS(connectedNode, visited);
            }
        }
    }

  


    public static void traverseDepthFirst(int startNode) {
        Set<Integer> visited = new HashSet<>();
        exploreDFS(startNode, visited);
    }
    public static void addConnection(int source, int destination) {
        nodeConnections.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
    }
    
    public static void resetGraph() {
        nodeConnections.clear();
        visitedNodes.clear();
        recursionStack.clear();
    }



    public static void traverseBreadthFirst(int startNode) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNode);
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            if (!visited.contains(currentNode)) {
                visited.add(currentNode);
                System.out.print(currentNode + " ");
                List<Integer> connectedNodes = nodeConnections.getOrDefault(currentNode, Collections.emptyList());
                queue.addAll(connectedNodes);
            }
        }
    }
    
       public static void detectAndPrintCycles() {
        for (int node : nodeConnections.keySet()) {
            if (!visitedNodes.contains(node)) {
                exploreForCycles(node);
            }
        }
    }

    public static boolean isBipartite() {
        Map<Integer, Integer> colorMap = new HashMap<>();
        for (int node : nodeConnections.keySet()) {
            if (!colorMap.containsKey(node)) {
                colorMap.put(node, 0);
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(node);

                while (!queue.isEmpty()) {
                    int currentNode = queue.poll();
                    List<Integer> connectedNodes = nodeConnections.getOrDefault(currentNode, Collections.emptyList());

                    for (int connectedNode : connectedNodes) {
                        if (!colorMap.containsKey(connectedNode)) {
                            colorMap.put(connectedNode, 1 -colorMap.get(currentNode));
                            queue.offer(connectedNode);
                        } else if (colorMap.get(connectedNode) == colorMap.get(currentNode)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean isGraphTree() {
        Set<Integer> visited= new HashSet<>();
        Stack<Integer> stack= new Stack<>();
        stack.push(1);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (visited.contains(node)) {
                return false;
            }
            visited.add(node);

            List<Integer> connectedNodes = nodeConnections.getOrDefault(node, Collections.emptyList());
            stack.addAll(connectedNodes);
        }
        return visited.size() == nodeConnections.size();
    }
    
    

    public static void printCycle(int startNode, int endNode) {
        boolean print = false;
        for (int node : recursionStack) {
            if (node ==startNode) {
                print =true;
            }
            if (print) {
                System.out.print(node + " ");
                if (node == endNode) {
                    break;
                }
            }
        }
    }
    public static void testGraph(int[] vertices, int[][]edges) {
        for (int i = 0; i < edges.length; i++) {
            addConnection(edges[i][0], edges[i][1]);
        }

        System.out.println("DepthFisrstSearch:");
        traverseDepthFirst(1);
        System.out.println();
        System.out.println("BreadthFirstSearch:");
        traverseBreadthFirst(1);
        System.out.println();
        System.out.println("Cycles:");
        detectAndPrintCycles();

        if (!isGraphTree()) {
            System.out.println("The graph is not a tree.");
        } else {
            System.out.println("The graph is a tree.");
        }

        if (isBipartite()) {
            System.out.println("The graph is bipartite.");
        } else {
            System.out.println("The graph is not bipartite.");
        }

        System.out.println("--------------");
        resetGraph();
    }
    

    public static void main(String[] args) {
        // Graph 1: Given Graph
        final int[] vertices1 = {1, 2,3,4};
        final int[][] edges1 = {{1,3}, {1,4}, {2,1}, {2,3}, {3,4}, {4,1}, {4,2}};

        testGraph(vertices1,edges1);
    }


    
}