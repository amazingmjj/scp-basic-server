package org.zhd.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dpi.BaseService;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.OpenBankDto;
import org.xy.api.mapper.CommMapper;
import org.xy.api.utils.DaoUtil;
import org.xy.api.utils.StringUtil;
import org.zhd.basic.entity.OpenBank;
import org.zhd.basic.mapper.OpenBankMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* 开户银行业务层
*
* @author samy
*/
@Service
public class OpenBankService implements BaseService<OpenBankDto, Long> {

    @Autowired
    private OpenBankMapper openBankMapper;

    @Autowired
    private CommMapper commMapper;

    @Override
    public OpenBankDto saveOrUpdate(OpenBankDto model) throws Exception {
        String msg = checkOpenBank(model);
        if (StringUtil.isNotEmpty(msg)){
            throw new Exception(msg);
        }

        OpenBank retOpenBank = new OpenBank();
        BeanUtils.copyProperties(model,retOpenBank);
        //新增
        if (StringUtil.isEmpty(model.getCode())){
            String maxCode = commMapper.maxCode("code","t_open_bank");
            String openBankCode = String.format("%06d", (maxCode == null ? 1 : Integer.parseInt(maxCode) + 1));
            retOpenBank.setCode(openBankCode);
            openBankMapper.insert(retOpenBank);
        }else {//修改
            OpenBank oldOpenBank = this.selectByCode(model.getCode());
            if (oldOpenBank==null||oldOpenBank.getId().equals(model.getId())){
                openBankMapper.updateById(retOpenBank);
            }else {
                throw new Exception("更新失败");
            }
        }
        return this.entity2Dto(retOpenBank);
    }

    @Override
    public BaseListDto<OpenBankDto> selectPage(Map<String, Object> params) throws Exception {
        // FIXME DO SELECTPAGE ACTION
        int currentPage = Integer.parseInt(params.getOrDefault("currentPage", 1).toString());
        int pageSize = Integer.parseInt(params.getOrDefault("pageSize", 10).toString());
        QueryWrapper<OpenBank> qw = new QueryWrapper<>();
        DaoUtil.parseGenericQueryWrapper(qw, params, OpenBank.class);
        IPage<OpenBank> pages = openBankMapper.selectPage((Page<OpenBank>) DaoUtil.queryPage(currentPage, pageSize), qw);
        List<OpenBankDto> list = pages.getRecords().stream().map(entity -> entity2Dto(entity)).collect(Collectors.toList());
        return new BaseListDto<OpenBankDto>(list, (int) pages.getTotal());
    }

    @Override
    public void delete(List<Long> ids) throws Exception {
        openBankMapper.deleteBatchIds(ids);
    }

    @Override
    public OpenBankDto selectById(Long id) throws Exception {
        OpenBank model = openBankMapper.selectById(id);
        return entity2Dto(model);
    }

    @Override
    public OpenBankDto entity2Dto(Object source) {
        if (source == null) {
            return null;
        }
        // FIXME CHANGE VALUE IN FACT REQUIREMENT
        OpenBankDto dto = new OpenBankDto();
        BeanUtils.copyProperties(source, dto);
        return dto;
    }

    public OpenBank selectByCode(String code) throws Exception{
        QueryWrapper<OpenBank> qw = new QueryWrapper<>();
        qw.eq("code", code);
        return openBankMapper.selectOne(qw);
    }

    private String checkOpenBank(OpenBankDto model) throws Exception{
        String msg = "";
//        if (StringUtil.isEmpty(model.getCode())){
//            msg +="开户行代码为空，";
//        }else

        if (StringUtil.isEmpty(model.getName())){
            msg +="银行名称为空，";
        }
        if (checkOpenBankName(model)){
            msg +="银行名称不能重复，";
        }

        if (StringUtil.isNotEmpty(msg)){
            msg = msg.substring(0,msg.length()-1);
        }
        return msg;
    }

    /**
     * 名字查重
     */
    public Boolean checkOpenBankName(OpenBankDto model){
        QueryWrapper<OpenBank> qw = new QueryWrapper<>();
        qw.eq("name",model.getName());
        OpenBank oldBank = openBankMapper.selectOne(qw);

        return !(model.getId() == null && oldBank == null || (null!=model.getId() && (oldBank == null || oldBank.getId().equals(model.getId()))));

    }

}