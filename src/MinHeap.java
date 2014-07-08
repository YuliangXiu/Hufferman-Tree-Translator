public class MinHeap {

	static int[] heapArray;// ������ݵ�����
	static int select = -1;
	static int heapCurrentSize;// ��ǰ�ѵĴ�С
	static String convertstr = "";
	int heapMaxSize;// �ѵ��������

	// ������˳�����Ϊ��С�ѵ�˳��
	public MinHeap(int a[], int n) {
		heapMaxSize = n;
		heapCurrentSize = n;
		heapArray = new int[heapMaxSize * 2 - 1];

		// bubbleSort(a);

		for (int j = 0; j < n; j++)
			heapArray[j] = a[j + 1];

		int i = (heapCurrentSize - 2) / 2;
		while (i >= 0) {
			FilterDown(i);
			i--;
		}
		if (heapMaxSize % 2 == 0) {
			for (int k = 0; k < n; k++) {
				heapArray[k] = heapArray[k + 1];
			}
		}
	}
	
	public String ConvertStr(int[] convertArray){
		for(int i=0;i<convertArray.length;i++)
			this.convertstr += convertArray[i]+"-";
		return this.convertstr;
	}

	// ð�������㷨

	public int[] bubbleSort(int[] args) {

		for (int i = 0; i < args.length - 1; i++) {
			for (int j = i + 1; j < args.length; j++) {
				if (args[i] > args[j]) {
					int temp = args[i];
					args[i] = args[j];
					args[j] = temp;
				}
			}
		}
		return args;
	}

	// ����С���в���Ԫ�غ��Զ�����Ϊ�µ���С��
	public void Insert(int d) {
		heapArray[heapCurrentSize] = d;
		// FilterUp(heapCurrentSize);
		heapCurrentSize++;
	}

	// Ѱ����С��������Ȩֵ��С��Ԫ��
	public int Findmin() {

		int[] heapArray2 = new int[heapCurrentSize]; // �������з�0�����С��
		for (int i = 0; i < heapCurrentSize; i++) {
			if (heapArray[i] != 0) {
				heapArray2[i] = heapArray[i];
			}
		}
		bubbleSort(heapArray2);

		select++;
		return heapArray2[select];

	}

	// �Դ�ֵΪ��ڶ�������е���ʹ������С�ѣ��������ǰ������
	public void FilterDown(int start) {

		int i = start;
		int j = 2 * i + 1;
		int temp = heapArray[i];
		while (j <= heapCurrentSize - 1) {
			if (j <= heapCurrentSize - 1 && heapArray[j] > heapArray[j + 1])
				j++;
			if (temp <= heapArray[j])
				break;
			else {
				heapArray[i] = heapArray[j];
				i = j;
				j = 2 * j + 1;
			}
		}
		heapArray[i] = temp;

	}

	// �Դ�ֵΪ��ڶ�������е���ʹ������С�ѣ�����ǰ�����������
	public void FilterUp(int p) {
		int j = p, i;
		int temp = heapArray[j];
		i = (j - 1) / 2;
		while (j > 0) {
			if (heapArray[i] <= temp)
				break;
			else {
				heapArray[j] = heapArray[i];
				j = i;
				i = (j - 1) / 2;
			}
		}
		heapArray[j] = temp;
	}

}
