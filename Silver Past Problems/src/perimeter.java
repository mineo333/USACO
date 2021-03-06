import java.util.*;
import java.io.*;

public class perimeter {

	static char[][] grid;
	static int totalPerimeter = 0;
	static PrintStream fileWriter;

	public static void main(String[] args) throws FileNotFoundException {

		Scanner fileReader = new Scanner(new File("perimeter.in.txt"));
		fileWriter = new PrintStream(new File("perimeter.out.txt"));

		// input
		int numBayhales = fileReader.nextInt();
		int[][] coord = new int[numBayhales][2];
		int largest = 0;
		for (int i = 0; i < numBayhales; i++) {
			coord[i][0] = fileReader.nextInt();
			if (coord[i][0] > largest) {
				largest = coord[i][0];
			}
			coord[i][1] = fileReader.nextInt();
			if (coord[i][1] > largest) {
				largest = coord[i][1];
			}
		}
		grid = new char[largest + 2][largest + 2];
		// mark haybales with "*" on the grid
		for (int i = 0; i < numBayhales; i++) {
			grid[coord[i][0]][coord[i][1]] = '*';
		}
		floodfill(0, 0, largest);
		perimeter(largest, grid);		

	}

	public static void perimeter(int largest, char[][] grid) {
		for (int x = 0; x < largest + 2; x++) {
			for (int y = 0; y < largest + 2; y++) {
				if (grid[x][y] == '.') {
					// check left
					if (x > 0) {
						if (grid[x - 1][y] == '*') {
							totalPerimeter++;
						}
					}
					// check right
					if (x < largest + 1) {
						if (grid[x + 1][y] == '*') {
							totalPerimeter++;
						}
					}
					// check up
					if (y > 0) {
						if (grid[x][y - 1] == '*') {
							totalPerimeter++;
						}
					}
					// check down
					if (y < largest + 1) {
						if (grid[x][y + 1] == '*') {
							totalPerimeter++;
						}
					}
				}
			}
		}
		fileWriter.println(totalPerimeter);

	}

	public static void floodfill(int x, int y, int largest) {
		// stop flood-filling if you're out of bounds
		if (x < 0 || y < 0 || x > largest + 1 || y > largest + 1) {
			return;
		} else {
			if (grid[x][y] != '*' && grid[x][y] != '.') {
				grid[x][y] = '.';
				floodfill(x + 1, y, largest);
				floodfill(x - 1, y, largest);
				floodfill(x, y + 1, largest);
				floodfill(x, y - 1, largest);
			}
		}
	}

}
