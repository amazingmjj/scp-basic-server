package org.zhd.basic.controller;

import org.springframework.web.bind.annotation.*;
import org.xy.api.dpi.basic.OrgGroupDpi;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.OrgGroupDto;
import org.xy.api.enums.ApiEnum;
import org.xy.api.utils.ApiUtil;
import org.xy.api.utils.DaoUtil;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

/**
 * 机构集团 控制层
 *
 * @author samy
 */
@RestController
public class OrgGroupController extends BaseController implements OrgGroupDpi {

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("api/basic/orgGroup")
    public Map<String, Object> saveOrUpdate(OrgGroupDto model) throws Exception {
        return ApiUtil.responseDto(orgGroupService.saveOrUpdate(model));
    }

    @PostMapping("api/basic/orgGroup/list")
    public BaseListDto<OrgGroupDto> selectPage(HttpServletRequest request) throws Exception {
        return orgGroupService.selectPage(DaoUtil.requestMap2Map(request));
    }

    @GetMapping("api/basic/orgGroup/allList")
    public BaseListDto<OrgGroupDto> allList(HttpServletRequest request) throws Exception {
        return orgGroupService.allList();
    }


    @Override
    public Map<String, Object> delete(List<Long> ids) throws Exception {
        orgGroupService.delete(ids);
        return ApiUtil.responseCode();
    }

    @Transactional(rollbackOn = Exception.class)
    @DeleteMapping("api/basic/orgGroup/deleteByMemberCode")
    public Map<String, Object> delete(String memberCode) throws Exception {

        try{
            orgGroupService.delete(memberCode);
            return ApiUtil.responseCode();
        }catch (Exception e){
            return ApiUtil.responseCode(null,ApiEnum.FAILURE,e.getMessage());
        }
    }

    @Override
    public Map<String, Object> selectById(Long id) throws Exception {
        return null;
    }

    public OrgGroupDto selectByCode(String memberCode) throws Exception {
        return orgGroupService.selectByCode(memberCode);
    }
}