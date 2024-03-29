package main;
public final class ImageProcessing {
	
    /**
     * Returns red component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer,  between 0 and 255
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getRed(int rgb) {
    	
    	int red = (rgb >> 16) & 0xff;
    	red = outOfLimit(red);    	
    	return red; 
    }

    /**
     * Returns green component from given packed colour.
     * @param rgb : a 32-bits RGB colour
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getGreen(int rgb) {
    	
    	rgb = rgb >> 8;
    	rgb = rgb & 0xff;
    	rgb = outOfLimit(rgb);
    	return rgb; 
    	
    }

    /**
     * Returns blue component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getGreen
     * @see #getRGB(int, int, int)
     */
        
    public static int getBlue(int rgb) {
    	
    	rgb = rgb & 0xff;
    	rgb = outOfLimit(rgb);
    	return rgb; 
    }
    
    
    /**
     * Returns an array of length 3 with Red, Green, and Blue ints as its elements.
     * @param rgb : 32-bits RGB color
     * @return an integer array of length 3 [R,G,B]
     */
    public static int[] getRGBArray(int rgb) {
    	int[] rgbArray = {getRed(rgb), getGreen(rgb), getBlue(rgb)};
    	return rgbArray;
	}

   
    /**
     * Returns the average of red, green and blue components from given packed color.
     * @param rgb : 32-bits RGB color
     * @return a double between 0 and 255
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int)
     */
    public static double getGray(int rgb) {
    	
        return (getRed(rgb) + getGreen(rgb) + getBlue(rgb))/3;
    }
    
    /**
     * If the number is smaller than 0, it is given the value 0.
     * If the number is bigger than 255, it is given the value 255.
     * @param number
     * @return a number between 0 and 255
     */
    public static int outOfLimit(int number) {
    	
    	if (number < 0) {
    		number = 0;
    	} else if (number > 255) {
    		number = 255;
    	}
    	return number;
    }

    /**
     * Returns packed RGB components from given red, green and blue components.
     * @param red : an integer 
     * @param green : an integer 
     * @param blue : an integer
     * @return a 32-bits RGB color
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     */
    public static int getRGB(int red, int green, int blue) {
    	red = outOfLimit(red);
    	green = outOfLimit(green);
    	blue = outOfLimit(blue);
    	red = red << 16;
    	green = green << 8;
    	return red + green + blue; 
    }
    
    

    /**
     * Returns packed RGB components from given gray-scale value.
     * @param gray : a double 
     * @return a 32-bits RGB color
     * @see #getGray
     */
    public static int getRGB(double gray) {
    	
    	int rgb = outOfLimit((int) gray);
    	rgb = getRGB(rgb, rgb, rgb);
    	return rgb; 
    }

    /**
     * Converts packed RGB image to gray-scale image.
     * @param image : a HxW integer array
     * @return a HxW double array
     * @see #encode
     * @see #getGray
     */
    public static double[][] toGray(int[][] image) {
    	double[][] grayscale = new double[image.length][];
    	
    	for (int i = 0; i < image.length; i++) {
    		grayscale[i] = new double[image[i].length];
    		
    		for (int j = 0; j < image[i].length; j++) {
    			grayscale[i][j] = getGray(image[i][j]);
    		}
    	}
    	
    	return grayscale;
    }

    /**
     * Converts gray-scale image to packed RGB image.
     * @param channels : a HxW double array
     * @return a HxW integer array
     * @see #decode
     * @see #getRGB(double)
     */
    public static int[][] toRGB(double[][] gray) {
    	
    	int [][] RGB = new int[gray.length][gray[0].length];
    	for (int i = 0; i < gray.length; i++) {
    		for (int j = 0; j < gray[i].length; j++) {
    			RGB[i][j] = getRGB(gray[i][j]);
    		}
    	}
    	return RGB;
    }

    
    /**
     * Convert an arbitrary 2D double matrix into a 2D integer matrix 
     * which can be used as RGB image
     * @param matrix : the arbitrary 2D double array to convert into integer
     * @param min : a double, the minimum value the matrix could theoretically contains
     * @param max : a double, the maximum value the matrix could theoretically contains
     * @return an 2D integer array, containing a RGB mapping of the matrix 
     */
    public static int[][] matrixToRGBImage(double[][] matrix, double min, double max) {
    	assert matrix.length >= 1 && matrix[0].length >= 1;
    	
    	int[][] rgb = new int[matrix.length][matrix[0].length];
    	
    	for (int i = 0; i < rgb.length; i++) {
    		for (int j = 0; j < rgb[i].length; j++) {
    			double g = (matrix[i][j] - min)/(max - min) * 255;	
    			rgb[i][j] = getRGB(g);
    		}
    	}
    	
    	return rgb;
    }
}
