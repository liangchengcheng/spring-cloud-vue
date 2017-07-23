package cloud.simple.service.domain;

import cloud.simple.service.base.BaseService;
import cloud.simple.service.base.BaseServiceImpl;
import cloud.simple.service.dao.SysAdminGroupDao;
import cloud.simple.service.model.SysAdminGroup;
import cloud.simple.service.util.BeanToMapUtil;
import cloud.simple.service.util.Category;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * Created by liangchengcheng on 2017/7/23.
 */
@Service
public class SysAdminGroupService extends BaseServiceImpl<SysAdminGroup> {

    @Autowired
    private SysAdminGroupDao sysAdminGroupDao;

    @Override
    public Mapper<SysAdminGroup> getMapper() {
        return sysAdminGroupDao;
    }

    public List<Map<String,Object>> getDataList(){
        Example example = new Example(SysAdminGroup.class);
        List<SysAdminGroup> rootSysAdminGroups = sysAdminGroupDao.selectByExample(example);

        Map<String, String> fields = Maps.newHashMap();
        fields.put("cid", "id");
        fields.put("fid", "pid");
        fields.put("name", "title");
        fields.put("fullname", "title");
        List<Map<String, Object>> rawList = Lists.newArrayList();
        rootSysAdminGroups.forEach((m)->{
            rawList.add(BeanToMapUtil.convertBean(m));
        });


        Category cate = new Category(fields, rawList);
        return cate.getList(Integer.valueOf("0"));
    }
}
