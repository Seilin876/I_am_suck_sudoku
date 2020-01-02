package com.example.sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static com.example.sudoku.GameModule.Mode_general;
import static com.example.sudoku.GameModule.Mode_simpleAI;
import static com.example.sudoku.GameModule.Mode_unlimited;
import static com.example.sudoku.GameModule.Mode_simpleAI;
public class PlayPad {
    String[][] c= new String[9][9];
    JPanel jp;
    private JFormattedTextField input = null;
    private int InitialPad_Width = 5;
    private int InitialPad_Height = 300;
    private int PAD_OFFSET = 10;
    private int playerSpeedY = 20;
    private int PLAYER_NUM = 2;
    private int[] playerPosX = new int[PLAYER_NUM];
    private int[] playerPosY = new int[PLAYER_NUM];
    private int[] playerScore = new int[PLAYER_NUM];
    private static JTextField t2;


    public void initPlayerPos(int screen_Width, int screen_Height) {
        for (int i = 0; i < PLAYER_NUM; i++) {
            playerPosY[i] = screen_Height / 2;
        }
        int WidthCorrectionLength = 10;
        playerPosX[0] = PAD_OFFSET;
        playerPosX[1] = screen_Width - InitialPad_Width - PAD_OFFSET - WidthCorrectionLength;
    }



    public void lattice(Graphics g) {
        int k, j;
        for (int i = 10; i < 310; i = i + 30) {
            k = i;
            j = i + 20;
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(k, 35, 3, 265);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(15, j, 265, 3);
        }

        for (int i = 10; i < 310; i = i + 30) {
            k = i;
            j = i + 20;
            if (k == 10 | k == 100 | k == 190 | k == 280) {
                g.setColor(Color.BLACK);
                g.fillRect(k, 30, 5, 275);
            }
            if (j == 30 | j == 120 | j == 210 | j == 300) {
                g.setColor(Color.BLACK);
                g.fillRect(15, j, 270, 5);
            }
        }
    }

    public void Down(int player) {
        playerPosY[player] += playerSpeedY;
    }

    public void UP(int player) {
        playerPosY[player] -= playerSpeedY;
    }

    public boolean checkPadPosRange(int screen_Height) {
        boolean HitTheWall = false;
        for (int i = 0; i < PLAYER_NUM; i++) {
            if (playerPosY[i] < 0) {
                playerPosY[i] = 0;
                HitTheWall = true;
            }

            if (playerPosY[i] > screen_Height - InitialPad_Height) {
                playerPosY[i] = screen_Height - InitialPad_Height;
                HitTheWall = true;
            }
        }
        return HitTheWall;
    }
    private String[][] make_board(int m) {
        String[][] board = null;
        int n = m * m;
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        while (board == null) {
            board = get_board(m, numbers);
        }
        return board;
    }
    public String[][] get_board(int m, List<Integer> numbers) {
        int n = m * m;
        String[][] board = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean ok = true;
// 打亂順序
                Collections.shuffle(numbers);
                for (int x = 0; x < n; x++) {
                    if (isOk(numbers.get(x) +  "", board, i, j, m)) {
                        board[i][j] = numbers.get(x) +"";
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    return null;
                }
            }
        }
        return board;
    }
    public void print_board_easy(String[][] board, int m,Graphics g) {
        int n = m * m;
        int hard =7;
        String[][] b = new String[n][n];
        System.arraycopy(board, 0, b, 0, board.length);
        Random rd = new Random();
        for (int i = 0; i < hard; i++ ) {
            for (int j = 0; j < n; j++) {
                int x = rd.nextInt(9);
                b[x][j] = null;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (null == board[i][j]) {
                    //input.setBounds(new Rectangle(, 30, 30));  //猜數字格子 前2位置 後2長寬
                    //FrameSet.getInput(30*i+22, 30*j+50).requestFocus();
                    // jContentPane.add(getInput(i,j), null);
                    //g.setColor(Color.BLACK);
                    //g.drawString("." , 30*i+22, 30*j+50);

                } else {
                    g.setColor(Color.BLACK);
                    g.drawString("" + b[i][j], 30*i+22, 30*j+50);
                }
            }
        }
    }

    private boolean isOk(String x, String[][] board, int i, int j, int m) {
        int i0 = i - i % m, j0 = j - j % m;
        int n = m * m;
        for (int k = 0; k < n; k++) {
            if (x.equals(board[i][k]) || x.equals(board[k][j])) {
                return false;
            }
        }
        for (int k = i0; k < i; k++ ) {
            for (int g = j0; g < j0+m;g++) {
                if (board[k][g].equals(x)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void num(Graphics g) {

        jp = new JPanel();
        PlayPad s = new PlayPad();
        int m = 3,i=5,j=5;
        String[][] b = s.make_board(m);
        for (int z = 0; z < 9; z++ ) {
            for (int zz = 0; zz < 9; zz++) {
                c[z][zz]=b[z][zz];
            }
        }
        s.print_board_easy(b,m,g);
        //this.getInput(i,j).requestFocus();
    }
    public void ans(Graphics g) {
        jp = new JPanel();
        PlayPad s = new PlayPad();
        int m = 3,i=5,j=5;
        String[][] b = s.make_board(m);
        for (int z = 0; z < 9; z++ ) {
            for (int zz = 0; zz < 9; zz++) {
                g.drawString("" + c[z][zz], 30*z+22, 30*zz+50);
            }
        }
    }

}

