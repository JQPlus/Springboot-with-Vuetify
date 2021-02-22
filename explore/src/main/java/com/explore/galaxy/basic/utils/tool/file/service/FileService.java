package com.explore.galaxy.basic.utils.tool.file.service;

import com.explore.galaxy.basic.utils.uuid.UUIDUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    @Value("${filePath.OS.windows}")
    String configWindowsFilePath;

    @Value("${filePath.OS.linux}")
    String configLinuxFilePath;

    @Value("${fileTemplatePath.OS.linux}")
    private String configLinuxTemplatePath;

    /**
     * @param folderName
     * @param fileName   if null, return folder path
     * @return
     */
    private String getPath(String folderName, String fileName) {
        // 操作系统名称
        String osName = System.getProperties().getProperty("os.name");
        String filePath = "";
        if (osName.toLowerCase().contains("windows")) {
            filePath = configWindowsFilePath + folderName + "\\" + fileName;
        } else {
            filePath = configLinuxFilePath + folderName + "/" + fileName;
        }
        return filePath;
    }

    public List<String> getFiles(String folderName) {
        List<String> files = new ArrayList<>();
        String filePath = getPath(folderName, "");
        try {
            File file = new File(filePath);
            File[] tempList = file.listFiles();
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
                    String fileName = tempList[i].getName();
                    files.add(fileName);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return files;
    }

    /**
     * @Description：上传文件
     */
    public int fileUpload(MultipartFile[] file, String folderName) throws Exception {
        if (file == null || file.length == 0) {
//            throw new GlobalException("未选择需上传的日志文件");
        }
//        String filePath = new File("ccs_app").getAbsolutePath();//获取应用程序的绝对路径
         /*
         // 分隔符
         String fileSeperator = File.separator;
         // 用户主目录
         String userHome = System.getProperties().getProperty("user.home");
         // Java实时运行环境的安装目录
         String javaPath = System.getProperties().getProperty("java.home");
         // 当前用户程序所在目录
         String userDir = System.getProperties().getProperty("user.dir");
         // JDK的安装目录
         String jdkDir = System.getProperties().getProperty("java.ext.dirs");
         */
        String filePath = getPath(folderName, "");
        File fileUpload = new File(filePath);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }
        for (MultipartFile f : file) {
            fileUpload = new File(filePath, f.getOriginalFilename());
            if (fileUpload.exists()) {
                //throw new GlobalException("上传的日志文件已存在");
            }
            try {
                //覆盖文件
                f.transferTo(fileUpload);
            } catch (IOException e) {
                //throw new GlobalException("上传日志文件到服务器失败：" + e.toString());
            }
        }
        return 1;
    }


    public int deleteFiles(String folderName, String fileName) {
        String filePath = getPath(folderName, "");
        try {
            File file = new File(filePath);
            File[] fileList = file.listFiles();
            for (File f : fileList
            ) {
                if ((f.isFile() && f.getName().equals(fileName)) || fileName.equals("DELETE")) {
                    f.delete();
                }
            }
        } catch (Exception e) {
        }
        return 1;
    }


    public ResponseEntity<FileSystemResource> downloadFile(String folderName, String fileName) throws IOException {
        String filePath = getPath(folderName, "");
        File file = new File(filePath, fileName);
        return getFileOutStream(file, "OTHER");
    }

    public ResponseEntity<FileSystemResource> preview(String folderName, String fileName) {
        try {
            String prefixFilePath = getPath(folderName, "");
            File file = new File(prefixFilePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (fileName.contains(".pdf")) {
                return getFileOutStream(new File(prefixFilePath + fileName), "PDF");
            } else {
                return imgToPDF(folderName, prefixFilePath, fileName);
            }
        } catch (Exception e) {
            return null;
        } finally {
//            deleteFiles(folderName, "a1a1307177c344954064fac0058d96d6.pdf");
        }
    }

    private ResponseEntity<FileSystemResource> imgToPDF(String folderName, String prefixFilePath, String fileName) throws IOException, DocumentException {
        String randomFileName = UUIDUtils.init() + ".pdf";
        try {
            Document document = new Document(PageSize.A4);
            //创建一个img的输出流
            FileOutputStream outImage = new FileOutputStream(prefixFilePath + randomFileName);
            //将输出流写入到document中
            PdfWriter.getInstance(document, outImage);
            document.open();
            document.newPage();
            //添加标题
            document.add(new Paragraph(fileName));
            //获取当前img对象,并进行设置
            Image img = Image.getInstance(prefixFilePath + fileName);
            img.setAlignment(Image.LEFT | Image.TEXTWRAP);
            img.setBorder(Image.BOX);
            img.setBorderWidth(20);
            img.setBorderColor(BaseColor.WHITE);
            img.setAlignment(Image.MIDDLE);
            img.scalePercent(20);
            img.setRotationDegrees(0);//旋转
            document.add(img);
            outImage.flush();
            document.close();
            outImage.close();
            return getFileOutStream(new File(prefixFilePath + randomFileName), "PDF");
        } catch (Exception e) {
            deleteFiles(folderName, randomFileName);
        }
        return null;
    }

    private ResponseEntity<FileSystemResource> getFileOutStream(File file, String streamType) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        MediaType contentType = getMediaType(streamType);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        String fileName = new String(file.getName().getBytes("UTF-8"), "iso-8859-1");
        headers.add("Content-Disposition", "attachment; filename=" + fileName);
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(contentType)
                .body(new FileSystemResource(file));
    }


    private static MediaType getMediaType(String streamType) {
        MediaType contentType = null;
        switch (streamType) {
            case "PDF":
                contentType = MediaType.parseMediaType("application/pdf;charset=utf-8");
                break;
            case "OTHER":
                contentType = MediaType.parseMediaType("application/octet-stream");
        }
        return contentType;
    }
}
