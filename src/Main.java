import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

public class Main {
	private final static String OUTPUT_DIR = "";
	private static DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
	private static Date date = new Date();
	private static float screenWidth = 1920;
	private static float screenHeight = 1080;

	private static float aimedWidth = 9933;
	private static float aimedHeight = 7016;
	private static RenderingHints renderingHints = null;
	
	
	
	
	private static int dpi = 300;
	
	
	
	private static float scale  = dpi / 72f;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	

	
	
	
	
	
	

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	   
	 
	 

	    
	    
	    
	    
	 private static void convert() throws Exception {
         
	        //  load a pdf from a file
	        File file = new File("blueprint.pdf");
	        RandomAccessFile raf = new RandomAccessFile(file, "r");
	        ReadableByteChannel ch = Channels.newChannel(new FileInputStream(file));
	 
	        FileChannel channel = raf.getChannel();
	        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,
	                0, channel.size());
	        PDFFile pdffile = new PDFFile(buf);
	 
	        //   get number of pages
	        int jumlahhalaman = pdffile.getNumPages();
	 
	        //  iterate through the number of pages
	        for (int i = 1; i <= jumlahhalaman; i++) {
	            PDFPage page = pdffile.getPage(i);
	 
	            //  create new image
	            Rectangle rect = new Rectangle(0, 0,(int)aimedWidth, (int)aimedHeight);
	 
	            Image img = page.getImage(
	                    rect.width, rect.height, //width & height
	                    rect, // clip rect
	                    null, // null for the ImageObserver
	                    true, // fill background with white
	                    true // block until drawing is done
	                    );
	 
	            
	           
	            
	            BufferedImage bufferedImage = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
	            Graphics g = bufferedImage.createGraphics();
	            g.drawImage(img, 0, 0, null);
	            g.dispose();
	 
	       
	            ImageIO.write(bufferedImage, "png", new File(OUTPUT_DIR + file.getName() + "_" + dateFormat.format(date) + ".png"));
	        }
	    }
	
	
	
	public static void test1() {
		File file = new File("blueprint.pdf");
		RandomAccessFile raf;
		System.out.println("Started!");
		try {
			raf = new RandomAccessFile(file, "r");
			FileChannel channel = raf.getChannel();
			ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			PDFFile pdffile = new PDFFile(buf);
			// draw the first page to an image

			// Get image
			PDFPage page = pdffile.getPage(0,true);
			// get the width and height for the doc at the default zoom
			
			
//			
//			int width = (int) page.getBBox().getWidth();
//			int height = (int) page.getBBox().getHeight();
//			
//			
			
			 
			 
			 
//			 System.out.println("Width: "  + width + " Scale: " + scale + " final: " + (int) (width *scale));
			 
			 
			 
			int rotation = page.getRotation();
			
			
			
			Rectangle backgroundRect = new Rectangle(0, 0, (int)aimedWidth, (int)aimedHeight);
			
			
			
			
			Rectangle imageRect = backgroundRect;
//			if (rotation == 90 || rotation == 270) {
//				System.out.println("True)");
//				imageRect = new Rectangle(0, 0, backgroundRect.height, backgroundRect.width);
//			}
			
			
			// generate the image
			BufferedImage img = (BufferedImage) page.getImage(backgroundRect.width, backgroundRect.height, // width & height
					imageRect, // clip rect
					null, // null for the ImageObserver
					true, // fill background with white
					true // block until drawing is done
			);

			ImageIO.write(img, "png", new File(OUTPUT_DIR + file.getName() + "_" + dateFormat.format(date) + ".png"));
			
			System.out.println("Saved!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
