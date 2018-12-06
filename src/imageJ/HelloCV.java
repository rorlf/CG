package imageJ;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Core;

/**
 * A simple class that demonstrates/tests the usage of the OpenCV library in
 * Java. It prints a 3x3 identity matrix and then converts a given image in gray
 * scale.
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @since 2013-10-20
 * 
 */
public class HelloCV
{
	public static void main(String[] args)
	{
		// load the OpenCV native library
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		// create and print on screen a 3x3 identity matrix
		System.out.println("Create a 3x3 identity matrix...");
		Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
		System.out.println("mat = " + mat.dump());
		
		// prepare to convert a RGB image in gray scale
		String location = "C:\\Users\\Rorlf\\workspace2\\CG\\src\\imagem\\Capturar.JPG";
		System.out.print("Convert the image at " + location + " in gray scale... ");
		// get the jpeg image from the internal resource folder
		Mat image = Imgcodecs.imread(location);
		Mat image2= Imgcodecs.imread(location); ;
		// convert the image in gray scale
		Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);
		
		Imgproc.threshold(image, image2,108, 255, Imgproc.THRESH_BINARY_INV);
		Imgproc.threshold(image, image,108, 255, Imgproc.THRESH_TOZERO);
		
//		Imgproc.cvtColor(image,image,Imgproc.COLOR_GRAY2RGB);
//		Imgproc.cvtColor(image,image,Imgproc.COLOR_RGB2HSV);
//		
//
//		Core.inRange(image, new Scalar(0,0,0), new Scalar(0,0,255), image);
	 Core.bitwise_or(image, image2, image);
//		Imgproc.threshold(image, image,20, 255, Imgproc.THRESH_BINARY_INV);
//		Imgproc.threshold(image, image,255, 255, Imgproc.THRESH_TRIANGLE);
//		Imgproc.threshold(image, image,255, 255, Imgproc.THRESH_TRUNC);
		
		// write the new image on disk
		Imgcodecs.imwrite("C:\\Users\\Rorlf\\workspace2\\CG\\src\\imagem\\new.jpg", image);
		System.out.println("Done!");
	}
}