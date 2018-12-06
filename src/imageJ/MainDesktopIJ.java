package imageJ;

import ij.ImagePlus;
import ij.process.AutoThresholder;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;



import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainDesktopIJ {

	public static void main(String[] args) throws IOException {
		
		String location = "C:\\Users\\Rorlf\\workspace2\\CG\\src\\imagem\\Capturar.JPG";
		MyFrame frame = new MyFrame();
		frame.setVisible(true);
		
		ImagePlus imagePlus = new ImagePlus(location);
		
		Image image = imagePlus.getImage();
		
		ColorProcessor cprocessador = new ColorProcessor(image);
		
		
		ByteProcessor processador = new ByteProcessor(cprocessador.convertToByte(true).createImage());
		
//		processador.smooth();
//        
		
     
		processador.setThreshold(109, 255, 3);
		
        processador.setMinAndMax(95, 255);
        processador.invertLut();
        
        
        
        
		
//		processador.setAutoThreshold(AutoThresholder.Method.Yen, true,1);
		
//		processador.setBackgroundValue(0);
//		processador.autoThreshold();
		
		
		
        
        
        
        BufferedImage bimage = (BufferedImage)processador.createImage();
        
	

        
        frame.render(bimage);
 
	}
}
