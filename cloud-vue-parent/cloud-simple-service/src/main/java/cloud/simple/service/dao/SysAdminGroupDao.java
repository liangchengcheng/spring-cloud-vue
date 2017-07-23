package cloud.simple.service.dao;

import cloud.simple.service.model.SysAdminGroup;
import cloud.simple.service.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liangchengcheng on 2017/7/23.
 */
public interface SysAdminGroupDao extends MyMapper<SysAdminGroup> {
    /**
     * 查询分组信息
     * @param userId 用户ID
     * @param status 状态
     */
    List<SysAdminGroup> selectByUserId(@Param("userId") Integer userId, @Param("status") Byte status);
}