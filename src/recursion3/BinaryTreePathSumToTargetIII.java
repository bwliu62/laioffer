package recursion3;
import java.util.*;
/**
 * Given a binary tree in which each node contains an integer number. Determine if there exists a path (the path can only be from one node to itself or to any of its descendants), the sum of the numbers on the path is the given target number.
 *
 * Examples
 *
 *     5
 *
 *   /    \
 *
 * 2      11
 *
 *      /    \
 *
 *     6     14
 *
 *   /
 *
 *  3
 *
 * If target = 17, There exists a path 11 + 6, the sum of the path is target.
 *
 * If target = 20, There exists a path 11 + 6 + 3, the sum of the path is target.
 *
 * If target = 10, There does not exist any paths sum of which is target.
 *
 * If target = 11, There exists a path only containing the node 11.
 *
 * How is the binary tree represented?
 *
 * We use the level order traversal sequence with a special symbol "#" denoting the null node.
 *
 * For Example:
 *
 * The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
 *
 *     1
 *
 *   /   \
 *
 *  2     3
 *
 *       /
 *
 *     4
 * */
public class BinaryTreePathSumToTargetIII {
    // method 1 : best case O(nlogn)  worst case n^2 ; SC: O(height) -> On
    public boolean exist(TreeNode root, int target) {
        // Write your solution here
        if (root == null) {
            return false;
        }
        return helper(root, target) || exist(root.left,target) || exist(root.right, target);
    }
    private boolean helper(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        return root.key == target || helper(root.left, target - root.key) || helper(root.right, target- root.key);
    }

    // method 2: we consider the path as a linked list
    public boolean exist2(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        Set<Integer> prefixSums = new HashSet<>();
        prefixSums.add(0);
        return helper2(root, prefixSums, 0 ,target);
    }

    private boolean helper2(TreeNode root, Set<Integer> prefixSums, int preSum, int target){
        preSum += root.key;
        if (prefixSums.contains(preSum - target)) {
            return true;
        }
        boolean needRemove = prefixSums.add(preSum);
        if (root.left != null && helper2(root.left, prefixSums, preSum, target)) {
            return true;
        }
        if (root.right != null && helper2(root.right, prefixSums, preSum, target)) {
            return true;
        }
        if (needRemove) {
            prefixSums.remove(preSum);
        }
        return false;
    }
}
