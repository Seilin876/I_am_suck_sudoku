package com.example.sudoku;

import java.awt.event.ActionEvent;

public class easy extends GameModule {

    easy(int Mode, FrameSet frameset) {
        super(Mode, frameset);
    }
    @Override
    public  void setWindows(){
        super.setFrameTitle("¤Jªù");
        super.setWindows();
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        super.actionPerformed(actionEvent);
        if (super.getMODE() == Mode_simpleAI)
            JudgeGameOver(super.getScore());
    }
}
