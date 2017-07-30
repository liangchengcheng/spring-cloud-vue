package cloud.simple.service.web;

import cloud.simple.service.contants.Constant;
import cloud.simple.service.domain.SysAdminMenuService;
import cloud.simple.service.domain.SysAdminRuleService;
import cloud.simple.service.domain.SysAdminUserService;
import cloud.simple.service.model.SysAdminRule;
import cloud.simple.service.model.SysAdminUser;
import cloud.simple.service.util.EncryptUtil;
import cloud.simple.service.util.FastJsonUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangchengcheng on 2017/7/25.
 */
@RestController
@RequestMapping("/admin")
@Api(value = "LoginController",description = "登录接口")
public class LoginController {
    private SysAdminUserService sysAdminUserService;
    @Autowired
    private SysAdminRuleService sysAdminRuleService;
    @Autowired
    private SysAdminMenuService sysAdminMenuService;
    @Autowired
    private DefaultKaptcha captchaProducer;


    @ApiOperation(value = "登录" ,notes = "登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "record", required=true, dataType = "SysAdminUser")	})
    @PostMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
    public String login(@RequestBody SysAdminUser record, HttpServletRequest request) {
        Map<String, Object> data = new HashMap<String, Object>();
        if(StringUtils.isBlank(record.getUsername())) {
            return FastJsonUtils.resultError(-100, "账号不能为空", null);
        }
        record.setPassword(DigestUtils.md5Hex(record.getPassword()));
        SysAdminUser adminUser = sysAdminUserService.selectOne(record);
        if(adminUser == null) {
            return FastJsonUtils.resultError(-100, "帐号与密码错误不正确", null);
        }
        if(!adminUser.getStatus().equals(Byte.valueOf("1"))) {
            return FastJsonUtils.resultError(-100, "帐号已被禁用", null);
        }
        String authKey = EncryptUtil.encryptBase64(adminUser.getUsername()+"|"+adminUser.getPassword(), Constant.SECRET_KEY);
        // 返回信息
        data.put("rememberKey", authKey);
        data.put("authKey", authKey);
        data.put("sessionId", request.getSession().getId());
        data.put("userInfo", adminUser);
        List<SysAdminRule> rulesTreeList = sysAdminRuleService.getTreeRuleByUserId(adminUser.getId());
        List<String> rulesList = sysAdminRuleService.rulesDeal(rulesTreeList);
        data.put("rulesList", rulesList);
        data.put("menusList", sysAdminMenuService.getTreeMenuByUserId(adminUser.getId()));

        return FastJsonUtils.resultSuccess(200, "登录成功", data);
    }

}
