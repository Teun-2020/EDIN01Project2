import java.util.*;

public class DeBruijnSequence {

    public List<Integer> generateDeBruijn(int q, int n) {
        List<Integer> sequence = new ArrayList<>();
        int totalNodes = (int) Math.pow(q, n - 1);
        int[] visitedEdges = new int[totalNodes]; // Track visited edges for each node
        Stack<Integer> stack = new Stack<>(); // Stack for backtracking

        // Start at the initial node
        stack.push(0);

        while (!stack.isEmpty()) {
            int currentNode = stack.peek();
            if (visitedEdges[currentNode] < q) {
                // Traverse the next edge
                int nextEdge = visitedEdges[currentNode]++;
                stack.push((currentNode * q + nextEdge) % totalNodes);
                sequence.add(nextEdge);
            } else {
                // Backtrack
                stack.pop();
            }
        }

        // Add the initial n-1 states to make the sequence cyclic
        for (int i = 0; i < n - 1; i++) {
            sequence.add(sequence.get(i));
        }

        return sequence;
    }

    public List<Integer> combineSequences(List<Integer> seqZ2, List<Integer> seqZ5) {
        List<Integer> combined = new ArrayList<>();
        int lenZ2 = seqZ2.size() - 3; // Exclude the wraparound (n-1) for Z2
        int lenZ5 = seqZ5.size() - 3; // Exclude the wraparound (n-1) for Z5

        // Map each pair uniquely to Z10
        for (int i = 0; i < lenZ2; i++) {
            for (int j = 0; j < lenZ5; j++) {
                int z2Digit = seqZ2.get(i);
                int z5Digit = seqZ5.get(j);
                combined.add(z2Digit * 5 + z5Digit); // Unique mapping to Z10
            }
        }

        // Add wraparound exactly once to make the sequence cyclic
        for (int k = 0; k < 3; k++) { // Add the first (n-1) digits
            combined.add(combined.get(k));
        }

        return combined;
    }
}
