package org.zhd.basic.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.xy.api.dpi.basic.DptDpi;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.DptDto;
import org.xy.api.utils.ApiUtil;
import org.xy.api.utils.DaoUtil;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

/**
 * 部门 控制层
 *
 * @author samy
 */
@RestController
public class DptController extends BaseController implements DptDpi {

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("api/basic/dpt")
    public Map<String, Object> saveOrUpdate(DptDto model) throws Exception {
        return ApiUtil.responseDto(dptService.saveOrUpdate(model));
    }

    @PostMapping("api/basic/dpt/list")
    @ApiOperation("部门列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页数", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", defaultValue = "10"),
            @ApiImplicitParam(name = "name", value = "部门名称")
    })
    public BaseListDto<DptDto> selectPage(HttpServletRequest request) throws Exception {
        return dptService.selectPage(DaoUtil.requestMap2Map(request));
    }

    @Override
    public Map<String, Object> delete(List<Long> ids) throws Exception {
        dptService.delete(ids);
        return ApiUtil.responseCode();
    }

    @Override
    public Map<String, Object> selectById(Long id) throws Exception {
        return ApiUtil.responseDto(dptService.selectById(id));
    }
}