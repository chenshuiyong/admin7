package cc.mrbird.system.controller;

import cc.mrbird.common.util.FileUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ImageUploadController {

  /**
   * 普通上传 对应html:index.html url:localhost:8080/index.html ajax上传 对应html:ajaxIndex.html
   * url:localhost:8080/ajaxIndex.html
   *
   * @param file
   * @return
   */
  @RequestMapping("/imageUpload")
  public Map imageUpload(HttpServletRequest req,@RequestParam("fileName") MultipartFile file) {
    String result_msg = ""; // 上传结果信息

    Map<String, Object> root = new HashMap<String, Object>();

    if (file.getSize() / 20000 > 100) {
      result_msg = "图片大小不能超过100KB";
    } else {
      // 判断上传文件格式
      String fileType = file.getContentType();
      if (fileType.equals("image/jpeg")
          || fileType.equals("image/png")
          || fileType.equals("image/jpeg")) {
        // 要上传的目标文件存放的绝对路径
        // 用src为保存绝对路径不能改名只能用原名，不用原名会导致ajax上传图片后在前端显示时出现404错误-->原因未知
        //                String
        // localPath="F:\\IDEAProject\\imageupload\\src\\main\\resources\\static\\img";
        //afinal String localPath = "F:\\IDEAProject\\imageupload\\target\\classes\\static\\img";
        String localPath=  req.getServletContext().getRealPath("")+ "uploaded" + File.separator;
        // 上传后保存的文件名(需要防止图片重名导致的文件覆盖)
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 重新生成文件名
        fileName = UUID.randomUUID() + suffixName;
        if (FileUtils.upload(file, localPath, fileName)) {
          // 文件存放的相对路径(一般存放在数据库用于img标签的src)
          String relativePath = "uploaded/" + fileName;
          root.put("relativePath", relativePath); // 前端根据是否存在该字段来判断上传是否成功
          result_msg = "图片上传成功";
        } else {
          result_msg = "图片上传失败";
        }
      } else {
        result_msg = "图片格式不正确";
      }
    }

    root.put("result_msg", result_msg);

    //        JSON.toJSONString(root,SerializerFeature.DisableCircularReferenceDetect);
    String root_json = JSON.toJSONString(root);
    System.out.println(root_json);
    return root;
  }

  /**
   * 多图片上传 对应html:multipleIndex.html 对应URL:localhost:8080/multipleIndex.html
   *
   * @param files
   * @return
   */
  @RequestMapping("/multipleImageUpload")
  public List multipleImageUpload(HttpServletRequest req, @RequestParam("uploadFiles") MultipartFile[] files) {
    System.out.println("上传的图片数：" + files.length);

    List<Map<String, Object>> root = new ArrayList<Map<String, Object>>();

    for (MultipartFile file : files) { // 循环保存文件

      Map<String, Object> result = new HashMap<String, Object>(); // 一个文件上传的结果
      String result_msg = ""; // 上传结果信息

      if (file.getSize() / 1000 > 100) {
        result_msg = "图片大小不能超过100KB";
      } else {
        // 判断上传文件格式
        String fileType = file.getContentType();
        if (fileType.equals("image/jpeg")
            || fileType.equals("image/png")
            || fileType.equals("image/bmp")
            || fileType.equals("image/gif")
            || fileType.equals("image/jpeg")) {
          // 要上传的目标文件存放的绝对路径
          //final String localPath = "F:\\IDEAProject\\imageupload\\target\\classes\\static\\img";
          String localPath=  req.getServletContext().getRealPath("")+ "uploaded" + File.separator;
          // 上传后保存的文件名(需要防止图片重名导致的文件覆盖)
          // 获取文件名
          String fileName = file.getOriginalFilename();
          // 获取文件后缀名
          String suffixName = fileName.substring(fileName.lastIndexOf("."));
          // 重新生成文件名
          fileName = UUID.randomUUID() + suffixName;
          if (FileUtils.upload(file, localPath, fileName)) {
            // 文件存放的相对路径(一般存放在数据库用于img标签的src)
            String relativePath = "uploaded/" + fileName;
            result.put("relativePath", relativePath); // 前端根据是否存在该字段来判断上传是否成功
            result_msg = "图片上传成功";
          } else {
            result_msg = "图片上传失败";
          }
        } else {
          result_msg = "图片格式不正确";
        }
      }
      result.put("result_msg", result_msg);
      root.add(result);
    }
    String root_json = JSON.toJSONString(root);
    System.out.println(root_json);
    return root;
  }
}
