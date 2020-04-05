package com.rickyin.coding.array;

/**
 * 一般像这种棋盘之类的问题,一般都是需要定义四个方向去做
 */
public class coding_999 {
    public static void main(String[] args) {

    }

    public int numRookCaptures(char[][] board) {
        /**
         * 定义4个方向
         */
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                /**
                 * 找到白色车
                 */
                if (board[i][j] == 'R') {
                    /**
                     * 找到白色车后，分别向四个方向遍历
                     */
                    for (int k = 0; k < 4; k++) {
                        int x = i;
                        int y = j;
                        while (true) {
                            x += dx[k];
                            y += dy[k];
                            if (x < 0 || x >= 8 || y < 0 || y >= 8 || board[x][y] == 'B') {
                                break;
                            }
                            if (board[x][y] == 'p') {
                                res++;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
}
