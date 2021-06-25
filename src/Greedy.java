package com.company;

import java.util.Iterator;

import java.util.Arrays;

public class Greedy {
    static int[] WN = {3,4,2,6,1,9,8,8,5};
    static int[][] WE= {{0,1,5,0,0,0,0,0,0},{0,0,0,6,2,0,0,0,0},{0,0,0,0,0,9,3,0,0},{0,0,0,0,0,0,0,6,4},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};

    public static void GreedyFunc() {
        Arrays.sort(WN);

        int clmn,NodeWay,parentindex=0,sumofWeight=0,childindex=1,childindex2=0;
        while(parentindex<WN.length) { // Node Weight Calc
            for(clmn = 0;clmn<WE[0].length;clmn++) {
                if(WE[parentindex][clmn]!=0) break;
            }
            if(clmn==9) break;
            NodeWay = WE[parentindex][clmn] + WN[childindex];
            if(NodeWay<WE[parentindex][clmn+1] + WN[childindex+1]) { //Left Node
                sumofWeight+=NodeWay;
                parentindex=childindex;
            }
            else {
                sumofWeight+=WE[parentindex][clmn+1] + WN[childindex+1];  //Right Node
                parentindex=childindex + 1;
            }
            childindex2=parentindex;
            childindex=(parentindex*2)+1;
            if (childindex>WN.length) {
                break;
            }
        }
        System.out.println("Total Weight : " + (sumofWeight += WN[0]) + " Child �ndex : " + childindex2 + " Child : " + WN[childindex2]);
    }

    public static void main(String[] args) {
        GreedyFunc();
    }

}