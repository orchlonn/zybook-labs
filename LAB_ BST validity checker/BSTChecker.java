import java.util.*;

public class BSTChecker {
	public static Node checkBSTValidity(Node rootNode) {
		// Make a node stack
		Stack<Node> nodes = new Stack<Node>();
		nodes.push(rootNode);

		// Check for loops first
		HashSet<Node> distinctSet = new HashSet<Node>();
		while (nodes.size() > 0) {
			Node node = nodes.peek();
			nodes.pop();

			if (node != null) {
				// Add the node to the set of distinct nodes visited
				distinctSet.add(node);

				// If either of the node's children is in distinctSet then the
				// node is invalid due to either:
				// referencing an ancestor and thus causing a loop in the tree
				// or
				// referencing a non-ancestor node from another part of the tree
				// and thus making a node have two parent nodes.
				// This lab's test cases only test the former and not the latter.
				if (distinctSet.contains(node.left) || distinctSet.contains(node.right)) {
					return node;
				}

				// Push children onto the stack
				nodes.push(node.left);
				nodes.push(node.right);
			}
		}

		// Check for key violations second
		while (nodes.size() > 0) {
			nodes.pop();
		}
		nodes.push(rootNode);
		while (nodes.size() > 0) {
			Node node = nodes.peek();
			nodes.pop();
			if (node != null) {
				// Check for key-related violations
				Node badNode = findViolator(node.left, node, "Left");
				if (badNode != null) {
					return badNode;
				}
				badNode = findViolator(node.right, node, "Right");
				if (badNode != null) {
					return badNode;
				}

				// Push children onto the stack
				nodes.push(node.left);
				nodes.push(node.right);
			}
		}

		// Arriving here implies that no violations were found
		return null;
	}

	// Visits all nodes in the subtree and returns the first encountered node
	// that doesn't satisfy the predicate. Returns null if all nodes satisfy.
	public static Node findViolator(Node subtreeRoot, Node prevNode, String dir) {
		// Test each node with the predicate. If the predicate returns false for
		// any node, then return that node.
		Stack<Node> nodes = new Stack<Node>();
		nodes.push(subtreeRoot);
		while (nodes.size() > 0) {
			Node node = nodes.peek();
			nodes.pop();
			if (node != null) {
				if (!predicate(prevNode, node, dir)) {
					return node;
				}

				// Push children onto the stack
				nodes.push(node.left);
				nodes.push(node.right);
			}
		}
		return null;
	}

	public static boolean predicate(Node node, Node otherNode, String dir) {
		if (dir == "Left") {
			return otherNode.key <= node.key;
		}
		else if (dir == "Right") { 
			return otherNode.key >= node.key;
		}
		else {
			return false;
		}
	}
}