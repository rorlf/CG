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
		
		
		MyFrame frame = new MyFrame();
		frame.setVisible(true);
		
		ImagePlus imagePlus = new ImagePlus("C:\\Users\\Rorlf\\eclipse-workspace\\cg-fx\\CG-FX\\src\\imagem\\Capturar.JPG");
		
		Image image = imagePlus.getImage();
		
		ColorProcessor cprocessador = new ColorProcessor(image);
		
		ByteProcessor processador = new ByteProcessor(cprocessador.convertToByte(true).createImage());
		
//		processador.smooth();
//        
        processador.setThreshold(109, 255,3 );

		cprocessador.setAutoThreshold(AutoThresholder.Method.Yen, true,1);
		
//		processador.setBackgroundValue(0);
//		processador.autoThreshold();
		
		
		
        
        
        
        BufferedImage bimage = (BufferedImage)processador.createImage();
        
        frame.render(bimage);
 
	}
}
