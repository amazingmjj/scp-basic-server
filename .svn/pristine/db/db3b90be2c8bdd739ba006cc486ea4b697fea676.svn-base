package org.zhd.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.xy.api.dto.basic.GoodsDto;
import org.zhd.basic.entity.Goods;

import java.util.List;

/**
 * 物资数据层
 *
 * @author samy
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 集团物资品名大类
     *
     * @param memberCode
     * @return
     */
    @Select("select DISTINCT(parent_name) from v_t_goods where member_code=#{memberCode}")
    List<String> parentNames(String memberCode);

    /**
     * 品名大类下的小类
     */
    @Select("select distinct(name) from v_t_goods where member_code=#{memberCode} and parent_name=#{parentName}")
    List<String> subNames(String memberCode, String parentName);

    /**
     * 物资分页列表
     */
    @Select("select * from v_t_goods ${ew.customSqlSegment}")
    IPage<GoodsDto> selectAllPg(Page<Goods> page, @Param(Constants.WRAPPER) QueryWrapper<GoodsDto> qw);

}