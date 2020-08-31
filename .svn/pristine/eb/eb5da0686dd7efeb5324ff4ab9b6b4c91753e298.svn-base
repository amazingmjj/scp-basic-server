package org.zhd.basic.controller;

import org.springframework.web.bind.annotation.*;
import org.xy.api.dpi.basic.FeeCategoryDpi;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.FeeCategoryDto;
import org.xy.api.utils.ApiUtil;
import org.xy.api.utils.DaoUtil;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

/**
* 费用类别 控制层
*
* @author samy
*/
@RestController
public class FeeCategoryController extends BaseController implements FeeCategoryDpi {

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("api/basic/feeCategory")
    public Map<String, Object> saveOrUpdate(FeeCategoryDto model) throws Exception {
        // FIXME DO SAVEORUPDATE ACTION
        return ApiUtil.responseDto(feeCategoryService.saveOrUpdate(model));
    }

    /**
    * 当前页面 currentPage 1(第一页)
    * 页面行数 pageSize(页面行数)
    *
    * @param request
    * @return
    * @throws Exception
    */
    @PostMapping("api/basic/feeCategory/list")
    public BaseListDto<FeeCategoryDto> selectPage(HttpServletRequest request) throws Exception {
        return feeCategoryService.selectPage(DaoUtil.requestMap2Map(request));
    }

    @Override
    public Map<String, Object> delete(List<Long> ids) throws Exception {
        feeCategoryService.delete(ids);
        return ApiUtil.responseCode();
    }

    @Override
    public Map<String, Object> selectById(Long id) throws Exception {
        return ApiUtil.responseDto(feeCategoryService.selectById(id));
    }
}