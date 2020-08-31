package org.zhd.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dpi.BaseService;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.FeeItemDto;
import org.xy.api.mapper.CommMapper;
import org.xy.api.utils.StringUtil;
import org.zhd.basic.entity.FeeItem;
import org.zhd.basic.mapper.FeeItemMapper;
import org.xy.api.utils.DaoUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
* 费用项目业务层
*
* @author samy
*/
@Service
public class FeeItemService implements BaseService<FeeItemDto, Long> {

    @Autowired
    private FeeItemMapper feeItemMapper;
    @Autowired
    private CommMapper commMapper;

    @Override
    public FeeItemDto saveOrUpdate(FeeItemDto model) throws Exception {
        String msg = checkFeeItem(model);
        if (StringUtil.isNotEmpty(msg)){
            throw new Exception(msg);
        }
        FeeItem retFeeItem = new FeeItem();
        BeanUtils.copyProperties(model,retFeeItem);
        //新增
        if (StringUtil.isEmpty(model.getCode())){
            String maxCode = commMapper.maxCode("code","t_fee_item");
            String feeItemCode = String.format("%06d", (maxCode == null ? 1 : Integer.parseInt(maxCode) + 1));
            retFeeItem.setCode(feeItemCode);
            feeItemMapper.insert(retFeeItem);
        }else {//修改
            FeeItem oldFeeItem = this.selectByCode(model.getCode());
            if (oldFeeItem==null||oldFeeItem.getId().equals(model.getId())){
                feeItemMapper.updateById(retFeeItem);
            }else {
                throw new Exception("更新失败");
            }
        }
        return this.entity2Dto(retFeeItem);
    }

    @Override
    public BaseListDto<FeeItemDto> selectPage(Map<String, Object> params) throws Exception {
        // FIXME DO SELECTPAGE ACTION
        int currentPage = Integer.parseInt(params.getOrDefault("currentPage", 1).toString());
        int pageSize = Integer.parseInt(params.getOrDefault("pageSize", 10).toString());
        QueryWrapper<FeeItem> qw = new QueryWrapper<>();
        DaoUtil.parseGenericQueryWrapper(qw, params, FeeItem.class);
        IPage<FeeItem> pages = feeItemMapper.selectPage((Page<FeeItem>) DaoUtil.queryPage(currentPage, pageSize), qw);
        List<FeeItemDto> list = pages.getRecords().stream().map(entity -> entity2Dto(entity)).collect(Collectors.toList());
        return new BaseListDto<FeeItemDto>(list, (int) pages.getTotal());
    }

    @Override
    public void delete(List<Long> ids) throws Exception {
        feeItemMapper.deleteBatchIds(ids);
    }

    @Override
    public FeeItemDto selectById(Long id) throws Exception {
        FeeItem model = feeItemMapper.selectById(id);
        return entity2Dto(model);
    }

    @Override
    public FeeItemDto entity2Dto(Object source) {
        if (source == null) {
            return null;
        }
        // FIXME CHANGE VALUE IN FACT REQUIREMENT
        FeeItemDto dto = new FeeItemDto();
        BeanUtils.copyProperties(source, dto);
        return dto;
    }

    public FeeItem selectByCode(String code) throws Exception{
        QueryWrapper<FeeItem> qw = new QueryWrapper<>();
        qw.eq("code", code);
        return feeItemMapper.selectOne(qw);
    }

    private String checkFeeItem(FeeItemDto model) throws Exception{
        String msg = "";
        if (StringUtil.isEmpty(model.getMemberCode())){
            msg +="集团代码为空，";
        }else if (StringUtil.isEmpty(model.getName())){
            msg +="费用项目名称为空，";
        }else if (StringUtil.isEmpty(model.getParentCode())){
            msg +="费用类型为空，";
        }
        if (checkFeeItemName(model)){
            msg +="费用项目名称不能重复，";
        }

        if (StringUtil.isNotEmpty(msg)){
            msg = msg.substring(0,msg.length()-1);
        }
        return msg;
    }

    /**
     * 名字查重
     */
    public Boolean checkFeeItemName(FeeItemDto model){
        QueryWrapper<FeeItem> qw = new QueryWrapper<>();
        qw.eq("name",model.getName());
        FeeItem oldDFeeItem = feeItemMapper.selectOne(qw);
        return !(model.getId() == null && oldDFeeItem == null || (null!=model.getId() && (oldDFeeItem == null || oldDFeeItem.getId().equals(model.getId()))));
    }

    public List<String> selectByParentId(String parentIds) {
        return feeItemMapper.selectByParentId(parentIds);
    }
}