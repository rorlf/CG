package imageJ;



import ij.ImagePlus;
import ij.process.AutoThresholder;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class MainDesktopCVIJ {
	
	static {
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	}

	public static void main(String[] args) throws IOException {
		
		String location = "C:\\Users\\Rorlf\\workspace2\\CG\\src\\imagem\\Capturar.JPG";
		ImagePlus imagePlus = new ImagePlus(location);
		
		Image image = imagePlus.getImage();
		MyFrame frame = new MyFrame();
		
		frame.setVisible(true);
		
		Mat processada = new Mat();
		
		

		ColorProcessor cprocessador = new ColorProcessor(image);
		
		ByteProcessor processador = new ByteProcessor(cprocessador.convertToByte(true).createImage());

        processador.setThreshold(109, 255,2 );
		
		processador.setAutoThreshold(AutoThresholder.Method.Yen, true,1);
					
		
		
		BufferedImage bimage = (BufferedImage)processador.createImage();
		
		Mat process = bufferedImage2Mat_v2(bimage);		
		
		Mat image2= Imgcodecs.imread(location);
		
		Imgproc.threshold(image2, image2,109, 255, Imgproc.THRESH_TOZERO);
		
		
		 Core.bitwise_or(process, image2, processada);
		
        
        frame.render(processada);
 
	}
	
	  public static Mat bufferedImage2Mat_v2(BufferedImage im) {

	        im = toBufferedImageOfType(im, BufferedImage.TYPE_3BYTE_BGR);

	        // Convert INT to BYTE
	        //im = new BufferedImage(im.getWidth(), im.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
	        // Convert bufferedimage to byte array
	        byte[] pixels = ((DataBufferByte) im.getRaster().getDataBuffer()).getData();

	        // Create a Matrix the same size of image
	        Mat image = new Mat(im.getHeight(), im.getWidth(), CvType.CV_8UC3);
	        // Fill Matrix with image values
	        image.put(0, 0, pixels);

	        return image;

	    }
	  private static BufferedImage toBufferedImageOfType(BufferedImage original, int type) {
	        if (original == null) {
	            throw new IllegalArgumentException("original == null");
	        }

	        // Don't convert if it already has correct type
	        if (original.getType() == type) {
	            return original;
	        }

	        // Create a buffered image
	        BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), type);

	        // Draw the image onto the new buffer
	        Graphics2D g = image.createGraphics();
	        try {
	            g.setComposite(AlphaComposite.Src);
	            g.drawImage(original, 0, 0, null);
	        }
	        finally {
	            g.dispose();
	        }

	        return image;
	    }
}


