package org.zhd.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xy.api.dpi.basic.GoodsDpi;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.GoodsDto;
import org.xy.api.mapper.CommMapper;
import org.xy.api.utils.ApiUtil;
import org.xy.api.utils.DaoUtil;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

/**
 * 物资 控制层
 *
 * @author samy
 */
@RestController
public class GoodsController extends BaseController implements GoodsDpi {

    @Autowired
    private CommMapper commMapper;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("api/basic/goods")
    public Map<String, Object> saveOrUpdate(GoodsDto model) throws Exception {
        return ApiUtil.responseDto(goodsService.saveOrUpdate(model));
    }

    @PostMapping("api/basic/goods/list")
    public BaseListDto<GoodsDto> selectPage(HttpServletRequest request) throws Exception {
        return goodsService.selectPage(DaoUtil.requestMap2Map(request));
    }

    @Override
    public Map<String, Object> delete(List<Long> ids) throws Exception {
        goodsService.delete(ids);
        return ApiUtil.responseCode();
    }

    @Override
    public Map<String, Object> selectById(Long id) throws Exception {
        return ApiUtil.responseDto(goodsService.selectById(id));
    }

    @GetMapping("api/basic/goods/parentTree")
    public Map<String, Object> parentTree(String memberCode) {
        Map<String, Object> result = new HashMap<>(2);
        result.put("list", goodsService.parentTree(memberCode));
        return ApiUtil.responseCode(result);
    }

    @PostMapping("api/basic/goods/searchColumn")
    public Map<String, Object> searchColumns(String columnName, String searchName) {
        List<String> list = commMapper.searchColumnRecords(columnName, "basic_goodscode", "%" + searchName + "%");
        Map<String, Object> resp = new HashMap<>(2);
        resp.put("list", list);
        return ApiUtil.responseCode(resp);
    }
}