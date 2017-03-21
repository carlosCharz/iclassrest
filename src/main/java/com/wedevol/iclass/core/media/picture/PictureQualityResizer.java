package com.wedevol.iclass.core.media.picture;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;

public class PictureQualityResizer {

	public static Dimension getScaledDimension(Dimension imgSize, Dimension targetBoundary) {

	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int bound_width = targetBoundary.width;
	    int bound_height = targetBoundary.height;
	    int new_width = original_width;
	    int new_height = original_height;

	    // first check if we need to scale width
	    if (original_width > bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (int)( (float)(new_width * original_height) / (float)original_width );
	    }

	    // then check if we need to scale even with the new height
	    if (new_height > bound_height) {
	        //scale height to fit instead
	        new_height = bound_height;
	        //scale width to maintain aspect ratio
	        new_width = (int)( (float)(new_height * original_width) / (float)original_height );
	    }

	    return new Dimension(new_width, new_height);
	}
	
	public static BufferedImage getScaledInstance(BufferedImage img, Dimension targetDim, Object hint,
			boolean higherQuality)
	{
		return getScaledInstance(img, targetDim.width, targetDim.height, hint, higherQuality);
	}
	
	public static BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint,
			boolean higherQuality)
	{
//		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
//				: BufferedImage.TYPE_INT_ARGB;
		int type = BufferedImage.TYPE_INT_RGB; // force RGB type since we are always using to JPEG conversion
		
		BufferedImage ret = (BufferedImage) img;
		
		int w, h;
		if (higherQuality) {
			// Use multi-step technique: start with original size, then
			// scale down in multiple passes with drawImage()
			// until the target size is reached
			w = img.getWidth();
			h = img.getHeight();
		} else {
			// Use one-step technique: scale directly from original
			// size to target size with a single drawImage() call
			w = targetWidth;
			h = targetHeight;
		}

		do {
			if (higherQuality && w > targetWidth) {
				w /= 2;
				if (w < targetWidth) {
					w = targetWidth;
				}
			}

			if (higherQuality && h > targetHeight) {
				h /= 2;
				if (h < targetHeight) {
					h = targetHeight;
				}
			}

			BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g2 = tmp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
			g2.drawImage(ret, 0, 0, w, h, null);
			g2.dispose();

			ret = tmp;
		} while (w != targetWidth || h != targetHeight);

		return ret;
	}

	public static void writeJPG(BufferedImage bufferedImage, OutputStream outputStream, float quality)
			throws IOException {
		Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName("jpg");
		ImageWriter imageWriter = iterator.next();
		ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
		imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		imageWriteParam.setCompressionQuality(quality);
		ImageOutputStream imageOutputStream = new MemoryCacheImageOutputStream(outputStream);
		imageWriter.setOutput(imageOutputStream);
		IIOImage iioimage = new IIOImage(bufferedImage, null, null);
		imageWriter.write(null, iioimage, imageWriteParam);
		imageOutputStream.flush();
	}
}
