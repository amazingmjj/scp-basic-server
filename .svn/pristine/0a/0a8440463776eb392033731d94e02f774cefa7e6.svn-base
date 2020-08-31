package org.zhd.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.zhd.basic.entity.Dpt;

/**
 * @author juny
 */
public interface DptMapper extends BaseMapper<Dpt> {

    /**
     * 查询实体类
     * @param name
     * @param memberCode
     * @return
     */
    @Select("select * from t_dpt t where t.name=#{name} and t.member_code=#{memberCode}")
    Dpt findByName(String name,String memberCode);
}
