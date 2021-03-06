package org.zhd.basic.controller;

import org.springframework.web.bind.annotation.*;
import org.xy.api.dpi.basic.AreaDpi;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.AreaDto;
import org.xy.api.utils.ApiUtil;
import org.xy.api.utils.DaoUtil;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

/**
* 地区 控制层
*
* @author samy
*/
@RestController
public class AreaController extends BaseController implements AreaDpi {

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("api/basic/area")
    public Map<String, Object> saveOrUpdate(AreaDto model) throws Exception {
        return ApiUtil.responseDto(areaService.saveOrUpdate(model));
    }

    /**
    * 当前页面 currentPage 1(第一页)
    * 页面行数 pageSize(页面行数)
    *
    * @param request
    * @return
    * @throws Exception
    */
    @PostMapping("api/basic/area/list")
    public BaseListDto<AreaDto> selectPage(HttpServletRequest request) throws Exception {
        return areaService.selectPage(DaoUtil.requestMap2Map(request));
    }

    @Override
    public Map<String, Object> delete(List<Long> ids) throws Exception {
        areaService.delete(ids);
        return ApiUtil.responseCode();
    }

    @Override
    public Map<String, Object> selectById(Long id) throws Exception {
        return ApiUtil.responseDto(areaService.selectById(id));
    }
}