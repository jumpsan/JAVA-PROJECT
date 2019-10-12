package shiyan1;


//��¼���У�25, 30, 11, 7, 22, 16, 18, 33, 40, 55��
//��������
public class SortPracticec {
	public static void main(String[] args) {
		int[] sequeen={25, 30, 11, 7, 22, 16, 18, 33, 40, 55};
//		Bubble(sequeen);
//		Select(sequeen);
//		Insert(sequeen);
		Heap(sequeen);
		for(int element:sequeen) {
			System.out.println(element);
		}
	}
	
	//ð��
	public static void Bubble(int[] example) {
		boolean flag=false;//�ж��Ƿ��������
		for(int count=example.length-2;count>=0;count--) {
			flag=false;
			for(int j=0;j<count;j++) {
				if(example[j]>example[j+1]) {
					int temp=example[j];
					example[j]=example[j+1];
					example[j+1]=temp;
					flag=true;
				}
			}
			if(!flag) {
				return;
			}
		}
	}
	
	public static void sort(int[] example,int tail) {
		for(int count=tail/2-1;count>=0;count--) {  //�����һ����Ҷ�ڵ㿪ʼ
			if(example[count]<example[count*2+1]) {
				int temp=example[count];
				example[count]=example[count*2+1];
				example[count*2+1]=temp;
			}
			//�ж��Ƿ�����ҽ��
			if(count*2+2<tail && example[count]<example[count*2+2]) {
				int temp=example[count];
				example[count]=example[count*2+2];
				example[count*2+2]=temp;
			}
		}
	}
	
	//������
	public static void Heap(int[] example) {
		//�ȹ���󶥶�
		//i> 2i+1 i>2i+2 
		//��ȫ�����������һ����Ҷ�ڵ�  length/2-1
		sort(example,example.length);
		//���һ�����Ͷ��˽�����Ȼ�����
		for(int count=example.length-1;count>=0;count--) {
			int temp=example[count];
			example[count]=example[0];
			example[0]=temp;
			sort(example,count);
		}
	}
	
	
	//ֱ��ѡ������
	public static void Select(int[] example) {
		int min=Integer.MAX_VALUE;
		int min_index=0;
		for(int count=0;count<example.length-1;count++) {
			min_index=count;
			min=example[count];
			for(int j=count;j<example.length;j++) {
				if(example[j]<min) {
					min=example[j];
					min_index=j;
				}
			}
			int temp=example[count];
			example[count]=min;
			example[min_index]=temp;
		}
	}
	
	//ֱ�Ӳ�������
	public static void Insert(int[] example) {
		boolean flag=false;
		for(int count=0;count<example.length-1;count++) {
			flag=false;
			for(int j=count+1;j>0;j--){
				if(example[j]<example[j-1]) {
					int temp=example[j];
					example[j]=example[j-1];
					example[j-1]=temp;
					flag=true;
				}
				if(!flag) {
					break;
				}
			}
		}
	}
}
