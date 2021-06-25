package com.company;

import java.util.Arrays;

public class DynamicProg {

    static int[] WN = {3,4,2,6,1,9,8,8,5};
    static int[][] WE= {{0,1,5,0,0,0,0,0,0},{0,0,0,6,2,0,0,0,0},{0,0,0,0,0,9,3,0,0},{0,0,0,0,0,0,0,6,4},{0,0,0,0,0,0,0,0,0}};

    public static void DynamicFunc() {
        Arrays.sort(WN);
        int parentindex=0,childindex=1,clmn,weight=0,weightright=0,weightleft=0,temppindex,minweightright=0,minweightleft=0;
        int[] sumofWeight= {0,0};
        while(childindex<WN.length) { // Left Node
            for(clmn = 0;clmn<WE[0].length;clmn++) {
                if(WE[parentindex][clmn]!=0) break;
            }
            if(childindex==1)
                weight=WE[parentindex][clmn]+WN[childindex];
            else {
                if(((parentindex*2+2)*2)+2>=WN.length &&  childindex*2+1>=WN.length) {//Same Depth
                    weightleft+=Math.min(WE[parentindex][clmn]+WN[childindex],WE[parentindex][clmn+1]+WN[parentindex*2+2]);
                }
                if((childindex*2+1<WN.length &&  ((parentindex*2+2)*2)+2>=WN.length)) {//Right Node Final Depth
                    weightright+=WE[parentindex][clmn+1]+WN[childindex+1];
                    weightleft+=WE[parentindex][clmn]+WN[parentindex*2+1];
                }
                if(childindex*2+1>WN.length) { // Son Eleman Sonrasý Sol Aðacýn Min Elemanýný Bul
                    minweightleft=Math.min(weightleft, weightright)+weight+WN[0];
                }
            }
            parentindex=childindex;
            childindex=childindex*2+1;
        }

        //----------- Right Node
        parentindex=0; childindex=2; weight=0; weightright=0; weightleft=0;

        while(childindex<WN.length) { // Right Node
            for(clmn = 0;clmn<WE[0].length;clmn++) {
                if(WE[parentindex][clmn]!=0) break;
            }
            if(childindex==2)
                weight=WE[parentindex][clmn+1]+WN[childindex];
            else {
                if(((parentindex*2+1)*2)+1>=WN.length &&  childindex*2+2>=WN.length) {//Same Depth
                    if(weightleft==0)
                        weightright+=Math.min(WE[parentindex][clmn+1]+WN[childindex],WE[parentindex][clmn]+WN[parentindex*2+1]);
                }
                if(((parentindex*2+1)*2+1<WN.length &&  childindex*2+2>=WN.length)) {//Right Node Final Depth
                    weightright+=WE[parentindex][clmn+1]+WN[childindex];
                    weightleft+=WE[parentindex][clmn]+WN[parentindex*2+1];
                }
                if(childindex*2+1>WN.length) { // Son Eleman Sonrasý Sað Aðacýn Min Elemanýný Bul
                    if(weightleft!=0)
                        minweightright=Math.min(weightleft, weightright);
                    else
                        minweightright=weightright;
                }
                if(childindex*2+2>WN.length) { // Son Eleman Sonrasý Sol Aðacýn Min Elemanýný Bul
                    if(weightleft!=0)
                        minweightright=Math.min(weightleft, weightright)+weight+WN[0];
                    else if(weightright!=0)
                        minweightright=weight+WN[0]+weightright;
                }
            }
            parentindex=childindex;
            childindex=childindex*2+2;
        }
        weight=Math.min(minweightright, minweightleft);
        System.out.println("Best Way on Tree : " + weight);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int parentindex=0,childindex=2,clmn;
        DynamicFunc();

    }

}
