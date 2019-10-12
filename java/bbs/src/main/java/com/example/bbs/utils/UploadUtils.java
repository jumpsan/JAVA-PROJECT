package com.example.bbs.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class UploadUtils {
    public static boolean uploadFile(MultipartFile multipartFile,Integer type,String newName) {
        boolean result=false;
        if (!multipartFile.isEmpty() && type!=null && newName!=null) {    //(1)判断是否为空
            try {
                String realPath="";
                if(type==0){
                    realPath = "C:\\java\\images\\";  //(2) 存放路径
                }else if(type==1){
                    realPath = "C:\\java\\videos\\";  //(2) 存放路径
                }else{
                    realPath ="C:\\java\\head\\";
                }
                File file = new File(realPath + newName);  //(5)
                multipartFile.transferTo(file);  //(6)将文件存储到指定路径
                result=true;
            } catch (Exception e) {
                e.printStackTrace();
                result=false;
            }
            finally {
                return result;  //(7)返回新名字
            }
        }
        return result;
    }

    public static String getNewName(MultipartFile multipartFile){
        String newName = null;
        if (!multipartFile.isEmpty()) {    //(1)判断是否为空
            String originalName = multipartFile.getOriginalFilename();  //(3)得到原来的名字
            String uuidName = UUID.randomUUID().toString();  //(4)获得UUID，避免重复
            newName = uuidName + originalName.substring(originalName.lastIndexOf("."));  //(4)得到后缀名
            return newName;  //(7)返回新名字
        }
        return newName;
    }

    public static boolean deleteFile(String fileName,Integer type){
        String realPath="";
        if(type==0){
            realPath = "C:\\java\\images\\";  //(2) 存放路径
        }else if(type==1){
            realPath = "C:\\java\\videos\\";  //(2) 存放路径
        }else{
            realPath ="C:\\java\\head\\";
        }
        File file = new File(realPath+fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                //System.out.println("删除文件" + fileName + "成功！");
                return true;
            } else {
                //System.out.println("删除文件" + fileName + "失败！");
                return false;
            }
        } else {
            //System.out.println("删除文件失败：" + fileName + "不存在！");
            return false;
        }
    }

}
