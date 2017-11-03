package main;

public class SimilarityBasedSearch {

	/**
	 * Computes the mean value of a gray-scale image given as a 2D array 
	 * @param image : a 2D double array, the gray-scale Image
	 * @return a double value between 0 and 255 which is the mean value
	 */
	public static double mean(double[][] image) {
		double mean = 0;
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				mean = mean + image[i][j];
			}
		}
		mean = mean/(image.length*image[0].length);
		return mean; 
	}
	

	/**
	 * Computes the mean value of a gray-scale pattern in an image
	 * @param matrix : a 2D double array, the gray-scale Image
	 * @param row : the y coordinate of the upper left corner of the pattern
	 * @param col : the x coordinate of the upper left corner of the pattern
	 * @param width : the width of the pattern
	 * @param height : the height of the pattern
	 * @return a double value between 0 and 255 which is the mean value
	 */
	static double windowMean(double[][] image, int row, int col, int width, int height) {
		double mean = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				mean = mean + image[i + col][j + row];
			}
		}
		mean = mean/(width*height);
		return mean;
	}
	
	
	/**
	 * Computes the Normalized Cross Correlation of a gray-scale pattern if positioned
	 * at the provided row, column-coordinate in a gray-scale image
	 * @param row : a integer, the row-coordinate of the upper left corner of the pattern in the image.
	 * @param column : a integer, the column-coordinate of the upper left corner of the pattern in the image.
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of double, the gray-scale image where to look for the pattern
	 * @return a double, the Normalized Cross Correlation value at position (row, col) between the pattern and the part of
	 * the base image that is covered by the pattern, if the pattern is shifted by x and y.
	 * should return -1 if the denominator is 0
	 */
	public static double normalizedCrossCorrelation(int row, int col, double[][] pattern, double[][] image) {
		
		// TODO implement me !
		return -2; 
	}

	
	/**
	 * Compute the similarityMatrix between a gray-scale image and a gray-scale pattern
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of doubles, the gray-scale image where to look for the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original gray-scale image, 
	 * the similarity (normalized cross-correlation) between the image's window and the pattern
	 * placed over this pixel (upper-left corner)
	 */
	public static double[][] similarityMatrix(double[][] pattern, double[][] image) {
		
		// TODO implement me !
		return new double[][]{}; 
	}

}
