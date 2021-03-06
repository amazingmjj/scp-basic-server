package org.zhd.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.xy.api.dpi.basic.EmployeeDpi;
import org.xy.api.dto.basic.EmployeeDto;
import org.zhd.basic.entity.Employee;

import java.util.List;

/**
 * 往来单位(客户)联系人数据层
 *
 * @author samy
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 根据code查询实体类
     *
     * @param code
     * @return
     */
    @Select("select * from t_employee where code = #{code}")
    Employee selectByCode(String code);

    @Select("select * from v_t_employee where code=#{code}")
    EmployeeDto selectDtoByCode(String code);

    /**
     * 分页查询
     *
     * @param page
     * @param qw
     * @return
     */
    @Select("select * from v_t_employee ${ew.customSqlSegment}")
    IPage<EmployeeDto> selectAllPg(Page<Employee> page, @Param(Constants.WRAPPER) QueryWrapper<EmployeeDto> qw);

    /**
     * 分页查询
     *
     * @param qw
     * @return
     */
    @Select("select * from v_t_employee ${ew.customSqlSegment}")
    List<EmployeeDto> selectAll(@Param(Constants.WRAPPER) QueryWrapper<EmployeeDto> qw);
}