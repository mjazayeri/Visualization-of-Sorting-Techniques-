

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

	private Scene scene;
	public MyFrame() {

		initUI();
	}

	public void showSort() {

		//int[] array = getRandomArray(40);
		//reversed
		//int[] array = {70,68,66,64,62,60,58,56,54,52,50,48,46,44,42,40,38,36,34,32,30,28,26,24,22,20,18,16,14,12,10};
		
		//mixed
		int[] array = {48,46,68,36,40,66,12,28,14,32,18,52,58,22,56,54,50,10,44,42,62,38,34,30,26,60,24,20,16,64,70};
		
		//semi-sorted
		//int[] array = {10,12,44,14,30,20,22,64,24,26,32,34,28,36,38,40,66,48,42,46,50,52,54,56,70,58,60,62,68};
		
		scene.setArray(array);
		scene.bubbleSort();
		
		try{Thread.sleep(2000);}catch(Exception e){}
		
		scene.setArray(array);
		scene.insertionSort();
		
		try{Thread.sleep(2000);}catch(Exception e){}
		
		scene.setArray(array);
		scene.selectionSort();
		
		try{Thread.sleep(2000);}catch(Exception e){}
		
		scene.setArray(array);
		scene.mergeSort();
		
		try{Thread.sleep(2000);}catch(Exception e){}
		
		scene.setArray(array);
		scene.quickSort(0, array.length-1);
	}
	
	private void initUI() {
		
		scene = new Scene(80);
		add(scene);

		setTitle("Points");
		setSize(1600, 1200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private int[] getRandomArray(int size) {
		int[] result = new int[size];
		Random randomizer = new Random(100);
		for (int i = 0; i < result.length; i++) {
			result[i] = randomizer.nextInt(30);
		}
		return result;
	}
	
	public static void main(String[] args) {
		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		try{
//			while(!reader.readLine().equals("start"))
//			{
//				
//			}
//		}
//		catch(Exception e){}
		MyFrame f = new MyFrame();
		f.setVisible(true);
		
		f.showSort();
	}
}
