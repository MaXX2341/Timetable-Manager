package com.timetable.kevin.timetable_manager;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Felix Geier on 04.04.2018.
 */

public class Stundenplan {

    private String[] stundenArr = new String[9];
    private String[] zeitArr = new String[9];
    private String[] montagArr = new String[9];
    private String[] dienstagArr = new String[9];
    private String[] mittwochArr = new String[9];
    private String[] donnerstagArr = new String[9];
    private String[] freitagArr = new String[9];

    private ArrayList<String[]> sammelArr= new ArrayList<String[]>();

    public Stundenplan(){
        sammelArr.add(stundenArr);
        sammelArr.add(zeitArr);
        sammelArr.add(montagArr);
        sammelArr.add(dienstagArr);
        sammelArr.add(mittwochArr);
        sammelArr.add(donnerstagArr);
        sammelArr.add(freitagArr);
    }


    public void fillArraysWithValue(String[] valueArr,int spaltenNR){

        for (int i = 0; i < 9; i++) {
            sammelArr.get(spaltenNR)[i] = valueArr[i]; //--> syntax-Rate King!              //hier wird in einem Array aus arrays die Values abgespeichert, um sie dann einfacher hernehmen zu können, wenn sie in das Textfield geladen werden sollen
        }
    }
    public ArrayList<String[]> getStundenplanArrList(){
        return sammelArr;
    }

}
