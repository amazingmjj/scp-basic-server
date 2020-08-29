package org.zhd.basic.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xy.api.dpi.basic.OrgDpi;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.OrgDto;
import org.xy.api.utils.ApiUtil;
import org.xy.api.utils.DaoUtil;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * 机构 控制层
 *
 * @author samy
 */
@RestController
public class OrgController extends BaseController implements OrgDpi {

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("api/basic/org")
    public Map<String, Object> saveOrUpdate(OrgDto model) throws Exception {
        return ApiUtil.responseDto(orgService.saveOrUpdate(model));
    }

    @PostMapping("api/basic/org/list")
    public BaseListDto<OrgDto> selectPage(HttpServletRequest request) throws Exception {
        return orgService.selectPage(DaoUtil.requestMap2Map(request));
    }

    @Override
    public Map<String, Object> delete(List<Long> ids) throws Exception {
        orgService.delete(ids);
        return ApiUtil.responseCode();
    }


    @Override
    public Map<String, Object> selectById(Long id) throws Exception {
        return ApiUtil.responseDto(orgGroupService.selectById(id));
    }

    @Override
    public Map<String, Object> selectByCode(String code) throws Exception {
        return ApiUtil.responseDto(orgService.selectByCode(code));
    }

}