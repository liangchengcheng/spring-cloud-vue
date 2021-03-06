package cloud.simple.service.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by liangchengcheng on 2017/7/24.
 * 上传工具类
 */
public class UploadUtils {

    public static final String allowUploadImageType = "jpg,jpge,bmp,gif,png";

    public static String saveMartipartFile(String multipartLocation, HttpServletRequest request,MultipartFile file) {
        return saveMartipartFile(multipartLocation, request, file, null, "yyyyMMdd");
    }

    public static String saveMartipartFile(String multipartLocation, HttpServletRequest request,MultipartFile file,String module) {
        return saveMartipartFile(multipartLocation, request, file, module, "yyyyMMdd");
    }

    public static String saveMartipartFile(String multipartLocation, HttpServletRequest request,
                                           MultipartFile file, String module, String format) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            String dateString = simpleDateFormat.format(new Date());
            File dir = new File(multipartLocation + "/upload/"
                    + (StringUtils.isNotEmpty(module) == true ? module + "/" + dateString : dateString));
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new Exception("创建保存目录失败");
                }
            }
            String fileName = UUID.randomUUID().toString() + "."
                    + FilenameUtils.getExtension(file.getOriginalFilename()).toUpperCase();
            return "/upload/" + (StringUtils.isNotEmpty(module) == true ? module + "/" + dateString : dateString) + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


