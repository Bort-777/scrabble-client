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
public class MyPackage
        implements java.io.Serializable {

    private String answer;
    private ArrayList data;

    public MyPackage(String tmp1) {
        this.answer = tmp1;
    }

    public String toString() {
        return answer;
    }

    public ArrayList data() {
        return data;
    }

}
