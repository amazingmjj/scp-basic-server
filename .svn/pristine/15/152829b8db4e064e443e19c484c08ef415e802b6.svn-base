package org.zhd.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.zhd.basic.entity.Org;

import java.util.List;

/**
 * @author juny
 */
public interface OrgMapper extends BaseMapper<Org> {
    /**
     * 根据机构编号查找列表
     *
     * @param codes
     * @return
     */
    @Select("select * from t_org where code in ${codes}")
    List<Org> selectByCodes(@Param("codes") String[] codes);

    /**
     * 根据机构编号查机构
     *
     * @param code
     * @return
     */
    @Select("select * from t_org where code=#{code}")
    Org selectByCode(String code);

    /**
     * 根据memberCode 查询机构数量
     * @param memberCode
     * @return
     */
    @Select("select count(*) from t_org where member_code = #{memberCode}")
    Integer selectByMemberCodeCount(String memberCode);
}
