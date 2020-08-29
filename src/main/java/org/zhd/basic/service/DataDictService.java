package org.zhd.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dpi.BaseService;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.DataDictDto;
import org.xy.api.mapper.CommMapper;
import org.xy.api.utils.DaoUtil;
import org.xy.api.utils.StringUtil;
import org.zhd.basic.entity.DataDict;
import org.zhd.basic.mapper.DataDictMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author samy
 */
@Service
public class DataDictService implements BaseService<DataDictDto, Long> {
    @Autowired
    private DataDictMapper dataDictMapper;

    @Autowired
    private CommMapper commMapper;

    @Override
    public DataDictDto saveOrUpdate(DataDictDto model) throws Exception {
        //校验
        String msg = checkDataDict(model);
        if (StringUtil.isNotEmpty(msg)){
            throw new Exception(msg);
        }

        DataDict retDataDict = new DataDict();
        BeanUtils.copyProperties(model,retDataDict);
        //新增
        if (null==model.getId()){
            retDataDict.setType(0);
            retDataDict.setCanShow(0);
            dataDictMapper.insert(retDataDict);
        }else {//修改
            dataDictMapper.updateById(retDataDict);
        }
        return this.entity2Dto(retDataDict);
    }

    @Override
    public BaseListDto<DataDictDto> selectPage(Map<String, Object> params) throws Exception {
        int currentPage = Integer.parseInt(params.getOrDefault("currentPage", 1).toString());
        int pageSize = Integer.parseInt(params.getOrDefault("pageSize", 10).toString());
        QueryWrapper<DataDict> qw = new QueryWrapper<>();
        DaoUtil.parseGenericQueryWrapper(qw, params, DataDict.class);
        IPage<DataDict> pages = dataDictMapper.selectPage((Page<DataDict>) DaoUtil.queryPage(currentPage, pageSize), qw);
        List<DataDictDto> list = pages.getRecords().stream().map(entity -> entity2Dto(entity)).collect(Collectors.toList());
        return new BaseListDto<DataDictDto>(list, (int) pages.getTotal());
    }

    @Override
    public void delete(List<Long> ids) throws Exception {
        for (Long thisId:ids){
            DataDict oldDict = dataDictMapper.selectById(thisId);
            if (null==oldDict){
                throw new Exception("数据不存在!");
            }
            if (1==oldDict.getType()){
                throw new Exception("系统数据不能删除!");
            }
        }
        dataDictMapper.deleteBatchIds(ids);
    }

    @Override
    public DataDictDto selectById(Long id) throws Exception {
        return entity2Dto(dataDictMapper.selectById(id));
    }

    @Override
    public DataDictDto entity2Dto(Object source) {
        if (source == null) {
            return null;
        }
        DataDictDto dto = new DataDictDto();
        BeanUtils.copyProperties(source, dto);
        return dto;
    }

    public BaseListDto<DataDictDto> selectByClassName(String className) {
        List<DataDict> list = dataDictMapper.selectByClassName(className);
        return new BaseListDto<DataDictDto>(list.stream().map(this::entity2Dto).collect(Collectors.toList()), list.size());
    }

    public DataDict selectByCode(String code) throws Exception{
        QueryWrapper<DataDict> qw = new QueryWrapper<>();
        qw.eq("class_name_code", code);
        return dataDictMapper.selectOne(qw);
    }

    public Boolean checkDataDictName(DataDictDto model){
        QueryWrapper<DataDict> qw = new QueryWrapper<>();
        qw.eq("class_name",model.getClassName());
        qw.eq("name",model.getClassName());
        DataDict dataDict = dataDictMapper.selectOne(qw);
        return !(model.getId() == null && dataDict == null || (null!=model.getId() && (dataDict == null || dataDict.getId().equals(model.getId()))));

    }

    private String checkDataDict(DataDictDto model) throws Exception{
        String msg = "";

        if (StringUtil.isEmpty(model.getName())){
            msg +="名称为空，";
        }
        if (checkDataDictName(model)){
            msg +="名称不能重复，";
        }

        if (StringUtil.isNotEmpty(msg)){
            msg = msg.substring(0,msg.length()-1);
        }
        return msg;
    }

}
