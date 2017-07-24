package cloud.simple.service.domain;

import cloud.simple.service.base.BaseServiceImpl;
import cloud.simple.service.dao.SysSystemConfigDao;
import cloud.simple.service.model.SysSystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by liangchengcheng on 2017/7/24.
 */
@Service
public class SysSystemConfigService extends BaseServiceImpl<SysSystemConfig> {

    @Autowired
    private SysSystemConfigDao sysSystemConfigDao;

    @Override
    public Mapper<SysSystemConfig> getMapper() {
        return sysSystemConfigDao;
    }

}