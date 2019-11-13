package cc.mrbird.system.controller;

import cc.mrbird.common.http.Response;
import cc.mrbird.common.http.model.DefaultPutRet;
import cc.mrbird.common.qiniu.QiniuException;
import cc.mrbird.common.qiniu.Zone;
import cc.mrbird.common.util.Constant;
import cc.mrbird.common.util.FileUtils;
import cc.mrbird.common.util.qiniu.Auth;
import cc.mrbird.common.util.qiniu.Configuration;
import cc.mrbird.common.util.qiniu.UploadManager;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
public class ImageUploadController {

  /**
   * @description 上传文件
   * @param request 请求
   * @param response 响应
   * @throws IOException 异常
   */
  @RequestMapping("/fileUpload")
  public void fileUpload(HttpServletRequest request,@RequestParam("fileName") MultipartFile multipartFile, HttpServletResponse response) throws IOException {
    Map<String, Object> result = new HashMap<>();
    try {
      String name = request.getParameter("name");
      //MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
     // MultipartFile multipartFile = multipartRequest.getFile(name);
     /* if (multipartFile == null) {
        multipartFile = multipartRequest.getFile("upfile");
      }
      if (multipartFile == null) {
        return;
      }*/
      String originalName = multipartFile.getOriginalFilename();
      String fileExt = originalName.substring(originalName.lastIndexOf("."));
      result.put("name", multipartFile.getName());
      result.put("originalName", originalName);
      result.put("size", multipartFile.getSize());
      result.put("state", "SUCCESS");
      result.put("type", fileExt);
      try {
       // byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
        Auth auth = Auth.create(Constant.AccessKey, Constant.SecretKey);
        String upToken = auth.uploadToken(Constant.bucket);
        try {
          //创建上传对象
          Zone z = Zone.autoZone();
          Configuration c = new Configuration(z);
          UploadManager uploadManager = new UploadManager(c);
          Response r = uploadManager.put(FileCopyUtils.copyToByteArray(multipartFile.getInputStream()), null, upToken);
          //解析上传成功的结果
          DefaultPutRet putRet = new Gson().fromJson(r.bodyString(), DefaultPutRet.class);
          System.out.println(putRet.key);
          System.out.println(putRet.hash);
        } catch (QiniuException ex) {
          Response r = ex.response;
          System.err.println(r.toString());
          try {
            System.err.println(r.bodyString());
          } catch (QiniuException ex2) {
            //ignore
          }
        }
      } catch (UnsupportedEncodingException ex) {
        //ignore
      }
    }catch (Exception e) {
      result.put("state", e.getMessage());
    }
  }
    /*  String url = wantuService.uploadFile(Sessions.getCurrentSessionId(),
              FileCopyUtils.copyToByteArray(multipartFile.getInputStream()));
      result.put("url", url);
    } catch (Exception e) {
      result.put("state", e.getMessage());
    }*/
 /*   response.setHeader("Content-Type", "text/html");
    String callback = request.getParameter("callback");
    String json = Constant.OBJECT_MAPPAER.writeValueAsString(result);
    if (callback == null) {
      response.getWriter().print(json);
    } else {
      response.getWriter().print("<script>" + callback + "(" + json + ")</script>");
    }
  }*/
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
