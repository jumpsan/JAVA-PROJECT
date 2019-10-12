package com.company;

public class binarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] list= {1, 8, 12, 15, 16, 21, 30, 35, 39};
//		Integer re=search(list,0,8,20);
//		System.out.print(re);
		int[] rel=new int[2];
		Integer re2=search2(list,0,8,10,rel);
		System.out.print(rel[0]+" "+rel[1]);
	}
	public static Integer search(int[] list,int left,int right,int goal) {
		if(left>right) {
			return -1;
		}
		if(left==right) {
			if(goal==list[left]) {
				return left;
			}else {
				return -1;
			}
		}
		int center=(left+right)/2;
		if(list[center]>goal) {
			return search(list,left,center-1,goal);
		}else if(list[center]<goal){
			return search(list,center+1,right,goal);
		}else {
			return center;
		}
	}
	
	public static Integer search2(int[] list,int l,int r,int goal,int[] re) {
		if(l==r) {
			if(goal==list[l]) {
				return l;
			}else {
				if(goal>list[l]) {
					re[0]=l;
					re[1]=l+1;
				}else {
					re[0]=l-1;
					re[1]=l;
				}
				return -1;
			}
		}else if(l>r) {
			re[0]=r;
			re[1]=l;
			return -1;
		}
		else {
			int center=(l+r)/2;
			if(list[center]>goal) {
				return search2(list,l,center-1,goal,re);
			}else if(list[center]<goal){
				return search2(list,center+1,r,goal,re);
			}else {
				return center;
			}
		}
		
	}
}
