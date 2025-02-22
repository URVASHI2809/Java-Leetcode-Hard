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
class RecoverFromPreorder {
    int i=0;
    public TreeNode recoverFromPreorder(String traversal) {
        return func(traversal,0,0);
    }
    TreeNode func(String traversal,int j,int D){
        if(i>=traversal.length() || j>=traversal.length()) return null;
        int d=0;
        while(traversal.charAt(j)=='-'){
            d++;
            j++;
        }
        if(D==d){
            int val=0;
            while(j<traversal.length() && traversal.charAt(j)!='-'){
                val*=10;
                val+=(int)traversal.charAt(j)-'0';
                j++;
            }
            TreeNode node=new TreeNode(val);
            i=j;
            node.left=func(traversal,j,D+1);
            if(node.left==null) node.right=func(traversal,j,D+1);
            else node.right=func(traversal,i,D+1);
            return node;
        }
        return null;
    }
}
