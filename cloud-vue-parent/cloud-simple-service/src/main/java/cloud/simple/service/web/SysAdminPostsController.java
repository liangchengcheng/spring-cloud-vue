package cloud.simple.service.web;

import cloud.simple.service.domain.SysAdminPostService;
import cloud.simple.service.model.SysAdminPost;
import cloud.simple.service.util.FastJsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by liangchengcheng on 2017/8/20.
 */
@RestController
@RequestMapping("/admin/posts")
@Api(value = "SysAdminPostsController", description="系统岗位接口")
public class SysAdminPostsController extends CommonController {
    @Autowired
    private SysAdminPostService sysAdminPostService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表", httpMethod="GET")
    @RequestMapping(value = "", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String index(String name,HttpServletRequest request) {
        List<SysAdminPost> goups = sysAdminPostService.getDataList(name);
        return FastJsonUtils.resultSuccess(200, "成功", goups);
    }

    /**
     * 读取
     */
    @ApiOperation(value = "编辑", httpMethod="GET")
    @GetMapping(value = "edit/{id}", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String read(@PathVariable Integer id,HttpServletRequest request) {
        SysAdminPost goup = sysAdminPostService.selectByPrimaryKey(id);
        return FastJsonUtils.resultSuccess(200, "成功", goup);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存", httpMethod="POST")
    @PostMapping(value = "save", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String save(@RequestBody(required=false) SysAdminPost record,HttpServletRequest request) {
        int row = sysAdminPostService.save(record);
        if(row == 0) {
            return FastJsonUtils.resultError(-200, "保存失败", null);
        }
        return FastJsonUtils.resultSuccess(200, "成功", null);
    }


    /**
     * 更新
     */
    @ApiOperation(value = "更新", httpMethod="POST")
    @PostMapping(value = "update", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String update(@RequestBody(required=false) SysAdminPost record,HttpServletRequest request) {
        int row = sysAdminPostService.save(record);
        if(row == 0) {
            return FastJsonUtils.resultError(-200, "更新失败", null);
        }
        return FastJsonUtils.resultSuccess(200, "更新成功", null);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "delete/{id}", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String delete(@PathVariable Integer id) {
        int row = sysAdminPostService.deleteByPrimaryKey(id);
        if(row == 0) {
            return FastJsonUtils.resultError(-200, "删除失败", null);
        }
        return FastJsonUtils.resultSuccess(200, "删除成功", null);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "根据ids批量删除")
    @PostMapping(value = "deletes", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String deletes(@RequestBody Map<String, Object> params) {
        @SuppressWarnings("unchecked")
        List<Integer> ids = (List<Integer>)params.get("ids");
        if (CollectionUtils.isEmpty(ids)) {
            return FastJsonUtils.resultError(-200, "操作失败", null);
        }
        try {
            for (int i = 0; i < ids.size(); i++) {
                //sysAdminPostService.deleteByPrimaryKey(ids.get(i));
            }
        } catch (Exception e) {
            return FastJsonUtils.resultError(-200, "操作失败", null);
        }
        return FastJsonUtils.resultSuccess(200, "操作成功", null);
    }

    /**
     * 启用或禁用
     */
    @ApiOperation(value = "根据ids批量启用或禁用")
    @PostMapping(value = "enables", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String enables(@RequestBody Map<String, Object> params) {
        @SuppressWarnings("unchecked")
        List<Integer> ids = (List<Integer>)params.get("ids");
        byte status = Byte.valueOf(params.get("status").toString());
        if (CollectionUtils.isEmpty(ids)) {
            return FastJsonUtils.resultError(-200, "操作失败", null);
        }
        try {
            for (int i = 0; i < ids.size(); i++) {
                SysAdminPost record = new SysAdminPost();
                record.setId(Integer.valueOf(ids.get(0)));
                record.setStatus(status);
                sysAdminPostService.updateByPrimaryKeySelective(record);
            }
        } catch (Exception e) {
            return FastJsonUtils.resultError(-200, "保存失败", null);
        }
        return FastJsonUtils.resultSuccess(200, "成功", null);
    }
}
