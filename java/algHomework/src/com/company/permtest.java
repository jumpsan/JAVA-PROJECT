package com.company;

public class permtest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		permtest t=new permtest();
		int[] list= {1,2,3,4,5};
	    t.perm(list,0,4);
	}
	//ǰ׺0:k  m:n�ݹ�
	public void perm(int[] list,int k,int m) {
		if(k==m) {
			//�����ǰ������
			for(int i=0;i<=k;i++) {
				System.out.print(list[i]+" ");
			}
			System.out.println();
		}else {
			for(int i=k;i<=m;i++) {
				swap(list,k,i);  //ʹkλ�ڲ�ͬλ��
				perm(list,k+1,m); //��k+1��m���
				swap(list,k,i);  //����
			}
		}
		
	}
	
	
	public void swap(int[] list,int a,int b) {
		int temp=list[a];
		list[a]=list[b];
		list[b]=temp;
	}
}
