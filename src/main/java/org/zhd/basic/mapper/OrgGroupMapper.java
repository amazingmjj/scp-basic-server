package org.zhd.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhd.basic.entity.Org;
import org.zhd.basic.entity.OrgGroup;

import java.util.List;

/**
 * @author juny
 */
public interface OrgGroupMapper extends BaseMapper<OrgGroup> {
    /**
     * 根据code查询实体类
     *
     * @param code
     * @return
     */
    @Select("select * from t_org_group where member_code=#{code}")
    OrgGroup selectByCode(String code);

    /**
     * 根据name查询实体类
     * @param name
     * @return
     */
    @Select("select * from t_org_group where member_name=#{name}")
    OrgGroup selectByName(String name);

    /**
     * 根据memberCode删除
     * @param code
     * @return
     */
    @Delete("delete from t_org_group where member_code=#{code}")
    int delByCode(String code);
}
