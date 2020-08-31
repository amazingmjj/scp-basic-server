package org.zhd.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dpi.BaseService;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.BasicSeqDto;
import org.zhd.basic.entity.BasicSeq;
import org.zhd.basic.mapper.BasicSeqMapper;
import org.xy.api.utils.DaoUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 地区业务层
 *
 * @author samy
 */
@Service
public class BasicSeqService implements BaseService<BasicSeqDto, Long> {

    @Autowired
    private BasicSeqMapper basicSeqMapper;

    @Override
    public BasicSeqDto saveOrUpdate(BasicSeqDto model) throws Exception {
        BasicSeq entity = new BasicSeq();
        BeanUtils.copyProperties(model, entity);
        if (model.getId() == null) {
            entity.setVersion(1);
            basicSeqMapper.insert(entity);
        } else {
            entity = basicSeqMapper.selectById(model.getId());
            entity.setRemark(model.getRemark());
            entity.setTableCode(model.getTableCode());
            entity.setTableName(model.getTableName());
            basicSeqMapper.updateById(entity);
        }
        return entity2Dto(entity);
    }

    @Override
    public BaseListDto<BasicSeqDto> selectPage(Map<String, Object> params) throws Exception {
        // FIXME DO SELECTPAGE ACTION
        int currentPage = Integer.parseInt(params.getOrDefault("currentPage", 1).toString());
        int pageSize = Integer.parseInt(params.getOrDefault("pageSize", 10).toString());
        QueryWrapper<BasicSeq> qw = new QueryWrapper<>();
        DaoUtil.parseGenericQueryWrapper(qw, params, BasicSeq.class);
        IPage<BasicSeq> pages = basicSeqMapper.selectPage((Page<BasicSeq>) DaoUtil.queryPage(currentPage, pageSize), qw);
        List<BasicSeqDto> list = pages.getRecords().stream().map(entity -> entity2Dto(entity)).collect(Collectors.toList());
        return new BaseListDto<BasicSeqDto>(list, (int) pages.getTotal());
    }

    @Override
    public void delete(List<Long> ids) throws Exception {
        basicSeqMapper.deleteBatchIds(ids);
    }

    @Override
    public BasicSeqDto selectById(Long id) throws Exception {
        BasicSeq model = basicSeqMapper.selectById(id);
        return entity2Dto(model);
    }

    @Override
    public BasicSeqDto entity2Dto(Object source) {
        if (source == null) {
            return null;
        }
        BasicSeqDto dto = new BasicSeqDto();
        BeanUtils.copyProperties(source, dto);
        return dto;
    }

    public void updateSeq(String newCode, String tableName) throws Exception {
        QueryWrapper<BasicSeq> qw = new QueryWrapper<>();
        qw.eq("table_name", tableName);
        BasicSeq entity = basicSeqMapper.selectOne(qw);
        if (entity == null) {
            throw new Exception("表不存在");
        }
        entity.setTableCode(newCode);
        basicSeqMapper.updateById(entity);
    }
}