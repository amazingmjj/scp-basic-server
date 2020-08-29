package org.zhd.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.zhd.basic.service.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Administrator
 */
public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected DataDictService dataDictService;
    @Autowired
    protected OrgGroupService orgGroupService;
    @Autowired
    protected OrgService orgService;
    @Autowired
    protected DptService dptService;
    @Autowired
    protected GoodsService goodsService;
    @Autowired
    protected EmployeeService employeeService;
    @Autowired
    protected AreaService areaService;
    @Autowired
    protected GoodsNameService goodsNameService;
    @Autowired
    protected GoodsParentNameService goodsParentNameService;
    @Autowired
    protected OpenBankService openBankService;
    @Autowired
    protected CurrencyService currencyService;
    @Autowired
    protected FeeCategoryService feeCategoryService;
    @Autowired
    protected FeeItemService feeItemService;
    @Autowired
    protected AddressService addressService;
    @Autowired
    protected BasicSeqService basicSeqService;
}
