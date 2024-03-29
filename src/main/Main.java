package main;


/**
 * 
 * @author Leandro Graziano, Luca Bataillard
 *
 *	Where is Charlie Project 
 *
 */
public final class Main {
	
    public static void main(String[] args) {
//    	testGetRed();
//    	testGrayscale();
//    	testFindNBest();
    	testDistanceBasedSearch();
    	testSimilarityBasedSearch();   
    	compareDistanceAndSimilarity();
    	findCharlie();
    }
    
    /*
     * Tests for Class ImageProcessing
     */
    public static void testGetRed() { 
    	int color = 0b11110000_00001111_01010101;
    	int ref = 0b11110000;
    	int red = ImageProcessing.getRed(color);
    	if (red == ref) {
    		System.out.println("Test passed");
    	} else {
    		System.out.println("Test failed. Returned value = " + red + " Expected value = " + ref);
    	}
    }
    
    public static void testGrayscale() {
    	System.out.println("Test Grayscale");
     	int[][] image = Helper.read("images/food.png");
    	double[][] gray = ImageProcessing.toGray(image);
    	Helper.show(ImageProcessing.toRGB(gray), "test bw");
    }    
        
    /*
     * Tests for Class Collector
     */
    
    public static void testFindNBest() {
    	System.out.println("Test findNBest");
    	double[][] t = new double[][] {{20, 30, 10, 50, 32}, {28, 39, 51, 78, 91}};
    	int[][] coords = Collector.findNBest(10, t, true);    			
    	for (int[] a : coords) {
    		int r = a[0];
    		int c = a[1];
    		System.out.println("Row=" + r + " Col=" + c + " Val=" + t[r][c]);
    	}    
    }

    /*
     * Tests for Class DistanceBasedSearch
     */
    
    public static void testDistanceBasedSearch() {
    	System.out.println("Test DistanceBasedSearch");
    	int[][] food = Helper.read("images/food.png");
    	int[][] onions = Helper.read("images/onions.png");
    	double[][] distance = DistanceBasedSearch.distanceMatrix(onions, food); 			
    	int[] p = Collector.findBest(distance, true);
    	Helper.drawBox(p[0], p[1], onions[0].length, onions.length, food);
    	Helper.show(food, "Found!");
    }
    
    /*
     * Tests for Class SimilarityBasedSearch
     */

    public static void testSimilarityBasedSearch() {
    	System.out.println("Test SimilarityBasedSearch");
		int[][] food = Helper.read("images/charlie_beach.png");
    	int[][] onions = Helper.read("images/charlie.png");
    	double[][] foodGray = ImageProcessing.toGray(food);
    	double[][] onionsGray = ImageProcessing.toGray(onions);    	
    	double[][] similarity = SimilarityBasedSearch.similarityMatrix(onionsGray, foodGray);
    	int[][] best = Collector.findNBest(8, similarity, false);    			
    	for (int[] a : best) {
    		int r = a[0];
    		int c = a[1];
        	Helper.drawBox(r, c, onions[0].length, onions.length, food);
    	}
    	Helper.show(food, "Found again!");    	
    }
    
    public static void compareDistanceAndSimilarity() {
		System.out.println("Compare Distance and Similarity based search");
		int[][] image = Helper.read("images/image-dark.png");
		int[][] pattern = Helper.read("images/pattern.png");
		double[][] imageGray = ImageProcessing.toGray(image);
		double[][] patternGray = ImageProcessing.toGray(pattern);
		
		double[][] distance = DistanceBasedSearch.distanceMatrix(pattern, image);
		double[][] similarity = SimilarityBasedSearch.similarityMatrix(patternGray, imageGray);
		
		int[] bestDist = Collector.findBest(distance, true);
		int[] bestSim = Collector.findBest(similarity, false);

		Helper.drawBox(bestDist[0], bestDist[1], pattern[0].length, pattern.length, image);    	
    	Helper.drawBox(bestSim[0], bestSim[1], pattern[0].length, pattern.length, image, pattern[0].length/15, 0x00ff00);;
    	Helper.show(image, "Compare Dist (red) and Sim (green)");
    }
    
    public static void findCharlie() {
    	System.out.println("Find Charlie");
		int[][] beach = Helper.read("images/charlie_beach.png");
    	int[][] charlie = Helper.read("images/charlie.png");
    	double[][] beachGray = ImageProcessing.toGray(beach);
    	double[][] charlieGray = ImageProcessing.toGray(charlie);    	

    	System.out.println("Compute Similarity Matrix: expected time about 2 min");
    	double[][] similarity = SimilarityBasedSearch.similarityMatrix(charlieGray, beachGray);

    	System.out.println("Find Best");
    	int[] best = Collector.findBest(similarity, false);   
    	double max = similarity[best[0]][best[1]];
    	
    	Helper.show(ImageProcessing.matrixToRGBImage(similarity, -1, max), "Similarity");
    	
    	Helper.drawBox(best[0], best[1], charlie[0].length, charlie.length, beach);
    	System.out.println("drawBox at (" + best[0] + "," + best[1] + ")");
    	Helper.show(beach, "Found again!");    	
    }
}
