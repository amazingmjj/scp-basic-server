package org.zhd.basic.controller;

import org.springframework.web.bind.annotation.*;
import org.xy.api.dpi.basic.GoodsParentNameDpi;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.GoodsParentNameDto;
import org.xy.api.enums.ApiEnum;
import org.xy.api.utils.ApiUtil;
import org.xy.api.utils.DaoUtil;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

/**
* 品名大类 控制层
*
* @author samy
*/
@RestController
public class GoodsParentNameController extends BaseController implements GoodsParentNameDpi {

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("api/basic/goodsParentName")
    public Map<String, Object> saveOrUpdate(GoodsParentNameDto model) throws Exception {
        return ApiUtil.responseDto(goodsParentNameService.saveOrUpdate(model));
    }

    /**
    * 当前页面 currentPage 1(第一页)
    * 页面行数 pageSize(页面行数)
    *
    * @param request
    * @return
    * @throws Exception
    */
    @PostMapping("api/basic/goodsParentName/list")
    public BaseListDto<GoodsParentNameDto> selectPage(HttpServletRequest request) throws Exception {
        return goodsParentNameService.selectPage(DaoUtil.requestMap2Map(request));
    }

    @Override
    public Map<String, Object> delete(List<Long> ids) throws Exception {
        try {
            goodsParentNameService.delete(ids);
            return ApiUtil.responseCode();
        }catch (Exception e){
            return ApiUtil.responseCode(null,ApiEnum.FAILURE,e.getMessage());
        }
    }

    @Override
    public Map<String, Object> selectById(Long id) throws Exception {
        return ApiUtil.responseDto(goodsParentNameService.selectById(id));
    }
}