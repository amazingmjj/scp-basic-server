package org.zhd.basic.controller;

import org.springframework.web.bind.annotation.*;
import org.xy.api.dpi.basic.BasicSeqDpi;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.BasicSeqDto;
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
public class BasicSeqController extends BaseController implements BasicSeqDpi {

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("api/basic/basicSeq")
    public Map<String, Object> saveOrUpdate(BasicSeqDto model) throws Exception {
        // FIXME DO SAVEORUPDATE ACTION
        return ApiUtil.responseDto(basicSeqService.saveOrUpdate(model));
    }

    /**
     * 当前页面 currentPage 1(第一页)
     * 页面行数 pageSize(页面行数)
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("api/basic/basicSeq/list")
    public BaseListDto<BasicSeqDto> selectPage(HttpServletRequest request) throws Exception {
        return basicSeqService.selectPage(DaoUtil.requestMap2Map(request));
    }

    public Map<String, Object> delete(List<Long> ids) throws Exception {
        basicSeqService.delete(ids);
        return ApiUtil.responseCode();
    }

    @Override
    public Map<String, Object> updateSeq(String tableCode, String tableName) throws Exception {
        basicSeqService.updateSeq(tableCode, tableName);
        return ApiUtil.responseCode();
    }
}