package org.zhd.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.zhd.basic.entity.FeeItem;

import java.util.List;

/**
* 费用项目数据层
*
* @author samy
*/
public interface FeeItemMapper extends BaseMapper<FeeItem> {
    /**
     * 根据ParentId 查询费用项目数量
     * @param parentIds
     * @return 仓库下的库区list
     */
    @Select(" select t1.name||'-'||t.name from t_fee_item t ,t_fee_category t1 where t.parent_code = t1.code and instr( #{parentIds},t1.id)>0 ")
    List<String> selectByParentId(String parentIds);
}