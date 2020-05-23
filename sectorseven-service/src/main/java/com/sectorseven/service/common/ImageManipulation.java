/**
 * The MIT License
 *
 * Copyright (c) 2010-2012 www.myjeeva.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE. 
 * 
 */
package com.sectorseven.service.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;


public class ImageManipulation {

	/**
	 * @param args
	 */
	public static String getBase64Image(String path) {
		File file = new File(path);
		String imageDataString = null;
		try {
			/*
			 * Reading a Image file from file systemcore.
			 */
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int)file.length()];
			imageInFile.read(imageData);
			
			imageDataString = new String(Base64.encodeBase64(imageData), "UTF-8");
			//imageDataString = Base64.encodeBase64(imageData).toString();
			
			/*
			 * Converting Image byte array into Base64 String 
			 */
		     //imageDataString = encodeImage(imageData);
			
			/*
			 * Converting a Base64 String into Image byte array 
			 */
			byte[] imageByteArray = decodeImage(imageDataString);
			
			/*
			 * Write a image byte array into file system  
			 */
			FileOutputStream imageOutFile = new FileOutputStream(file);
			imageOutFile.write(imageByteArray);
			
			imageInFile.close();
			imageOutFile.close();
			
		} catch (FileNotFoundException e) {
		} catch (IOException ioe) {
		}
		return imageDataString;

	}
	
	/**
	 * Encodes the byte array into base64 string
	 * @param imageByteArray - byte array
	 * @return String a {@link java.lang.String}
	 */
	public static String encodeImage(byte[] imageByteArray){		
		return Base64.encodeBase64URLSafeString(imageByteArray);		
	}
	
	/**
	 * Decodes the base64 string into byte array
	 * @param imageDataString - a {@link java.lang.String} 
	 * @return byte array
	 */
	public static byte[] decodeImage(String imageDataString) {		
		return Base64.decodeBase64(imageDataString);
	}

}
