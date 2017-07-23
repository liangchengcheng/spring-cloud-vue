package cloud.simple.service.dao;

import cloud.simple.service.model.SysAdminMenu;
import cloud.simple.service.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liangchengcheng on 2017/7/23.
 */
public interface SysAdminMenuDao extends MyMapper<SysAdminMenu> {
    /**
     * 根据ruleIds查询菜单信息
     * @param ruleIds 权限id
     * @param status 状态值
     * @return List<SysAdminMenu>
     */
    List<SysAdminMenu> selectInRuleIds(@Param("ruleIds") String ruleIds, @Param("status") int status);
}
