public class MinHeap {

	static int[] heapArray;// 存放数据的数组
	static int select = -1;
	static int heapCurrentSize;// 当前堆的大小
	static String convertstr = "";
	int heapMaxSize;// 堆的最大容量

	// 将数组顺序调整为最小堆的顺序
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

	// 冒泡排序算法

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

	// 将最小堆中插入元素后自动调整为新的最小堆
	public void Insert(int d) {
		heapArray[heapCurrentSize] = d;
		// FilterUp(heapCurrentSize);
		heapCurrentSize++;
	}

	// 寻找最小堆数组中权值最小的元素
	public int Findmin() {

		int[] heapArray2 = new int[heapCurrentSize]; // 包含所有非0点的最小堆
		for (int i = 0; i < heapCurrentSize; i++) {
			if (heapArray[i] != 0) {
				heapArray2[i] = heapArray[i];
			}
		}
		bubbleSort(heapArray2);

		select++;
		return heapArray2[select];

	}

	// 以传值为入口对数组进行调整使其变成最小堆（从最后往前调整）
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

	// 以传值为入口对数组进行调整使其变成最小堆（从最前面往后调整）
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
