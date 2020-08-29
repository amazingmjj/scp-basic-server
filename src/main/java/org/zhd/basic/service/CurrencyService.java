package org.zhd.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dpi.BaseService;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.CurrencyDto;
import org.xy.api.mapper.CommMapper;
import org.xy.api.utils.StringUtil;
import org.zhd.basic.entity.Currency;
import org.zhd.basic.mapper.CurrencyMapper;
import org.xy.api.utils.DaoUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
* 货币业务层
*
* @author samy
*/
@Service
public class CurrencyService implements BaseService<CurrencyDto, Long> {

    @Autowired
    private CurrencyMapper currencyMapper;

    @Autowired
    private CommMapper commMapper;

    @Override
    public CurrencyDto saveOrUpdate(CurrencyDto model) throws Exception {
        String msg = checkCurrency(model);
        if (StringUtil.isNotEmpty(msg)){
            throw new Exception(msg);
        }

        Currency retCurrency = new Currency();
        BeanUtils.copyProperties(model,retCurrency);
        //新增
        if (StringUtil.isEmpty(model.getCode())){
            String maxCode = commMapper.maxCode("code","t_currency");
            String code = String.format("%06d", (maxCode == null ? 1 : Integer.parseInt(maxCode) + 1));
            retCurrency.setCode(code);
            currencyMapper.insert(retCurrency);
        }else {//修改
            Currency oldCurrency = this.selectByCode(model.getCode());
            if (oldCurrency==null||oldCurrency.getId().equals(model.getId())){
                currencyMapper.updateById(retCurrency);
            }else {
                throw new Exception("更新失败");
            }
        }
        return this.entity2Dto(retCurrency);
    }

    @Override
    public BaseListDto<CurrencyDto> selectPage(Map<String, Object> params) throws Exception {
        int currentPage = Integer.parseInt(params.getOrDefault("currentPage", 1).toString());
        int pageSize = Integer.parseInt(params.getOrDefault("pageSize", 10).toString());
        QueryWrapper<Currency> qw = new QueryWrapper<>();
        DaoUtil.parseGenericQueryWrapper(qw, params, Currency.class);
        IPage<Currency> pages = currencyMapper.selectPage((Page<Currency>) DaoUtil.queryPage(currentPage, pageSize), qw);
        List<CurrencyDto> list = pages.getRecords().stream().map(entity -> entity2Dto(entity)).collect(Collectors.toList());
        return new BaseListDto<CurrencyDto>(list, (int) pages.getTotal());
    }

    @Override
    public void delete(List<Long> ids) throws Exception {
        currencyMapper.deleteBatchIds(ids);
    }

    @Override
    public CurrencyDto selectById(Long id) throws Exception {
        Currency model = currencyMapper.selectById(id);
        return entity2Dto(model);
    }

    @Override
    public CurrencyDto entity2Dto(Object source) {
        if (source == null) {
            return null;
        }
        CurrencyDto dto = new CurrencyDto();
        BeanUtils.copyProperties(source, dto);
        return dto;
    }
    public Currency selectByCode(String code) throws Exception{
        QueryWrapper<Currency> qw = new QueryWrapper<>();
        qw.eq("code", code);
        return currencyMapper.selectOne(qw);
    }

    public Boolean checkCurrencyName(CurrencyDto model){
        QueryWrapper<Currency> qw = new QueryWrapper<>();
        qw.eq("name",model.getName());
        Currency oldCurrency = currencyMapper.selectOne(qw);
        return !(model.getId() == null && oldCurrency == null || (null!=model.getId() && (oldCurrency == null || oldCurrency.getId().equals(model.getId()))));
    }

    private String checkCurrency(CurrencyDto model) throws Exception{
        String msg = "";

        if (StringUtil.isEmpty(model.getName())){
            msg +="币种名称为空，";
        }
        if (checkCurrencyName(model)){
            msg +="币种名称不能重复，";
        }

        if (StringUtil.isNotEmpty(msg)){
            msg = msg.substring(0,msg.length()-1);
        }
        return msg;
    }

}