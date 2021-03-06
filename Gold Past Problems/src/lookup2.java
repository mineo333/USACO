import java.util.*;
import java.io.*;

public class lookup2 {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner fileReader = new Scanner(new File("lookup.in"));
		PrintStream fileWriter = new PrintStream(new File("lookup.out"));
		int numCows = fileReader.nextInt();
		int[] cowHeights = new int[numCows];
		int[] answers = new int[numCows];
		for (int i = 0; i < numCows; i++) {
			cowHeights[i] = fileReader.nextInt();
		}
		Stack<Integer> unmatchedIndex = new Stack();
		unmatchedIndex.push(0);
		for (int i = 1; i < numCows; i++) {
			if (!unmatchedIndex.isEmpty()) {
				while (cowHeights[unmatchedIndex.peek()] < cowHeights[i]) {
					answers[unmatchedIndex.peek()] = i + 1;
					unmatchedIndex.pop();
					if (unmatchedIndex.isEmpty()) break;
				}
			}
			unmatchedIndex.push(i);
		}
		for (int i = 0; i < numCows; i++) {
			fileWriter.println(answers[i]);
		}
	}

}
