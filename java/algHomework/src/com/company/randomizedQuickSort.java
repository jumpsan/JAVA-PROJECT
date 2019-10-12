package com.company;

import java.util.Random;

public class randomizedQuickSort {
    public static void main(String args[]){
        int[] list= {8,4,3,7,1,5,6,2};
        randomizedSort(list,0,7);
        for(int e:list) {
            System.out.print(e);
        }
    }

    public static void randomizedSort(int[] list,int l,int r){
        if(l<r){
            int pivot=randomizedPartition(l,r,list);
            randomizedSort(list,l,pivot-1);
            randomizedSort(list,pivot+1,r);
        }


    }

    //随机基准
    public static int randomizedPartition(int l,int r,int[] list){
        Random random=new Random();
        int pivot=random.nextInt(r-l+1)+l;
        swap(list,pivot,l);
        return partition(list,l,r);
    }

    public static Integer partition(int[] list,int l,int r) {
        int i=l;
        int j=r+1;
        int x=list[i];//选取左下标为分界点
        while(true) {
            while(list[++i]<x && i<r);
            while(list[--j]>x);
            if(i>=j) break;
            swap(list,i,j);
        }
        list[l]=list[j];
        list[j]=x;
        return j;
    }
    public static void swap(int[] list,int a,int b) {
        int temp=list[a];
        list[a]=list[b];
        list[b]=temp;
    }
}
