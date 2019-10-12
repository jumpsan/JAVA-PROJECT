package com.company;

public class permtest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		permtest t=new permtest();
		int[] list= {1,2,3,4,5};
	    t.perm(list,0,4);
	}
	//前缀0:k  m:n递归
	public void perm(int[] list,int k,int m) {
		if(k==m) {
			//输出当前的序列
			for(int i=0;i<=k;i++) {
				System.out.print(list[i]+" ");
			}
			System.out.println();
		}else {
			for(int i=k;i<=m;i++) {
				swap(list,k,i);  //使k位于不同位置
				perm(list,k+1,m); //对k+1：m组合
				swap(list,k,i);  //交换
			}
		}
		
	}
	
	
	public void swap(int[] list,int a,int b) {
		int temp=list[a];
		list[a]=list[b];
		list[b]=temp;
	}
}
