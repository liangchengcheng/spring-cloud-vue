package cloud.simple.service.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liangchengcheng on 2017/8/20.
 */
@RestController
@RequestMapping("/admin/posts")
@Api(value = "SysAdminPostsController", description="系统岗位接口")
public class SysAdminPostsController extends CommonController {

}
