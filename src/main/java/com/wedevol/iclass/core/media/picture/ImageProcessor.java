package com.wedevol.iclass.core.media.picture;

import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import com.wedevol.iclass.core.amazon.MediaFile;

/**
 * Image Processor: resize picture, convert to jpeg
 * 
 * @author charz
 *
 */
@Component
public class ImageProcessor {

	public MediaFile resize(InputStream inputStream, String fileName, int maxWidth) throws IOException {
		ByteArrayOutputStreamDirectAccess outputStream = convertFileToJpeg(inputStream, maxWidth);
		InputStream inputResized = new ByteArrayInputStream(outputStream.getInternalBuffer());
		// Remove file extension from incoming URL
		String imgURLWithoutExt = FilenameUtils.removeExtension(fileName);
		String generatedImgURL = imgURLWithoutExt + "_" + maxWidth + ".jpeg";
		return new MediaFile(generatedImgURL, "jpg", Long.valueOf(outputStream.size()), inputResized);
	}

	private ByteArrayOutputStreamDirectAccess convertFileToJpeg(InputStream inputStream, int maxWidth)
			throws IOException {
		CountableInputStream inputForImage = new CountableInputStream(inputStream);
		ImageIO.setUseCache(false);
		BufferedImage bufferedImage = ImageIO.read(inputForImage);
		// Create an output stream with an initial size equals to the original image
		ByteArrayOutputStreamDirectAccess outputStream = new ByteArrayOutputStreamDirectAccess(
				inputForImage.getStreamLength());
		// Get the max height given the target width and the aspect ratio of the original image
		int maxHeight = (int) ((float) maxWidth
				/ ((float) bufferedImage.getWidth() / (float) bufferedImage.getHeight()));
		// Calculate dimensions maintaining the aspect ratio
		Dimension imgSizeDim = new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight());
		Dimension boundaryDim = new Dimension(maxWidth, maxHeight);
		Dimension scaledDim = PictureQualityResizer.getScaledDimension(imgSizeDim, boundaryDim);
		BufferedImage scaledImage = PictureQualityResizer.getScaledInstance(bufferedImage, scaledDim,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		PictureQualityResizer.writeJPG(scaledImage, outputStream, 0.9f);
		return outputStream;
	}

}
