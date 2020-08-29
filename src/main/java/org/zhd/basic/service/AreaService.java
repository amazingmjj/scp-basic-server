package org.zhd.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dpi.BaseService;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.AreaDto;
import org.xy.api.mapper.CommMapper;
import org.xy.api.utils.StringUtil;
import org.zhd.basic.entity.Area;
import org.zhd.basic.mapper.AreaMapper;
import org.xy.api.utils.DaoUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
* 地区业务层
*
* @author samy
*/
@Service
public class AreaService implements BaseService<AreaDto, Long> {

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private CommMapper commMapper;

    @Override
    public AreaDto saveOrUpdate(AreaDto model) throws Exception {
        String msg = checkArea(model);
        if (StringUtil.isNotEmpty(msg)){
            throw new Exception(msg);
        }
        Area retArea = new Area();
        BeanUtils.copyProperties(model,retArea);
        //新增
        if (StringUtil.isEmpty(model.getCode())){
            String maxCode = commMapper.maxCode("code","t_area");
            String areaCode = String.format("%06d", (maxCode == null ? 1 : Integer.parseInt(maxCode) + 1));
            retArea.setCode(areaCode);
            areaMapper.insert(retArea);
        }else {//修改
            Area oldArea = this.selectByCode(model.getCode());
            if (oldArea==null||oldArea.getId().equals(model.getId())){
                areaMapper.updateById(retArea);
            }else {
                throw new Exception("更新失败");
            }
        }
        return this.entity2Dto(retArea);
    }

    @Override
    public BaseListDto<AreaDto> selectPage(Map<String, Object> params) throws Exception {
        // FIXME DO SELECTPAGE ACTION
        int currentPage = Integer.parseInt(params.getOrDefault("currentPage", 1).toString());
        int pageSize = Integer.parseInt(params.getOrDefault("pageSize", 10).toString());
        QueryWrapper<Area> qw = new QueryWrapper<>();
        DaoUtil.parseGenericQueryWrapper(qw, params, Area.class);
        IPage<Area> pages = areaMapper.selectPage((Page<Area>) DaoUtil.queryPage(currentPage, pageSize), qw);
        List<AreaDto> list = pages.getRecords().stream().map(entity -> entity2Dto(entity)).collect(Collectors.toList());
        return new BaseListDto<AreaDto>(list, (int) pages.getTotal());
    }

    @Override
    public void delete(List<Long> ids) throws Exception {
        areaMapper.deleteBatchIds(ids);
    }

    @Override
    public AreaDto selectById(Long id) throws Exception {
        Area model = areaMapper.selectById(id);
        return entity2Dto(model);
    }

    @Override
    public AreaDto entity2Dto(Object source) {
        if (source == null) {
            return null;
        }
        // FIXME CHANGE VALUE IN FACT REQUIREMENT
        AreaDto dto = new AreaDto();
        BeanUtils.copyProperties(source, dto);
        return dto;
    }

    public Area selectByCode(String code) throws Exception{
        QueryWrapper<Area> qw = new QueryWrapper<>();
        qw.eq("code", code);
        return areaMapper.selectOne(qw);
    }

    private String checkArea(AreaDto model) throws Exception{
        String msg = "";
        if (StringUtil.isEmpty(model.getMemberCode())){
            msg +="集团代码为空，";
        }else if (StringUtil.isEmpty(model.getName())){
            msg +="产地名称为空，";
        }

        if (checkAreaName(model)){
            msg +="产地名称不能重复，";
        }

        if (StringUtil.isNotEmpty(msg)){
            msg = msg.substring(0,msg.length()-1);
        }
        return msg;
    }

    /**
     * 名字查重
     */
    public Boolean checkAreaName(AreaDto model){
        QueryWrapper<Area> qw = new QueryWrapper<>();
        qw.eq("name",model.getName());
        qw.eq("member_code",model.getMemberCode());
        Area oldDept = areaMapper.selectOne(qw);
        return !(model.getId() == null && oldDept == null || (null!=model.getId() && (oldDept == null || oldDept.getId().equals(model.getId()))));
    }
}