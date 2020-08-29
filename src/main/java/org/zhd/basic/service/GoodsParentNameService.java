package org.zhd.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dpi.BaseService;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.GoodsParentNameDto;
import org.xy.api.mapper.CommMapper;
import org.xy.api.utils.StringUtil;
import org.zhd.basic.entity.GoodsName;
import org.zhd.basic.entity.GoodsParentName;
import org.zhd.basic.mapper.GoodsNameMapper;
import org.zhd.basic.mapper.GoodsParentNameMapper;
import org.xy.api.utils.DaoUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
* 品名大类业务层
*
* @author samy
*/
@Service
public class GoodsParentNameService implements BaseService<GoodsParentNameDto, Long> {

    @Autowired
    private GoodsParentNameMapper goodsParentNameMapper;

    @Autowired
    private GoodsNameService goodsNameService;

    @Autowired
    private CommMapper commMapper;

    @Override
    public GoodsParentNameDto saveOrUpdate(GoodsParentNameDto model) throws Exception {
        String msg = checkGoodsName(model);
        if (StringUtil.isNotEmpty(msg)){
            throw new Exception(msg);
        }
        GoodsParentName retName = new GoodsParentName();
        BeanUtils.copyProperties(model,retName);
        //新增
        if (StringUtil.isEmpty(model.getCode())){
            String maxCode = commMapper.maxCode("code","t_goods_parent_name");
            String goodsParentCode = String.format("%06d", (maxCode == null ? 1 : Integer.parseInt(maxCode) + 1));
            retName.setCode(goodsParentCode);
            goodsParentNameMapper.insert(retName);
        }else {//修改
            GoodsParentName oldGoodsParentName = this.selectByCode(model.getCode());
            if (oldGoodsParentName==null||oldGoodsParentName.getId().equals(model.getId())){
                goodsParentNameMapper.updateById(retName);
                //同步品名
                goodsNameService.updateParentName(this.entity2Dto(retName));
            }else {
                throw new Exception("更新失败");
            }
        }
        return this.entity2Dto(retName);
    }

    @Override
    public BaseListDto<GoodsParentNameDto> selectPage(Map<String, Object> params) throws Exception {
        // FIXME DO SELECTPAGE ACTION
        int currentPage = Integer.parseInt(params.getOrDefault("currentPage", 1).toString());
        int pageSize = Integer.parseInt(params.getOrDefault("pageSize", 10).toString());
        QueryWrapper<GoodsParentName> qw = new QueryWrapper<>();
        DaoUtil.parseGenericQueryWrapper(qw, params, GoodsParentName.class);
        IPage<GoodsParentName> pages = goodsParentNameMapper.selectPage((Page<GoodsParentName>) DaoUtil.queryPage(currentPage, pageSize), qw);
        List<GoodsParentNameDto> list = pages.getRecords().stream().map(entity -> entity2Dto(entity)).collect(Collectors.toList());
        return new BaseListDto<GoodsParentNameDto>(list, (int) pages.getTotal());
    }

    @Override
    public void delete(List<Long> ids) throws Exception {
        if (checkHasChildren(ids)){
            throw new Exception("有下级品名，不允许删除");
        }else {
            goodsParentNameMapper.deleteBatchIds(ids);
        }
    }

    @Override
    public GoodsParentNameDto selectById(Long id) throws Exception {
        GoodsParentName model = goodsParentNameMapper.selectById(id);
        return entity2Dto(model);
    }

    @Override
    public GoodsParentNameDto entity2Dto(Object source) {
        if (source == null) {
            return null;
        }
        // FIXME CHANGE VALUE IN FACT REQUIREMENT
        GoodsParentNameDto dto = new GoodsParentNameDto();
        BeanUtils.copyProperties(source, dto);
        return dto;
    }

    public GoodsParentName selectByCode(String code) throws Exception{
        QueryWrapper<GoodsParentName> qw = new QueryWrapper<>();
        qw.eq("code", code);
        return goodsParentNameMapper.selectOne(qw);
    }

    private String checkGoodsName(GoodsParentNameDto model) throws Exception{
        String msg = "";
        if (StringUtil.isEmpty(model.getMemberCode())){
            msg +="集团代码为空，";
        }else if (StringUtil.isEmpty(model.getName())){
            msg +="品名大类名称为空，";
        }

        if (checkSameName(model)){
            msg +="品名大类名称不能重复，";
        }

        if (StringUtil.isNotEmpty(msg)){
            msg = msg.substring(0,msg.length()-1);
        }
        return msg;
    }

    /**
     * 名字查重
     */
    public Boolean checkSameName(GoodsParentNameDto model){
        QueryWrapper<GoodsParentName> qw = new QueryWrapper<>();
        qw.eq("name",model.getName());
        qw.eq("member_code",model.getMemberCode());
        GoodsParentName oldParentName = goodsParentNameMapper.selectOne(qw);
        return !(model.getId() == null && oldParentName == null || (null!=model.getId() && (oldParentName == null || oldParentName.getId().equals(model.getId()))));
    }

    public boolean checkHasChildren(List<Long> ids) throws Exception{
        boolean result = false;
        for(Long thisId:ids){
            GoodsParentName thisGoodsParent = goodsParentNameMapper.selectById(thisId);
            List<GoodsName> goodsNameList = goodsNameService.selectByParentCode(thisGoodsParent.getCode());
            result = null!=goodsNameList&&goodsNameList.size()>0?true:false;
            if (result){
                break;
            }
        }
        return result;
    }
}