package main;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//must change path
		int[][] pattern = Helper.read("images/charlie.png");
		int[][] array = Helper.read("images/charlie_beach.png");
		double[][] distance = DistanceBasedSearch.distanceMatrix(pattern, array);
		int[] coords = Collector.findBest(distance, true);
		System.out.println(coords[0]);
		System.out.println(coords[1]);
		Helper.drawBox(coords[0], coords[1], pattern[0].length, pattern.length, array);
		Helper.show(array, "image trouv�e");
	}

}