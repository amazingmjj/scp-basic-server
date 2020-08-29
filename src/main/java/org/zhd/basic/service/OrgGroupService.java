package org.zhd.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dpi.BaseService;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.OrgGroupDto;
import org.xy.api.mapper.CommMapper;
import org.xy.api.utils.DaoUtil;
import org.zhd.basic.entity.OrgGroup;
import org.zhd.basic.mapper.OrgGroupMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 集团业务处理类
 *
 * @author juny
 */
@Service
public class OrgGroupService implements BaseService<OrgGroupDto, Long> {
    @Autowired
    private OrgGroupMapper orgGroupMapper;

    @Autowired
    private  OrgService orgService;

    @Autowired
    private CommMapper commMapper;

    public OrgGroupDto selectByCode(String memberCode) throws Exception {
        OrgGroup orgGroup = orgGroupMapper.selectByCode(memberCode);
        if (orgGroup == null) {
            return null;
        } else {
            OrgGroupDto dto = new OrgGroupDto();
            BeanUtils.copyProperties(orgGroup, dto);
            return dto;
        }
    }

    @Override
    public OrgGroupDto saveOrUpdate(OrgGroupDto model) throws Exception {
        if (model.getMemberName() == null) {
            throw new Exception("名称不能为空");
        }

        OrgGroup orgGroup = new OrgGroup();
        BeanUtils.copyProperties(model, orgGroup);
        if (model.getId() == null) {
            // 新增
            OrgGroup og = orgGroupMapper.selectByName(model.getMemberName());
            if (og == null) {
                String nextCode = commMapper.maxCode("member_code", "t_org_group");
                Integer newCode = Integer.parseInt(nextCode == null ? "0" : nextCode) + 1;
                orgGroup.setMemberCode(String.format("%06d", newCode));
                orgGroupMapper.insert(orgGroup);
            } else {
                throw new Exception("名称不能重复");
            }
        } else {
            // 更新
            OrgGroup og = orgGroupMapper.selectByCode(model.getMemberCode());
            if (og.getId().equals(model.getId())) {
                BeanUtils.copyProperties(orgGroup, og, "id", "memberCode", "createAt", "updateAt");
                orgGroupMapper.updateById(og);
            } else {
                throw new Exception("更新失败");
            }
        }
        return this.entity2Dto(orgGroup);
    }

    @Override
    public BaseListDto<OrgGroupDto> selectPage(Map<String, Object> params) {
        int currentPage = Integer.parseInt(params.getOrDefault("currentPage", 1).toString());
        int pageSize = Integer.parseInt(params.getOrDefault("pageSize", 10).toString());
        QueryWrapper<OrgGroup> qw = new QueryWrapper<>();
        DaoUtil.parseGenericQueryWrapper(qw, params, OrgGroup.class);
        IPage<OrgGroup> ipage = orgGroupMapper.selectPage((Page<OrgGroup>) DaoUtil.queryPage(currentPage, pageSize), qw);
        List<OrgGroupDto> list = ipage.getRecords().stream().map(org -> entity2Dto(org)).collect(Collectors.toList());
        System.out.println("ipage total:>>" + ipage.getTotal());
        return new BaseListDto<OrgGroupDto>(list, (int) ipage.getTotal());
    }

    public BaseListDto<OrgGroupDto> allList() {
        QueryWrapper<OrgGroup> qw = new QueryWrapper<>();
        List<OrgGroup> resList = orgGroupMapper.selectList(qw);
        List<OrgGroupDto> list = resList.stream().map(org -> entity2Dto(org)).collect(Collectors.toList());
        return new BaseListDto<OrgGroupDto>(list, list.size());
    }

    @Override
    public void delete(List<Long> ids) {
        orgGroupMapper.deleteBatchIds(ids);
    }

    public void delete(String code) throws Exception {
        if (orgService.selectByMemberCodeCount(code) == 0) {
            orgGroupMapper.delByCode(code);
        } else {
            throw new Exception("有下级公司，不允许删除");
        }

    }

    @Override
    public OrgGroupDto selectById(Long id) {
        // FIXME 用到时补全代码
        return null;
    }

    @Override
    public OrgGroupDto entity2Dto(Object source) {
        OrgGroupDto newDto = new OrgGroupDto();
        OrgGroup temp = OrgGroup.class.cast(source);
        BeanUtils.copyProperties(temp, newDto);
        return newDto;
    }
}
