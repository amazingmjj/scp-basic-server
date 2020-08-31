package org.zhd.basic.controller;

import org.springframework.web.bind.annotation.*;
import org.xy.api.dpi.basic.FeeItemDpi;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.FeeItemDto;
import org.xy.api.utils.ApiUtil;
import org.xy.api.utils.DaoUtil;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

/**
* 费用项目 控制层
*
* @author samy
*/
@RestController
public class FeeItemController extends BaseController implements FeeItemDpi {

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("api/basic/feeItem")
    public Map<String, Object> saveOrUpdate(FeeItemDto model) throws Exception {
        // FIXME DO SAVEORUPDATE ACTION
        return ApiUtil.responseDto(feeItemService.saveOrUpdate(model));
    }

    /**
    * 当前页面 currentPage 1(第一页)
    * 页面行数 pageSize(页面行数)
    *
    * @param request
    * @return
    * @throws Exception
    */
    @PostMapping("api/basic/feeItem/list")
    public BaseListDto<FeeItemDto> selectPage(HttpServletRequest request) throws Exception {
        return feeItemService.selectPage(DaoUtil.requestMap2Map(request));
    }

    @Override
    public Map<String, Object> delete(List<Long> ids) throws Exception {
        feeItemService.delete(ids);
        return ApiUtil.responseCode();
    }

    @Override
    public Map<String, Object> selectById(Long id) throws Exception {
        return ApiUtil.responseDto(feeItemService.selectById(id));
    }
}