package wenyu.learning.Tree.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TraversalBFS {
	
	public static <E> void traversalBFS(BinaryTreeNode<E> root, TraversalOperation<E> op) {
		if(root == null) {
			return;
		}
		
		Queue<BinaryTreeNode<E>> queue  = new LinkedList<BinaryTreeNode<E>>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			BinaryTreeNode<E> node = queue.poll();
			if(op == null) {
				System.out.print(node.value + " ");
			} else {
				op.operate(node);
			}
			
			if(node.left != null) {
				queue.offer(node.left);
			}
			if(node.right != null) {
				queue.offer(node.right);
			}
		}
		System.out.println("");
	}
	
	// Print BFS with recursion
	public static <E> void traversalBFSRec(BinaryTreeNode<E> root, TraversalOperation<E> op) {
		// O(n^2)
		int height = HeightCalculator.calculateTreeHeight(root);
		for(int i=1; i<=height; i++) {
			traversalBFSRec_core(root, i, op);
		}
	}
	private static <E> void traversalBFSRec_core(BinaryTreeNode<E> node, int level, TraversalOperation<E> op) {
		if(node == null) {
			return;
		} else if(level == 1) {
			if(op == null) {
				System.out.print(node.value + " ");
			} else {
				op.operate(node);
			}
		} else if(level > 1) {
			traversalBFSRec_core(node.left, level-1, op);
			traversalBFSRec_core(node.right, level-1, op);
		}
	}
	
	// Print BFS with lines
	public static <E> void traversalWithLine(BinaryTreeNode<E> root, TraversalOperation<E> op) {
		if(root == null) {
			return;
		}
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		Queue<BinaryTreeNode<E>> queue = new LinkedList<BinaryTreeNode<E>>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			BinaryTreeNode<E> node = queue.poll();
			if(op == null) {
				System.out.print(node.value + " ");
			} else {
				op.operate(node);
			}
			
			if(node.left != null) {
				queue.offer(node.left);
				nextLineNodeCount++;
			}
			if(node.right != null) {
				queue.offer(node.right);
				nextLineNodeCount++;
			}

			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				if(op == null) {
					System.out.println();
				}
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
			}
		}
	}
	
	// Print BFS with recursion
	public static <E> void traversalBFSRecWithLine(BinaryTreeNode<E> root, TraversalOperation<E> op) {
		// O(n^2)
		int height = HeightCalculator.calculateTreeHeight(root);
		for(int i=1; i<=height; i++) {
			traversalBFSRecWithLine_core(root, i, op);
			System.out.println();
		}
	}
	private static <E> void traversalBFSRecWithLine_core(BinaryTreeNode<E> node, int level, TraversalOperation<E> op) {
		if(node == null) {
			return;
		} else if(level == 1) {
			if(op == null) {
				System.out.print(node.value + " ");
			} else {
				op.operate(node);
			}
		} else if(level > 1) {
			traversalBFSRecWithLine_core(node.left, level-1, op);
			traversalBFSRecWithLine_core(node.right, level-1, op);
		}
	}
	
	// Print BFS with lines
	public static <E> void traversalWithinSpecLines(BinaryTreeNode<E> root, TraversalOperation<E> op, int startLine, int endLin, boolean newLine) {
		if(root==null || startLine>endLin || startLine<0 || endLin<0) {
			return;
		}
		int currLineNum = 1;
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		Queue<BinaryTreeNode<E>> queue = new LinkedList<BinaryTreeNode<E>>();
		queue.offer(root);

		while(!queue.isEmpty()) {
			BinaryTreeNode<E> node = queue.poll();
			if(op==null && currLineNum>=startLine && currLineNum<=endLin) {
				System.out.print(node.value + " ");
			} else if(currLineNum>=startLine && currLineNum<=endLin){
				op.operate(node);
			}

			if(node.left != null) {
				queue.offer(node.left);
				nextLineNodeCount++;
			}
			if(node.right != null) {
				queue.offer(node.right);
				nextLineNodeCount++;
			}

			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				if(op==null && newLine) {
					System.out.println();
				}
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
				currLineNum++;
			}
		}
	}
	
	// Print BFS with lines
	public static <E> void traversalWithLineZigZag(BinaryTreeNode<E> root, TraversalOperation<E> op) {
		if(root == null) {
			return;
		}
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		int lineNo = 0;
		Stack<BinaryTreeNode<E>> stackEven = new Stack<BinaryTreeNode<E>>();
		Stack<BinaryTreeNode<E>> stackOdd = new Stack<BinaryTreeNode<E>>();
		stackEven.push(root);
		
		while(!stackEven.isEmpty() || !stackOdd.isEmpty()) {
			if(lineNo%2 == 0) {
				BinaryTreeNode<E> node = stackEven.pop();
				if(op == null) {
					System.out.print(node.value + " ");
				} else {
					op.operate(node);
				}
				if(node.left != null) {
					stackOdd.push(node.left);
					nextLineNodeCount++;
				}
				if(node.right != null) {
					stackOdd.push(node.right);
					nextLineNodeCount++;
				}
			} else {
				BinaryTreeNode<E> node = stackOdd.pop();
				if(op == null) {
					System.out.print(node.value + " ");
				} else {
					op.operate(node);
				}
				if(node.right != null) {
					stackEven.push(node.right);
					nextLineNodeCount++;
				}
				if(node.left != null) {
					stackEven.push(node.left);
					nextLineNodeCount++;
				}
			}

			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				if(op == null) {
					System.out.println();
				}
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
				lineNo++;
			}
		}
	}
	
	// Print BFS with recursion
	public static <E> void traversalBFSRecWithLineZigZag(BinaryTreeNode<E> root, TraversalOperation<E> op) {
		// O(n^2)
		int height = HeightCalculator.calculateTreeHeight(root);
		for(int i=1; i<=height; i++) {
			traversalBFSRecWithLineZigZag_core(root, i, op, i%2==1);
			System.out.println();
		}
	}
	private static <E> void traversalBFSRecWithLineZigZag_core(BinaryTreeNode<E> node, int level, TraversalOperation<E> op, boolean flag) {
		if(node == null) {
			return;
		} else if(level == 1) {
			if(op == null) {
				System.out.print(node.value + " ");
			} else {
				op.operate(node);
			}
		} else if(level > 1) {
			if(flag) {
				traversalBFSRecWithLineZigZag_core(node.left, level-1, op, flag);
				traversalBFSRecWithLineZigZag_core(node.right, level-1, op, flag);
			} else {
				traversalBFSRecWithLineZigZag_core(node.right, level-1, op, flag);
				traversalBFSRecWithLineZigZag_core(node.left, level-1, op, flag);
			}
		}
	}
	
	public static <E> void traversalBFSReversed(BinaryTreeNode<E> root, TraversalOperation<E> op) {
		if(root == null) {
			return;
		}
		
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		Queue<BinaryTreeNode<E>> queue  = new LinkedList<BinaryTreeNode<E>>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			BinaryTreeNode<E> node = queue.poll();
			stack.push(node);
			
			if(node.right != null) {
				queue.offer(node.right);
			}
			if(node.left != null) {
				queue.offer(node.left);
			}
		}
		
		while(!stack.isEmpty()) {
			BinaryTreeNode<E> node = stack.pop();
			if(op == null) {
				System.out.print(node.value + " ");
			} else {
				op.operate(node);
			}
		}
		System.out.println("");
	}
	
	public static <E> void traversalBFSWithLineReversed(BinaryTreeNode<E> root, TraversalOperation<E> op) {
		if(root == null) {
			return;
		}
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		Queue<BinaryTreeNode<E>> queue = new LinkedList<BinaryTreeNode<E>>();
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		Stack<Integer> nodeCountStack = new Stack<Integer>();
		queue.offer(root);
		nodeCountStack.push(1);
		
		while(!queue.isEmpty()) {
			BinaryTreeNode<E> node = queue.poll();
			stack.push(node);
			
			if(node.right != null) {
				queue.offer(node.right);
				nextLineNodeCount++;
			}
			if(node.left != null) {
				queue.offer(node.left);
				nextLineNodeCount++;
			}

			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				nodeCountStack.push(nextLineNodeCount);
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
			}
		}
		
		if(nodeCountStack.isEmpty()) {
			return;
		}
		nodeCountStack.pop(); // remove last zero node level
		int currLineNodeCount = nodeCountStack.pop();
		while(!stack.isEmpty() && currLineNodeCount>0) {
			BinaryTreeNode<E> node = stack.pop();
			if(op == null) {
				System.out.print(node.value + " ");
			} else {
				op.operate(node);
			}
			currLineNodeCount--;
			
			if(currLineNodeCount==0 && !nodeCountStack.isEmpty()) {
				currLineNodeCount = nodeCountStack.pop();
				System.out.println();
			}
		}
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genIntegerBST(10);
		traversalWithLine(root, null);
		
//		System.out.println("=============================================");
//		traversalBFS(root, null);
		
//		System.out.println("=============================================");
//		traversalBFSRec(root, null);
		
//		System.out.println("=============================================");
//		traversalBFSRecWithLine(root, null);
		
//		System.out.println("=============================================");
//		traversalWithLineZigZag(root, null);

//		System.out.println("=============================================");
//		traversalBFSRecWithLineZigZag(root, null);
		
//		System.out.println("=============================================");
//		traversalBFSReversed(root, null);
		
//		System.out.println("=============================================");
//		traversalBFSWithLineReversed(root, null);
		
		System.out.println("=============================================");
		traversalWithinSpecLines(root, null, 3, 3, false);
	}
}
