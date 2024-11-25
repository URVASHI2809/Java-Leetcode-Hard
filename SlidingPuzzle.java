package Java;

import java.util.*;

public class SlidingPuzzle {
    public int slidingPuzzle(int[][] board) {
        String target = "123450";
        String start = boardToString(board);

        if (!isSolvable(start)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);
        int moves = 0;

        int[] directions = {-1, 1, -3, 3};

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                if (current.equals(target)) {
                    return moves;
                }

                int zeroIndex = current.indexOf('0');

                for (int dir : directions) {
                    int newIndex = zeroIndex + dir;

                    if (newIndex >= 0 && newIndex < 6 && !(zeroIndex == 2 && dir == 1) && !(zeroIndex == 3 && dir == -1)) {
                        StringBuilder nextState = new StringBuilder(current);

                        nextState.setCharAt(zeroIndex, current.charAt(newIndex));
                        nextState.setCharAt(newIndex, '0');

                        String nextStateStr = nextState.toString();
                        if (!visited.contains(nextStateStr)) {
                            queue.add(nextStateStr);
                            visited.add(nextStateStr);
                        }
                    }
                }
            }
            moves++;
        }
        return -1;
    }

    private String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    private boolean isSolvable(String start) {
        int[] puzzle = new int[6];
        for (int i = 0; i < 6; i++) {
            puzzle[i] = start.charAt(i) - '0';
        }

        int inversions = 0;
        for (int i = 0; i < 6; i++) {
            if (puzzle[i] == 0) continue;
            for (int j = i + 1; j < 6; j++) {
                if (puzzle[j] != 0 && puzzle[i] > puzzle[j]) {
                    inversions++;
                }
            }
        }
        return inversions % 2 == 0;
    }

    public static void main(String[] args) {
        SlidingPuzzle slide = new SlidingPuzzle();

        int[][] board1 = {{1, 2, 3}, {4, 0, 5}};
        System.out.println(slide.slidingPuzzle(board1)); // Output: 1

        int[][] board2 = {{1, 2, 3}, {5, 4, 0}};
        System.out.println(slide.slidingPuzzle(board2)); // Output: -1

        int[][] board3 = {{4, 1, 2}, {5, 0, 3}};
        System.out.println(slide.slidingPuzzle(board3)); // Output: 5
    }
}
