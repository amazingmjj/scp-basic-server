package org.zhd.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dpi.BaseService;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.GoodsDto;
import org.xy.api.mapper.CommMapper;
import org.xy.api.utils.CommUtil;
import org.xy.api.utils.DaoUtil;
import org.xy.api.utils.StringUtil;
import org.zhd.basic.entity.Goods;
import org.zhd.basic.mapper.GoodsMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 物资业务层
 *
 * @author samy
 */
@Service
public class GoodsService implements BaseService<GoodsDto, Long> {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private CommMapper commMapper;

    @Override
    public GoodsDto saveOrUpdate(GoodsDto model) throws Exception {
        String msg = checkGoods(model);
        if (StringUtil.isNotEmpty(msg)) {
            throw new Exception(msg);
        }

        Goods retGoods = new Goods();
        BeanUtils.copyProperties(model, retGoods);
        // 自动反写厚度和宽度(只能是小数和整数)
        String[] arr = retGoods.getSpec() == null ? new String[]{} : retGoods.getSpec().split("\\*");
        String reg = "^^[+-]?\\d+(\\.[0-9]{1,6})?$";
        if (arr.length > 0 && CommUtil.reg(reg, arr[0])) {
            // 厚度
            retGoods.setThickness(arr[0]);
        }
        if (arr.length > 1 && CommUtil.reg(reg, arr[1])) {
            // 宽度
            retGoods.setWide(arr[1]);
        }
        //新增
        if (StringUtil.isEmpty(model.getCode())) {
            String maxCode = commMapper.maxCode("code", "t_goods");
            String code = String.format("%06d", (maxCode == null ? 1 : Integer.parseInt(maxCode) + 1));
            retGoods.setCode(code);
            goodsMapper.insert(retGoods);
        } else {//修改
            Goods oldGoods = this.selectByCode(model.getCode());
            if (oldGoods == null || oldGoods.getId().equals(model.getId())) {
                goodsMapper.updateById(retGoods);
            } else {
                throw new Exception("更新失败");
            }
        }
        return this.entity2Dto(retGoods);
    }

    @Override
    public BaseListDto<GoodsDto> selectPage(Map<String, Object> params) throws Exception {
        int currentPage = Integer.parseInt(params.getOrDefault("currentPage", 1).toString());
        int pageSize = Integer.parseInt(params.getOrDefault("pageSize", 10).toString());
        QueryWrapper<GoodsDto> qw = new QueryWrapper<>();
        DaoUtil.parseGenericQueryWrapper(qw, params, GoodsDto.class);
        qw.eq("status", 0);
        IPage<GoodsDto> pages = goodsMapper.selectAllPg((Page<Goods>) DaoUtil.queryPage(currentPage, pageSize), qw);
        List<GoodsDto> list = pages.getRecords().stream().map(entity -> entity2Dto(entity)).collect(Collectors.toList());
        return new BaseListDto<GoodsDto>(list, (int) pages.getTotal());
    }

    @Override
    public void delete(List<Long> ids) throws Exception {
        // FIXME DO DELETE ACTION
    }

    @Override
    public GoodsDto selectById(Long id) throws Exception {
        Goods model = goodsMapper.selectById(id);
        return entity2Dto(model);
    }

    @Override
    public GoodsDto entity2Dto(Object source) {
        // FIXME CHANGE VALUE IN FACT REQUIREMENT
        GoodsDto dto = new GoodsDto();
        BeanUtils.copyProperties(source, dto);
        return dto;
    }


    /**
     * 获取品名大类树
     *
     * @param memberCode
     * @return
     */
    public List<Map<String, Object>> parentTree(String memberCode) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<String> parentList = goodsMapper.parentNames(memberCode);
        for (String str : parentList) {
            Map<String, Object> parentMap = new HashMap<>();
            parentMap.put("text", str);
            List<Map<String, Object>> subListMap = new ArrayList<>();
            List<String> subList = goodsMapper.subNames(memberCode, str);
            for (String sub : subList) {
                Map<String, Object> subMap = new HashMap<>();
                subMap.put("text", sub);
                subMap.put("children", new ArrayList<>());
                subListMap.add(subMap);
            }
            parentMap.put("children", subListMap);
            list.add(parentMap);
        }
        return list;
    }

    public Goods selectByCode(String code) throws Exception {
        QueryWrapper<Goods> qw = new QueryWrapper<>();
        qw.eq("code", code);
        return goodsMapper.selectOne(qw);
    }

    public Boolean checkGoodsName(GoodsDto model) {
        QueryWrapper<Goods> qw = new QueryWrapper<>();
        qw.eq("parent_code", model.getParentCode());
        qw.eq("name", model.getName());
        qw.eq("spec", model.getSpec());
        qw.eq("material", model.getMaterial());
        qw.eq("area", model.getArea());
        qw.eq("weight_range", model.getWeightRange());
        qw.eq("tolerance_range", model.getToleranceRange());
        qw.eq("length", model.getLength());
        Goods oldGoods = goodsMapper.selectOne(qw);
        return !(model.getId() == null && oldGoods == null || (null != model.getId() && (oldGoods == null || oldGoods.getId().equals(model.getId()))));
    }

    private String checkGoods(GoodsDto model) throws Exception {
        String msg = "";

        if (StringUtil.isEmpty(model.getName())) {
            msg += "物资名称为空，";
        }
        if (checkGoodsName(model)) {
            msg += "物资不能重复，";
        }

        if (StringUtil.isNotEmpty(msg)) {
            msg = msg.substring(0, msg.length() - 1);
        }
        return msg;
    }
}

