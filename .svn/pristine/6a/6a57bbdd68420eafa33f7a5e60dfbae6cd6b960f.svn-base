package org.zhd.basic.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xy.api.dpi.basic.OpenBankDpi;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.OpenBankDto;
import org.xy.api.utils.ApiUtil;
import org.xy.api.utils.DaoUtil;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
* 开户银行 控制层
*
* @author samy
*/
@RestController
public class OpenBankController extends BaseController implements OpenBankDpi {

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("api/basic/openBank")
    public Map<String, Object> saveOrUpdate(OpenBankDto model) throws Exception {
        return ApiUtil.responseDto(openBankService.saveOrUpdate(model));
    }

    /**
    * 当前页面 currentPage 1(第一页)
    * 页面行数 pageSize(页面行数)
    *
    * @param request
    * @return
    * @throws Exception
    */
    @PostMapping("api/basic/openBank/list")
    public BaseListDto<OpenBankDto> selectPage(HttpServletRequest request) throws Exception {
        return openBankService.selectPage(DaoUtil.requestMap2Map(request));
    }

    @Override
    public Map<String, Object> delete(List<Long> ids) throws Exception {
        openBankService.delete(ids);
        return ApiUtil.responseCode();
    }

    @Override
    public Map<String, Object> selectById(Long id) throws Exception {
        return ApiUtil.responseDto(openBankService.selectById(id));
    }
}