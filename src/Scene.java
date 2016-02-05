import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JPanel;

public class Scene extends JPanel{

	private ArrayElement[] elements;
	private long delay;
	
	public Scene() {	
		delay = 200;
		setBackground(Color.black);
	}
	public Scene(long delay) {
		this.delay = delay;
		setBackground(Color.black);
	}
	
	public void bubbleSort() {

		for (int i = 0; i <= elements.length - 1; i++) {
			for (int j = 0; j < elements.length - 1; j++)
				if (elements[j].value > elements[j + 1].value) {
					swap(j,j+1);
				}
			setSorted(elements.length-1-i);
		}

	}
	
	public void selectionSort() {

		int min;
		for (int i = 0; i < elements.length - 1; i++) {
			min = i;
			selectElement(min);
			
			for (int j = i + 1; j < elements.length; j++) {
				brightenElement(j);
				if (elements[j].value < elements[min].value) {
					unselectElement(min);
					min = j;
					selectElement(min);
				}
				darkenElement(j);
			}
			unselectElement(min);
			swap(min, i);
			setSorted(i);
		}
		setSorted(elements.length-1);
	}

	public void insertionSort() {
		int sortedIndex;

		for (int i = 0; i < elements.length; i++) {
			sortedIndex = i;
			selectElement(sortedIndex);
			unselectElement(sortedIndex);
			setSorted(sortedIndex);
			while (sortedIndex > 0 && elements[sortedIndex - 1].value > elements[sortedIndex].value) {
				swap(sortedIndex - 1, sortedIndex);
				sortedIndex--;
			}		
		}
	}
	
	public void quickSort(int start, int end) {
		if (start < end) {
			int pivot = partition(start, end);
			setSorted(pivot);
			quickSort(start, pivot - 1);
			quickSort(pivot + 1, end);
		}
		if (start == end)
			setSorted(start);
	}

	private int partition(int start, int end) {

		int pivot = elements[end].value;
		selectElement(end);

		int session = start;
		for (int i = start; i < end; i++) {
			if (elements[i].value < pivot) {
				swap(session,i);
				session++;
			}
		}
		
		unselectElement(end);
		
		swap(end,session);
		
		return session;
	}

	public ArrayElement[] mergeSort() {
		return mergeSort(0, elements.length - 1);
	}
	
	private ArrayElement[] mergeSort(int start, int end) {

		if (start == end) {
			return new ArrayElement[] { elements[start] };
		}

		int middle = start + (end - start) /  2;
		
		ArrayElement[] leftArray = mergeSort(start, middle);
		ArrayElement[] rightArray = mergeSort(middle + 1, end);

		return mergeTowArray(leftArray, rightArray, start, end);
	}

	private ArrayElement[] mergeTowArray(ArrayElement[] leftArray, ArrayElement[] rightArray, int start, int end) {
		
		ArrayElement[] mergedArray = new ArrayElement[leftArray.length + rightArray.length];

		int leftPointer = 0;
		int rightPointer = 0;
		int meregedPointer = 0;

		while (leftPointer < leftArray.length && rightPointer < rightArray.length) {
			if (leftArray[leftPointer].value < rightArray[rightPointer].value) {
				mergedArray[meregedPointer] = leftArray[leftPointer];
				leftPointer++;
			} else {
				mergedArray[meregedPointer] = rightArray[rightPointer];
				rightPointer++;
			}
			meregedPointer++;
		}

		System.arraycopy(leftArray, leftPointer, mergedArray, meregedPointer, leftArray.length - leftPointer);
		System.arraycopy(rightArray, rightPointer, mergedArray, meregedPointer, rightArray.length - rightPointer);
		for(int i=start; i<=end; i++){
			elements[i] = mergedArray[i-start];
			elements[i].setPosition(i);
			setSorted(i);
		}
		try{Thread.sleep(delay);}catch(Exception e){}
		return mergedArray;
	}
	
	public void setArray(int[] array) {
		elements = new ArrayElement[array.length];
		for (int i = 0; i < array.length; i++) {
			elements[i] = new ArrayElement(array[i], i);
		}
	}


	private void swap(int i, int j) {

		Color bgColorI = elements[i].getColor();
		Color bgColorJ = elements[j].getColor();
		
		elements[i].highlight(true);
		elements[j].highlight(true);
		repaint();
		try { Thread.sleep(delay); } catch(InterruptedException e) {}
		int temp = elements[j].value;
		elements[j].value = elements[i].value;
		elements[i].value = temp;
		repaint();
		try { Thread.sleep(delay); } catch(InterruptedException e) {}
		elements[i].setColor(bgColorI);
		elements[j].setColor(bgColorJ);
		repaint();		
	}
	
	private void brightenElement(int i) {
		elements[i].bright(true);
		repaint();
	}

	private void darkenElement(int i) {
		//try { Thread.sleep(delay); } catch(InterruptedException e) {}
		elements[i].bright(false);
		repaint();
	}
	
	private void setSorted(int i ) {
		if(i<0)
			return;
		elements[i].setSorted();
		try{Thread.sleep(delay);}catch(Exception e){}
		repaint();
	}
	
	private void selectElement(int i) {
		elements[i].setSelected(true);
		repaint();
	}
	
	private void unselectElement(int i) {
		try { Thread.sleep(delay); } catch(InterruptedException e) {}
		elements[i].setSelected(false);
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < elements.length; i++) {
			elements[i].draw(g);
		}
	}
}