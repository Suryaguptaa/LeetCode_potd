/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return binary_sum(0, root);
    }

    private int binary_sum(int current_sum, TreeNode root){
        if(root == null) return 0;
        current_sum = (current_sum << 1) | root.val;
        if(root.left == null && root.right == null){
            return current_sum;
        }
        return (binary_sum(current_sum, root.left) + binary_sum(current_sum, root.right));
    }
}