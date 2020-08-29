package org.zhd.basic.controller;

import org.springframework.web.bind.annotation.*;
import org.xy.api.dpi.basic.GoodsNameDpi;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.GoodsNameDto;
import org.xy.api.utils.ApiUtil;
import org.xy.api.utils.DaoUtil;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

/**
* 品名 控制层
*
* @author samy
*/
@RestController
public class GoodsNameController extends BaseController implements GoodsNameDpi {

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("api/basic/goodsName")
    public Map<String, Object> saveOrUpdate(GoodsNameDto model) throws Exception {
        return ApiUtil.responseDto(goodsNameService.saveOrUpdate(model));
    }

    /**
    * 当前页面 currentPage 1(第一页)
    * 页面行数 pageSize(页面行数)
    *
    * @param request
    * @return
    * @throws Exception
    */
    @PostMapping("api/basic/goodsName/list")
    public BaseListDto<GoodsNameDto> selectPage(HttpServletRequest request) throws Exception {
        return goodsNameService.selectPage(DaoUtil.requestMap2Map(request));
    }

    @Override
    public Map<String, Object> delete(List<Long> ids) throws Exception {
        goodsNameService.delete(ids);
        return ApiUtil.responseCode();
    }

    @Override
    public Map<String, Object> selectById(Long id) throws Exception {
        return ApiUtil.responseDto(goodsNameService.selectById(id));
    }
}