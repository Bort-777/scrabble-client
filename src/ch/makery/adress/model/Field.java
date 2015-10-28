/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.adress.model;

import java.util.ArrayList;

/**
 *
 * @author Администратор
 */
public class Field {

    ArrayList<Block> blocks;

    public Field() {
        blocks = new ArrayList<Block>();
        for (int i = 0; i < 12 * 12; i++) {
            blocks.add(new Block(""));
        }
    }

    public void setNewWord(String checkWord, int x, int y) {
        blocks.add(y * x + x, new Block("A"));
    }

    public String getBlock(int x, int y) {
        return blocks.get(y * x + x).getText();
    }

}
