-- 集团表序列oracle
create sequence basic_org_group_seq increment by 1 start with 1 nocycle nocache;

--集团orgGroup
create sequence t_org_group_seq increment by 1 start with 1 nocycle nocache;

--组织org
create sequence t_org_seq increment by 1 start with 1 nocycle nocache;

--员工
create sequence t_employee_seq increment by 1 start with 1 nocycle nocache;

-- 部门
create sequence t_dpt_seq increment by 1 start with 1 nocycle nocache;

-- 产地
create sequence t_area_seq increment by 1 start with 1 nocycle nocache;

-- 品名
create sequence t_goods_name_seq increment by 1 start with 1 nocycle nocache;

-- 品名大类
create sequence t_goods_parent_name_seq increment by 1 start with 1 nocycle nocache;

-- 开户行
create sequence t_open_bank_seq increment by 1 start with 1 nocycle nocache;

--数据字典
create sequence t_data_dict_seq increment by 1 start with 1 nocycle nocache;

---物资代码
create sequence t_goods_seq increment by 1 start with 1 nocycle nocache;

---币种设置
create sequence t_currency_seq increment by 1 start with 1 nocycle nocache;

---费用类型
create sequence t_fee_category_seq increment by 1 start with 1 nocycle nocache;

---费用项目
create sequence t_fee_item_seq increment by 1 start with 1 nocycle nocache;

---地址设置
create sequence t_address_seq increment by 1 start with 1 nocycle nocache;

-- 序列和实体表维护表
create sequence t_basic_seq_seq increment by 1 start with 1 nocycle nocache;
