package org.zhd.basic.controller;

import org.springframework.web.bind.annotation.*;
import org.xy.api.dpi.basic.CurrencyDpi;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.CurrencyDto;
import org.xy.api.utils.ApiUtil;
import org.xy.api.utils.DaoUtil;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

/**
* 货币 控制层
*
* @author samy
*/
@RestController
public class CurrencyController extends BaseController implements CurrencyDpi {

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("api/basic/currency")
    public Map<String, Object> saveOrUpdate(CurrencyDto model) throws Exception {
        return ApiUtil.responseDto(currencyService.saveOrUpdate(model));
    }

    /**
    * 当前页面 currentPage 1(第一页)
    * 页面行数 pageSize(页面行数)
    *
    * @param request
    * @return
    * @throws Exception
    */
    @PostMapping("api/basic/currency/list")
    public BaseListDto<CurrencyDto> selectPage(HttpServletRequest request) throws Exception {
        return currencyService.selectPage(DaoUtil.requestMap2Map(request));
    }

    @Override
    public Map<String, Object> delete(List<Long> ids) throws Exception {
        currencyService.delete(ids);
        return ApiUtil.responseCode();
    }

    @Override
    public Map<String, Object> selectById(Long id) throws Exception {
        return ApiUtil.responseDto(currencyService.selectById(id));
    }
}