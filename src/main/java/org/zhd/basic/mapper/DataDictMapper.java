package org.zhd.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.zhd.basic.entity.DataDict;

import java.util.List;


/**
 * @author juny
 */
@Mapper
public interface DataDictMapper extends BaseMapper<DataDict> {
    /**
     * 根据类别名称查找列表
     *
     * @param className
     * @return
     */
    @Select("select * from system_dd where dd_classtext=#{className}")
    @ResultMap("dataDictPojo")
    List<DataDict> selectByClassName(String className);
}
