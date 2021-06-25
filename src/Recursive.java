package com.company;

import java.util.Arrays;

public class Recursive {
    static int[] WN = {3,4,2,6,1,9,8,8,5};
    static int[][] WE= {{0,1,5,0,0,0,0,0,0},{0,0,0,6,2,0,0,0,0},{0,0,0,0,0,9,3,0,0},{0,0,0,0,0,0,0,6,4},{0,0,0,0,0,0,0,0,0}};
    static int sumof=0,x=0,sumleftside,compress,left=0;
    public static int RecursiveFunc(int parentindex ) {
        int[] sumofWeight= {0,0};
        int check=0;
        if(x==0) {
            Arrays.sort(WN);
            x++;
            check++;
        }
        int weight=0,clmn=0,childindex=0,weightright=0,weightleft=0;
        for(clmn = 0;clmn<WE[0].length;clmn++) {
            if(WE[parentindex][clmn]!=0) break;
        }

        if(parentindex*2+2<WN.length){ // Right Leaf Recursive
            childindex=(parentindex*2)+2;
            weightright=WE[parentindex][clmn+1]+WN[childindex];
            if(childindex*2+2<WN.length)
                if(sumleftside==0)
                    sumofWeight[1]+=RecursiveFunc(childindex);
                else
                    sumofWeight[0]+=RecursiveFunc(childindex);

        }

        if(((parentindex*2+1)*2)+1<WN.length  &&  childindex*2+2>WN.length)
            compress=1;

        if(parentindex*2+1<WN.length){ // Left Leaf Recursive
            if(check==1) {
                sumofWeight[1]+=weightright;
                sumleftside=1;
            }
            childindex=(parentindex*2)+1;
            weightleft=WE[parentindex][clmn]+WN[childindex];
            if(childindex*2+1<WN.length)
                if(sumleftside==1)
                    sumofWeight[0]+=RecursiveFunc(childindex);
                else
                    sumofWeight[1]+=RecursiveFunc(childindex);


        }

        if(childindex*2+1>=WN.length &&  childindex*2+2>=WN.length) {
            if(weightleft<weightright)
                return weightleft;
            else
                return weightright;

        }

        if(compress==1) {
            if(weightleft+sumofWeight[0]<weightright) {
                compress=0;
                return weightleft+sumofWeight[0];
            }
            else {
                compress=0;
                sumofWeight[0]=0;
                return weightright;
            }
        }


        if(sumofWeight[0]+weightleft<sumofWeight[1]) {
            sumofWeight[0]+=weightleft+WN[0];
            System.out.println("Best Way on Tree : " + (sumofWeight[0]) );
            return sumofWeight[0];
        }
        else {
            sumofWeight[1]+=WN[0];
            System.out.println("Best Way Tree : " + sumofWeight[1]);
            return sumofWeight[1];
        }


    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(RecursiveFunc(0));
    }

}
