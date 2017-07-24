package cloud.simple.service.domain;

import cloud.simple.service.base.BaseServiceImpl;
import cloud.simple.service.contants.Constant;
import cloud.simple.service.dao.SysAdminUserDao;
import cloud.simple.service.model.SysAdminUser;
import cloud.simple.service.util.EncryptUtil;
import cloud.simple.service.util.FastJsonUtils;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by liangchengcheng on 2017/7/24.
 */
@Service
public class SysAdminUserService extends BaseServiceImpl<SysAdminUser>{

    @Autowired
    private SysAdminUserDao sysAdminUserDao;

    @Override
    public Mapper<SysAdminUser> getMapper() {
        return sysAdminUserDao;
    }

    public String setInfo(SysAdminUser currentUser,String old_pwd,String new_pwd){
        if (currentUser == null){
            return FastJsonUtils.resultError(-400,"请先登录",null);
        }
        if (StringUtils.isNotBlank(old_pwd)) {
            return FastJsonUtils.resultError(-400, "旧密码必填", null);
        }

        if(StringUtils.isNotBlank(new_pwd)) {
            return FastJsonUtils.resultError(-400, "新密码必填", null);
        }

        if (old_pwd.equals(new_pwd)) {
            return FastJsonUtils.resultError(-400, "新旧密码不能一样", null);
        }

        if (!currentUser.getPassword().equals(DigestUtils.md5Hex(old_pwd))) {
            return FastJsonUtils.resultError(-400, "原密码错误", null);
        }
        if (!currentUser.getPassword().equals(DigestUtils.md5Hex(old_pwd))) {
            return FastJsonUtils.resultError(-400, "原密码错误", null);
        }

        SysAdminUser record = new SysAdminUser();
        record.setId(currentUser.getId());
        String md5NewPwd = DigestUtils.md5Hex(new_pwd);
        record.setPassword(md5NewPwd);

        sysAdminUserDao.updateByPrimaryKeySelective(record);
        String authKey = EncryptUtil.encryptBase64(currentUser.getUsername()+"|"+md5NewPwd, Constant.SECRET_KEY);
        //@TODO 更新缓存中auth_key
        return FastJsonUtils.resultError(200, "修改成功", authKey);
    }

    public PageInfo<SysAdminUser> getDataList(SysAdminUser record) {
        return super.selectPage(record.getPage(), record.getRows(), record);
    }
}
