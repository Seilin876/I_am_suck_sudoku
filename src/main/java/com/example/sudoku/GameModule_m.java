package com.example.sudoku;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class GameModule_m extends JPanel implements ActionListener {
    boolean ans=false;
    private FrameSet frameSet = null;
    JPanel jp = new JPanel();
    private JPanel jContentPane = null;
    private JFormattedTextField input ;
    final static int Mode_general = 0, Mode_unlimited = 1, Mode_simpleAI = 2;
    private boolean AI_move = true;
    private Timer Update_frequency;
    private final int UpdateDELAY_MS = 10;
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private int[] Score;
    private boolean isContinue = true;
    //private Ball ball = new Ball();
    private PlayPad_m playPad = new PlayPad_m();
    //private Operating operating = new Operating();
    private int MODE = -1;

    GameModule_m(int Mode, FrameSet frameset) {
        frameSet = frameset;
        initComponents();
        MODE = Mode;
        SCREEN_WIDTH = 400;
        SCREEN_HEIGHT = 400;
        //ball.setMode(MODE, SCREEN_WIDTH, SCREEN_HEIGHT);
        //operating.setMode(MODE);
        setWindows();
    }

    public void initComponents(){
        frameSet.add(this);
        this.setLayout(null);
        JButton btn1 = new JButton("返回目錄");
        btn1.setBounds(290, 300, 100, 50);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu p1=new menu(frameSet);//新建面板NewJPanel2，並將視窗n2傳入
                setVisible(false);//隱藏當前面板
                frameSet.add(p1);//在視窗中新增面板p1
                frameSet.setVisible(true);//顯示面板
            }
        });
        this.add(btn1);

        JButton btn2 = new JButton("看答案");
        btn2.setBounds(290, 150, 100, 50);
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ans=true;
                UpdateFrequency();
            }
        });
        this.add(btn2);
        //JTextField jtf1 = new JTextField("");
        //jtf1.setBounds( 290, 100,100,25);
        //this.add(jtf1);
    }

    public void setWindows() {
        this.setFocusable(true);            //http://hk.uwenku.com/question/p-scplcwbw-bgh.html
        //this.addKeyListener(operating);
        //operating.setOperating(playPad, SCREEN_HEIGHT, this);

        frameSet.setVisible(true);
        frameSet.add(this);

        playPad.initPlayerPos(SCREEN_WIDTH, SCREEN_HEIGHT);
        UpdateFrequency();
    }

    public void setFrameTitle(String Title) {
        frameSet.setTitle(Title);
    }

    public int getMODE() {
        return MODE;
    }

    public int[] getScore() {
        return Score;
    }

    public void update(Graphics g) {
        this.paint(g);
    }

    public void paint(Graphics g) {
        super.paint(g);         //?????OWindow??paint
        playPad.lattice(g);
        if(ans==false)playPad.num(g);
        if(ans==true)playPad.ans(g);
        //ball.drawBall(g);
    }

    public void ans(Graphics g) {
        playPad.ans(g);
        //ball.drawBall(g);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (isContinue) {
            //ball.BallMove();
            // Score = ball.checkBallPosRange(SCREEN_WIDTH, SCREEN_HEIGHT, playPad.getPlayerScore());
            //playPad.setPlayerScore(Score);
            //ball.Ball_Pad_interactive(playPad.getPlayerPosX(), playPad.getPlayerPosY(), playPad.getPad_Width_Height());
            repaint();
        }
    }


    public void JudgeGameOver(int[] Score) {
        if (isContinue) {
            for (int i = 0; i < 2; i++) {
                if (Score[i] > 0) {
                    terminate();
                    //operating.JudgeContinue(isContinue);
                    GameOver p1 = new GameOver(frameSet);//?s????ONewJPanel2?A??N????n2??J
                    setVisible(false);//???÷?e???O
                    frameSet.add(p1);//?b???????s?W???Op1
                    frameSet.setVisible(true);//?????O
                }
            }
        }
    }


    private void terminate() {
        isContinue = false;//https://openhome.cc/Gossip/JavaEssence/StopThread.html
    }

    private void UpdateFrequency() {
        Update_frequency = new Timer(UpdateDELAY_MS, this);
        Update_frequency.setInitialDelay(1);
        Update_frequency.start();
        if (MODE == Mode_simpleAI) {
            Timer test = new Timer(500, new WorkJob());
            test.start();
        }
    }

    public class WorkJob implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (AI_move)
                playPad.UP(0);
            else
                playPad.Down(0);
            if(playPad.checkPadPosRange(SCREEN_HEIGHT)){
                AI_move = !AI_move;
            }

        }
    }

}
