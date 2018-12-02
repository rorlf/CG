package it.polito.teaching.cv;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import it.polito.elite.teaching.cv.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class CG_Controller
{
	// images to show in the view
	@FXML
	private ImageView originalImage;
	@FXML
	private ImageView transformedImage;
	
	@FXML
	private Button transformButton;

	
	// the main stage
	private Stage stage;
	// the JavaFX file chooser
	private FileChooser fileChooser;
	// support variables
	private Mat image;
	private Mat image2;
	private List<Mat> planes;
	// the final complex image
	private Mat complexImage;
	
	/**
	 * Init the needed variables
	 */
	protected void init()
	{
		this.fileChooser = new FileChooser();
		this.image = new Mat();
		this.planes = new ArrayList<>();
		this.complexImage = new Mat();
	}
	
	/**
	 * Load an image from disk
	 */
	@FXML
	protected void loadImage()
	{
		// show the open dialog window
		File file = this.fileChooser.showOpenDialog(this.stage);
		if (file != null)
		{
			this.transformedImage.setImage(null);
			// read the image in gray scale
			this.image = Imgcodecs.imread(file.getAbsolutePath());
			this.image2 = Imgcodecs.imread(file.getAbsolutePath());
			// show the image
			this.updateImageView(originalImage, Utils.mat2Image(this.image));
			// set a fixed width
			this.originalImage.setFitWidth(250);
			// preserve image ratio
			this.originalImage.setPreserveRatio(true);
			// update the UI
			this.transformButton.setDisable(false);
			
			
			if (!this.planes.isEmpty())
			{
				this.planes.clear();
				this.transformedImage.setImage(null);
				
			}
			
		}
	}
	

	@FXML
	protected void transformImage()
	{
		
		
		// convert the image in gray scale
		Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);
		
		Imgproc.threshold(image, image2,109, 255, Imgproc.THRESH_BINARY_INV);
		Imgproc.threshold(image, image,109, 255, Imgproc.THRESH_TOZERO);
		

	 Core.bitwise_or(image, image, image);
		
		
	
		
		// optimize the image resulting from the dft operation
		
		
		// show the result of the transformation as an image
	 this.updateImageView(transformedImage, Utils.mat2Image(this.image));
		// set a fixed width
		this.transformedImage.setFitWidth(250);
		// preserve image ratio
		this.transformedImage.setPreserveRatio(true);		
		
		// disable the button for applying the dft
		this.transformButton.setDisable(true);
	}
	

	
	
	private void updateImageView(ImageView view, Image image)
	{
		Utils.onFXThread(view.imageProperty(), image);
	}
	
}
