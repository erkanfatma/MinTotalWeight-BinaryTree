package com.company;

import java.util.Arrays;

/**  @author  Fatma ERKAN - 20150807029 **/

public class Main {

    /* First array list */
  /*  static int[] WN = {3,4,2,6,1,9,8,8,5};
    static int[][] WE=
            {{0,1,5,0,0,0,0,0,0},
             {0,0,0,6,2,0,0,0,0},
             {0,0,0,0,0,9,3,0,0},
             {0,0,0,0,0,0,0,6,4},
             {0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0}};
*/
    /* Second array list */
  /*  static int[] WN =   {5,7,9,11,3,4,6,9,10,1,2,7};
    static int[][] WE= {{0,2,3,0,0,0,0,0,0,0,0,0},
                        {0,0,0,1,5,0,0,0,0,0,0,0},
                        {0,0,0,0,0,9,1,0,0,0,0,0},
                        {0,0,0,0,0,0,0,3,2,0,0,0},
                        {0,0,0,0,0,0,0,0,0,1,7,0},
                        {0,0,0,0,0,0,0,0,0,0,0,3},
                        {0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0}, };
*/

    /* Third array list */
    static int[] WN = {3,4,2,6,1,9};
    static int[][] WE= {{0,1,5,0,0,0},
            {0,0,0,6,2,0},
            {0,0,0,0,0,9},
            {0,0,0,0,0,0},
            {0,0,0,0,0,0},
            {0,0,0,0,0,0}};

    public static void main(String[] args) {

        /** Call Greedy Algorithm */
        System.out.println("-------------------------------");
        System.out.println("Greedy algorithm is running");
        long startGreedy = System.nanoTime();
        GreedyFunc();
        long endGreedy = System.nanoTime();
        long elapsedTimeforGreedy = endGreedy - startGreedy;
        System.out.println("Greedy Algorithm - Elapsed time is " + elapsedTimeforGreedy + " ns");
        System.out.println("-------------------------------");

        /** Call Recursive Algorithm */
        System.out.println("Recursive algorithm is running");
        long startRecursive = System.nanoTime();
        RecursiveFunc(0);
        long endRecursive = System.nanoTime();
        long elapsedTimeforRecursive = endRecursive - startRecursive;
        System.out.println("Recursive Algorithm - Elapsed time is " + elapsedTimeforRecursive + " ns");
        System.out.println("-------------------------------");

        /** Call Dynamic Programming Algorithm */
        System.out.println("Dynamic programming is running");
        //int parentindex=0,childindex=2,clmn;
        long startDynamic = System.nanoTime();
        DynamicFunc();
        long endDynamic = System.nanoTime();
        long elapsedTimeforDynamic = endDynamic - startDynamic;
        System.out.println("Dynamic Programming Algorithm - Elapsed time is " + elapsedTimeforDynamic + " ns");
        System.out.println("-------------------------------");
    }

    /** GREEDY FUNCTION **/
    public static void GreedyFunc() {
        Arrays.sort(WN);

        int col;
        int nodePath;
        int parent=0;
        int weightSum=0;
        int child1=1;
        int child2=0;
        // Weight of node calculation
        while(parent<WN.length) {
            for(col = 0;col<WE[0].length;col++) {
                if(WE[parent][col]!=0) break;
            }
            if(col==9) break;
            nodePath = WE[parent][col] + WN[child1];
            if(nodePath<WE[parent][col+1] + WN[child1+1]) {
                //Left node
                weightSum+=nodePath;
                parent=child1;
            }
            else {
                //Right node
                weightSum+=WE[parent][col+1] + WN[child1+1];
                parent=child1 + 1;
            }
            child2=parent;
            child1=(parent*2)+1;
            if (child1>WN.length) {
                break;
            }
        }
        System.out.println("Total Weight is: " + (weightSum += WN[0]) + " Child Index is: " + child2 + " Child is: " + WN[child2]);
    }

    /** RECURSIVE FUNCTION **/
    static int x=0;
    static int leftSum;
    static int compress;
    public static int RecursiveFunc(int parent) {
        int[] weightSum= {0,0};
        int check=0;
        if(x==0) {
            Arrays.sort(WN);
            x++;
            check++;
        }
        int col=0;
        int child=0;
        int rightWeight=0;
        int leftWeight=0;
        for(col = 0;col<WE[0].length;col++) {
            if(WE[parent][col]!=0) break;
        }

        // Right Leaf Recursive
        if(parent*2+2<WN.length){
            child=(parent*2)+2;
            rightWeight=WE[parent][col+1]+WN[child];
            if(child*2+2<WN.length)
                if(leftSum==0)
                    weightSum[1]+=RecursiveFunc(child);
                else
                    weightSum[0]+=RecursiveFunc(child);

        }

        if(((parent*2+1)*2)+1<WN.length  &&  child*2+2>WN.length)
            compress=1;

        // Left Leaf Recursive
        if(parent*2+1<WN.length){
            if(check==1) {
                weightSum[1]+=rightWeight;
                leftSum=1;
            }
            child=(parent*2)+1;
            leftWeight=WE[parent][col]+WN[child];
            if(child*2+1<WN.length)
                if(leftSum==1)
                    weightSum[0]+=RecursiveFunc(child);
                else
                    weightSum[1]+=RecursiveFunc(child);
        }

        if(child*2+1>=WN.length &&  child*2+2>=WN.length) {
            if(leftWeight<rightWeight)
                return leftWeight;
            else
                return rightWeight;

        }

        if(compress==1) {
            if(leftWeight+weightSum[0]<rightWeight) {
                compress=0;
                return leftWeight+weightSum[0];
            }
            else {
                compress=0;
                weightSum[0]=0;
                return rightWeight;
            }
        }

        if(weightSum[0]+leftWeight<weightSum[1]) {
            weightSum[0]+=leftWeight+WN[0];
            System.out.println("Total Weight is: " + (weightSum[0]) );
            return weightSum[0];
        }
        else {
            weightSum[1]+=WN[0];
            System.out.println("Total Weight is: " + weightSum[1]);
            return weightSum[1];
        }

    }

    /** DYNAMIC PROGRAMMING FUNCTION **/
    public static void DynamicFunc() {
        Arrays.sort(WN);
        int parent=0;
        int child=1;
        int col;
        int weightSum=0;
        int rightWeight=0;
        int leftWeight=0;
        int rightMinWeight=0;
        int leftMinWeight=0;

        // Left Node
        while(child<WN.length) {
            for(col = 0;col<WE[0].length;col++) {
                if(WE[parent][col]!=0) break;
            }
            if(child==1)
                weightSum=WE[parent][col]+WN[child];
            else {
                //Same Depth
                if(((parent*2+2)*2)+2>=WN.length &&  child*2+1>=WN.length) {
                    leftWeight+=Math.min(WE[parent][col]+WN[child],WE[parent][col+1]+WN[parent*2+2]);
                }
                //Right Node Final Depth
                if((child*2+1<WN.length &&  ((parent*2+2)*2)+2>=WN.length)) {
                    rightWeight+=WE[parent][col+1]+WN[child+1];
                    leftWeight+=WE[parent][col]+WN[parent*2+1];
                }
                //After last element left tree find min element
                if(child*2+1>WN.length) {
                    leftMinWeight=Math.min(leftWeight, rightWeight)+weightSum+WN[0];
                }
            }
            parent=child;
            child=child*2+1;
        }

        // Right Node
        parent=0;
        child=2;
        weightSum=0;
        rightWeight=0;
        leftWeight=0;

        while(child<WN.length) {
            for(col = 0;col<WE[0].length;col++) {
                if(WE[parent][col]!=0) break;
            }
            if(child==2)
                weightSum=WE[parent][col+1]+WN[child];
            else {
                //Same Depth
                if(((parent*2+1)*2)+1>=WN.length &&  child*2+2>=WN.length) {
                    if(leftWeight==0)
                        rightWeight+=Math.min(WE[parent][col+1]+WN[child],WE[parent][col]+WN[parent*2+1]);
                }
                //Right Node Final Depth
                if(((parent*2+1)*2+1<WN.length &&  child*2+2>=WN.length)) {
                    rightWeight+=WE[parent][col+1]+WN[child];
                    leftWeight+=WE[parent][col]+WN[parent*2+1];
                }
                //After last element right tree find min element
                if(child*2+1>WN.length) {
                    if(leftWeight!=0)
                        rightMinWeight=Math.min(leftWeight, rightWeight);
                    else
                        rightMinWeight=rightWeight;
                }
                //After last element left tree find min element
                if(child*2+2>WN.length) {
                    if(leftWeight!=0)
                        rightMinWeight=Math.min(leftWeight, rightWeight)+weightSum+WN[0];
                    else if(rightWeight!=0)
                        rightMinWeight=weightSum+WN[0]+rightWeight;
                }
            }
            parent=child;
            child=child*2+2;
        }
        weightSum=Math.min(rightMinWeight, leftMinWeight);
        System.out.println("Total Weight is: " + weightSum);
    }

}
