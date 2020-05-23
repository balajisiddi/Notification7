package com.sectorseven.web.utils;

import static com.sectorseven.web.utils.ViewConstants.SAVE_LOCATION;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ImageUtility {

    public String getAbsoluteImagePath(CommonsMultipartFile str, long id, String folder) throws IOException {
        String controllerPath = "";
        String filePath = "";
        controllerPath = folder + "/" + id;
        filePath = SAVE_LOCATION + "/" + controllerPath + "/";
        CommonsMultipartFile imageFile = str;
        //System.out.println("Original File Name : "+imageFile.getOriginalFilename());
        File file = null;
        if (imageFile!=null && imageFile.getOriginalFilename()!=null && !imageFile.getOriginalFilename().trim().equals("")) {
            try {
                String fileName = imageFile.getOriginalFilename();
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File[] files = dir.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
                file = new File(filePath + fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                imageFile.transferTo(file);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return controllerPath;

    }
    
    public String getAbsoluteImagePath(MultipartFile str, long id, String folder) throws IOException {
        String controllerPath = "";
        String filePath = "";
        controllerPath = folder + "/" + id;
        filePath = SAVE_LOCATION + "/" + controllerPath + "/";
        MultipartFile imageFile = str;
        //System.out.println("Original File Name : "+imageFile.getOriginalFilename());
        File file = null;
        if (!imageFile.getOriginalFilename().trim().equals("")) {
            try {
                String fileName = imageFile.getOriginalFilename();
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File[] files = dir.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
                file = new File(filePath + fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                imageFile.transferTo(file);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return controllerPath;
    }
    
    
    public String Base64ToImage(String imageStr, String imageStr2,long id, String folder) throws IOException{
        String controllerPath = "";
        String filePath = "";
        controllerPath = folder + "/" + id;
        filePath = SAVE_LOCATION + "/" + controllerPath + "/";
        
        String removeDFirst =imageStr.replaceAll("\\s{2}","++");
        String removeThird=removeDFirst.replaceAll("\\s{3}","+++");
        String firstOutput =removeThird.replaceAll("\\s","+");
        String removeDsecond =imageStr2.replaceAll("\\s{2}","++");
        String removeSecondThird=removeDsecond.replaceAll("\\s{3}","+++");
        String secondOutput=removeSecondThird.replaceAll("\\s","+");
        
        byte[] mim = new byte[300000];
        mim=Base64.getMimeDecoder().decode(firstOutput+secondOutput);
        FileOutputStream fileOuputStream;
        if (!mim.equals("")) {
            try {
                String fileName = "profilePic.png";
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                fileOuputStream = new FileOutputStream(filePath + fileName);
                try {
                    fileOuputStream.write(mim);
                   } catch (IOException e) {
                    e.printStackTrace();
                  }
                try {
                    fileOuputStream.close();
                 } catch (IOException e) {
                    e.printStackTrace();
                 }
              } catch (FileNotFoundException e) {
                e.printStackTrace();
            }           
         }
        return controllerPath;
        
    }


    
    public String[] getAbsoluteImagePathWithFileName(MultipartFile multipartFile, long id, String folder) throws IOException {
        String controllerPath = "";
        String filePath = "";
        controllerPath = folder + "/" + id;
        filePath = SAVE_LOCATION + "/" + controllerPath + "/";
        MultipartFile imageFile = multipartFile;
        //System.out.println("Original File Name : "+imageFile.getOriginalFilename());
        File file = null;
        String fileName = "";
        String retArr[] = new String[2];
        if (!imageFile.getOriginalFilename().trim().equals("")) {
            try {
                fileName = imageFile.getOriginalFilename();
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File[] files = dir.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
                file = new File(filePath + fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                imageFile.transferTo(file);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        retArr[0]=controllerPath;
        retArr[1]=fileName;
        return retArr;

    }
    
    public String[] getAbsoluteImagePathAr(CommonsMultipartFile str, long id, String folder) throws IOException {
        String controllerPath = "";
        String filePath = "";
        controllerPath = folder + "/" + id;
        filePath = SAVE_LOCATION + "/" + controllerPath + "/";
        CommonsMultipartFile imageFile = str;
        //System.out.println("Original File Name : "+imageFile.getOriginalFilename());
        File file = null;
        String fileName = "";
        String retArr[] = new String[2];
        if (!imageFile.getOriginalFilename().trim().equals("")) {
            try {
                fileName = imageFile.getOriginalFilename();
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File[] files = dir.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
                file = new File(filePath + fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                imageFile.transferTo(file);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        retArr[0]=controllerPath;
        retArr[1]=fileName;
        return retArr;

    }
    
}
