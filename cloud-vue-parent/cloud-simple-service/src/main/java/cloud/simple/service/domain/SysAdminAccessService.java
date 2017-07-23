package cloud.simple.service.domain;

import cloud.simple.service.base.BaseServiceImpl;
import cloud.simple.service.dao.SysAdminAccessDao;
import cloud.simple.service.model.SysAdminAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by liangchengcheng on 2017/7/23.
 */
@Service
public class SysAdminAccessService extends BaseServiceImpl<SysAdminAccess> {

    @Autowired
    private SysAdminAccessDao sysAdminAccessDao;

    @Override
    public Mapper<SysAdminAccess> getMapper() {
        return sysAdminAccessDao;
    }

}