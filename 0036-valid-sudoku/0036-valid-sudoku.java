class Solution {
    public boolean isValidSudoku(char[][] board) {
        int n = 9;

        // Check rows
        for (int i = 0; i < n; i++) {
            boolean[] seen = new boolean[10];

            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') continue;

                int num = board[i][j] - '0';

                if (seen[num]) return false;

                seen[num] = true;
            }
        }


        for (int j = 0; j < n; j++) {
            boolean[] seen = new boolean[10];

            for (int i = 0; i < n; i++) {
                if (board[i][j] == '.') continue;

                int num = board[i][j] - '0';

                if (seen[num]) return false;

                seen[num] = true;
            }
        }


        for (int boxRow = 0; boxRow < 9; boxRow += 3) {
            for (int boxCol = 0; boxCol < 9; boxCol += 3) {

                boolean[] seen = new boolean[10];

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        char c = board[boxRow + i][boxCol + j];
                        if (c == '.') continue;

                        int num = c - '0';

                        if (seen[num]) return false;

                        seen[num] = true;
                    }
                }
            }
        }

        return true;
    }
}
