package org.zhd.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dpi.BaseService;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.FeeCategoryDto;
import org.xy.api.mapper.CommMapper;
import org.xy.api.utils.StringUtil;
import org.zhd.basic.entity.FeeCategory;
import org.zhd.basic.mapper.FeeCategoryMapper;
import org.xy.api.utils.DaoUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
* 费用类别业务层
*
* @author samy
*/
@Service
public class FeeCategoryService implements BaseService<FeeCategoryDto, Long> {

    @Autowired
    private FeeCategoryMapper feeCategoryMapper;
    @Autowired
    private CommMapper commMapper;
    @Autowired
    private FeeItemService feeItemService;

    @Override
    public FeeCategoryDto saveOrUpdate(FeeCategoryDto model) throws Exception {
        String msg = checkFeeCategory(model);
        if (StringUtil.isNotEmpty(msg)){
            throw new Exception(msg);
        }
        FeeCategory retFeeCategory = new FeeCategory();
        BeanUtils.copyProperties(model,retFeeCategory);
        //新增
        if (StringUtil.isEmpty(model.getCode())){
            String maxCode = commMapper.maxCode("code","t_fee_category");
            String feeCategoryCode = String.format("%06d", (maxCode == null ? 1 : Integer.parseInt(maxCode) + 1));
            retFeeCategory.setCode(feeCategoryCode);
            feeCategoryMapper.insert(retFeeCategory);
        }else {//修改
            FeeCategory oldFeeCategory = this.selectByCode(model.getCode());
            if (oldFeeCategory==null||oldFeeCategory.getId().equals(model.getId())){
                feeCategoryMapper.updateById(retFeeCategory);
            }else {
                throw new Exception("更新失败");
            }
        }
        return this.entity2Dto(retFeeCategory);
    }

    @Override
    public BaseListDto<FeeCategoryDto> selectPage(Map<String, Object> params) throws Exception {
        // FIXME DO SELECTPAGE ACTION
        int currentPage = Integer.parseInt(params.getOrDefault("currentPage", 1).toString());
        int pageSize = Integer.parseInt(params.getOrDefault("pageSize", 10).toString());
        QueryWrapper<FeeCategory> qw = new QueryWrapper<>();
        DaoUtil.parseGenericQueryWrapper(qw, params, FeeCategory.class);
        IPage<FeeCategory> pages = feeCategoryMapper.selectPage((Page<FeeCategory>) DaoUtil.queryPage(currentPage, pageSize), qw);
        List<FeeCategoryDto> list = pages.getRecords().stream().map(entity -> entity2Dto(entity)).collect(Collectors.toList());
        return new BaseListDto<FeeCategoryDto>(list, (int) pages.getTotal());
    }

    @Override
    public void delete(List<Long> ids) throws Exception {

        List<String> warehouseArea = feeItemService.selectByParentId(ids.toString());
        if(warehouseArea.size() == 0){
            feeCategoryMapper.deleteBatchIds(ids);
        } else {
            throw new Exception( warehouseArea + "类型有下级项目，不允许删除");
        }
    }

    @Override
    public FeeCategoryDto selectById(Long id) throws Exception {
        FeeCategory model = feeCategoryMapper.selectById(id);
        return entity2Dto(model);
    }

    @Override
    public FeeCategoryDto entity2Dto(Object source) {
        if (source == null) {
            return null;
        }
        // FIXME CHANGE VALUE IN FACT REQUIREMENT
        FeeCategoryDto dto = new FeeCategoryDto();
        BeanUtils.copyProperties(source, dto);
        return dto;
    }

    public FeeCategory selectByCode(String code) throws Exception{
        QueryWrapper<FeeCategory> qw = new QueryWrapper<>();
        qw.eq("code", code);
        return feeCategoryMapper.selectOne(qw);
    }

    private String checkFeeCategory(FeeCategoryDto model) throws Exception{
        String msg = "";
        if (StringUtil.isEmpty(model.getMemberCode())){
            msg +="集团代码为空，";
        }else if (StringUtil.isEmpty(model.getName())){
            msg +="费用类型名称为空，";
        }
        if (checkFeeCategoryName(model)){
            msg +="费用类型名称不能重复，";
        }

        if (StringUtil.isNotEmpty(msg)){
            msg = msg.substring(0,msg.length()-1);
        }
        return msg;
    }

    /**
     * 名字查重
     */
    public Boolean checkFeeCategoryName(FeeCategoryDto model){
        QueryWrapper<FeeCategory> qw = new QueryWrapper<>();
        qw.eq("name",model.getName());
//        qw.eq("member_code",model.getMemberCode());
        FeeCategory oldDFeeCategory = feeCategoryMapper.selectOne(qw);
        return !(model.getId() == null && oldDFeeCategory == null || (null!=model.getId() && (oldDFeeCategory == null || oldDFeeCategory.getId().equals(model.getId()))));
    }

}