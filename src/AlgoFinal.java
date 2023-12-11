import java.util.Random;

public class AlgoFinal {
	
	static int[] createUniqueRandomNumbers(int start, int end) {
		int n = end-start +1; //number of integer need to generate
		
		// Create an array to store in [start, end]
		int stored[] = new int[n];
		for(int i = 0; i< n; i++) {
			stored[i] = i;
		}
		
		// an array to store the result
		int result[] = new int[n];
		
		int x = n;
		Random random = new Random();
		for(int i = 0; i<n; i++) {
			// k is a random index from [0,x]
			int randomIndex = random.nextInt(x);
			
			result[i] = stored[randomIndex];
			
			// we got value from a[k]. We replace its value by the value from last index
			// so we do not get that value (a[k]) anymore
			stored[randomIndex] = stored[x-1];
			
			// then we decrease x by 1 to get a random index from 0 to x only
			x--;
		}
		
		return result;
	}
	
	// Bubble sort is not very good for very large sets
	// Complexity is Big O(n^2)
	public static int[] bubbleSort() {
		int result[] = createUniqueRandomNumbers(0, 10000);
		System.out.print("Bubble Before: ");
		// int operations = 0;
		/*
		for(int i = 0; i< result.length; i++) {
			System.out.print(result[i] + " ");
		}
		*/
		
		// Setting swapped to true so we can get to the while loop
		boolean swapped = true;
		
		// -1 because when we are looking at the 2nd to last element, we will be comparing it to the last element. Nothing after last element
		while(swapped) {
			// If you didn't have to swap anything, you know the list is in perfect order
			// If you did have to swap something, you will have to go through it again
			swapped = false;
			for(int i = 0; i< result.length - 1; i++) {
				// If current index is greater than the next index, swap the values
				if(result[i] > result[i+1]) {
					swapped = true;
					int temp = result[i];
					result[i] = result[i+1];
					result[i+1] = temp;
					bubbleOperations++;
				}
			}
		}
		
		return result;
	}
	static int bubbleOperations = 0;
	
	// Complexity of Big O(n log n)
	// Faster than bubble sort
	// Divide and Conquer algorithm
	public static void mergeSort(int[] inputArray) {
		int length = inputArray.length;
		
		// Part of the algorithm where numbers are by themselves in an array
		if(length < 2) {
			return;
		}
		
		// Getting the midpoint of the input array
		int middleIndex = length / 2;
		// Creating arrays of size of the midpoint number
		int[] leftArray = new int[middleIndex];
		int[] rightArray = new int[length - middleIndex];
		
		// Filling up the left half
		for(int i = 0; i < middleIndex; i++) {
			leftArray[i] = inputArray[i];
		}
		// Filling up the right half
		for(int i = middleIndex; i < length; i++) {
			rightArray[i - middleIndex] = inputArray[i];
		}
		
		// Need to merge sort each half of our array
		mergeSort(leftArray);
		mergeSort(rightArray);
		
		// Need to merge those arrays together
		merge(inputArray, leftArray, rightArray);
		
	}
	
	static int mergeOperations = 0;
	
	private static void merge(int[] inputArray, int[] leftArray, int[] rightArray) {
		int leftSize = leftArray.length;
		int rightSize = rightArray.length;
		int operations = 0;
		
		// i is iterator for left half, j is iterator for right half, k is iterator for merged array
		int i = 0;
		int j = 0;
		int k = 0;
		
		// Until we run out of elements in the left array or we run out of elements in the right array
		while(i < leftSize && j < rightSize) {
			if(leftArray[i] <= rightArray[j]) {
				inputArray[k] = leftArray[i];
				i++;
				operations++;
			}
			else {
				inputArray[k] = rightArray[j];
				j++;
				operations++;
			}
			k++;
		}
		
		// Cleanup adding all the elements that are still remaining in the array that didn't run out
		while(i < leftSize) {
			inputArray[k] = leftArray[i];
			i++;
			k++;
		}
		while(j < rightSize) {
			inputArray[k] = rightArray[j];
			j++;
			k++;
		}
		mergeOperations = mergeOperations + operations;
		
	}

	public static void main(String[] args) {
		
		int bubbleSort[] = bubbleSort();
		System.out.print("\nBubble After: ");
		/*
		for(int i = 0; i<bubbleSort.length; i++) {
			System.out.print(bubbleSort[i] + " ");
		}
		*/
		System.out.print("\nBubble Sort Operations: " +bubbleOperations);
		
		int randomNumbers[] = createUniqueRandomNumbers(0, 10000);
		System.out.print("\nMerge Before: ");
		/*
		for(int i = 0; i<randomNumbers.length; i++) {
			System.out.print(randomNumbers[i] + " ");
		}
		*/
		mergeSort(randomNumbers);
		/*
		System.out.print("\nMerge After:");
		for(int i = 0; i<randomNumbers.length; i++) {
			System.out.print(randomNumbers[i] + " ");
		}
		*/
		System.out.print("\nMerge Sort Operations: "+mergeOperations);

	}

}
