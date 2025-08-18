import java.util.*;

class DFS {
    private static final double EPS = 1e-6;

    private boolean dfs(List<Double> nums) {
        if (nums.size() == 1) return Math.abs(nums.get(0) - 24.0) < EPS;

        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                double a = nums.get(i), b = nums.get(j);
                List<Double> next = new ArrayList<>();
                for (int k = 0; k < nums.size(); k++) {
                    if (k != i && k != j) next.add(nums.get(k));
                }

                double[] cand = new double[] { a + b, a - b, b - a, a * b };
                for (double c : cand) {
                    next.add(c);
                    if (dfs(next)) return true;
                    next.remove(next.size() - 1);
                }
                if (Math.abs(b) > EPS) {
                    next.add(a / b);
                    if (dfs(next)) return true;
                    next.remove(next.size() - 1);
                }
                if (Math.abs(a) > EPS) {
                    next.add(b / a);
                    if (dfs(next)) return true;
                    next.remove(next.size() - 1);
                }
            }
        }
        return false;
    }

    public boolean judgePoint24(int[] cards) {
        List<Double> nums = new ArrayList<>();
        for (int x : cards) nums.add((double)x);
        return dfs(nums);
    }
}
