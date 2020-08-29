package org.zhd.basic.controller;

import org.springframework.web.bind.annotation.*;
import org.xy.api.dpi.basic.EmployeeDpi;
import org.xy.api.dto.BaseListDto;
import org.xy.api.dto.basic.EmployeeDto;
import org.xy.api.utils.ApiUtil;
import org.xy.api.utils.DaoUtil;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

/**
 * 员工 控制层
 *
 * @author samy
 */
@RestController
public class EmployeeController extends BaseController implements EmployeeDpi {

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("api/basic/employee")
    public Map<String, Object> saveOrUpdate(EmployeeDto model) throws Exception {
        return ApiUtil.responseDto(employeeService.saveOrUpdate(model));
    }

    @PostMapping("api/basic/employee/list")
    public BaseListDto<EmployeeDto> selectPage(HttpServletRequest request) throws Exception {
        return employeeService.selectPage(DaoUtil.requestMap2Map(request));
    }

    @Override
    public Map<String, Object> delete(List<Long> ids) throws Exception {
        employeeService.delete(ids);
        return ApiUtil.responseCode();
    }

    @Override
    public Map<String, Object> selectById(Long id) throws Exception {
        return ApiUtil.responseDto(employeeService.selectById(id));
    }

    @GetMapping("api/basic/employee/one")
    public Map<String, Object> selectOne(String code) throws Exception {
        return ApiUtil.responseDto(employeeService.selectDtoByCode(code));
    }
}