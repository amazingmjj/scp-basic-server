--币种设置
insert into t_currency (id,code,member_code,convert_way,name,precision,rate,remark)
 select t.dd_id,t.dd_id,'00000001','',t.dd_name,2,0,'' from system_dd t where t.dd_classtext like '%币%'

--物资代码
delete from t_goods where id >=1;
insert into t_goods ( id,code,member_code,parent_name,name,spec,material,area,weight_range
,tolerance_range,thickness,length,per_weight,num_unit,weight_unit,measure,tax_rate,m98_meter_weight
,c98_weight,m10_meter_weight,c10_weight,status,location, mnemonic_code, remark, weight_change
 )
select t.goodscode_id,t.goodscode_code,t.member_code,t.pntree_name ,
t.partsname_name,t.goods_spec,t.goods_material,t.productarea_name,t.zlfw,t.gcfw,
t.wzhd,t.goods_property1,t.goods_pmeterweight,t.partsname_numunit,t.partsname_weightunit,
t.goods_metering,t.goods_taxrate,t.m98s_weight,t.l98s_weight,
t.m10s_weight,t.l10s_weight,t.state_flg,position,partsname_mnemcode,partsname_remark,data_awedit
from basic_goodscode t where t.goodscode_id >1;


--数据字典
delete from t_data_dict where id >1;
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
select t_data_dict_seq.nextval,d.dd_show,d.dd_classtext,d.dd_class,d.dd_order,d.member_code,d.dd_name,d.dd_remark,d.dd_type,d.dd_text
from system_dd d;
--工作组
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
select t_data_dict_seq.nextval,0,'工作组','workgroup',0,g.member_code,g.workgroup_name,g.workgroup_remark,0,g.workgroup_code
from (SELECT distinct b.member_code,b.workgroup_name,b.workgroup_remark,b.workgroup_code FROM basic_workgroup b) g;
update t_data_dict t set t.name= '板材组' where t.value='板材组';
--业务关系
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'业务关系','businessShip',0,'00000001','客户','',0,'客户');
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'业务关系','businessShip',0,'00000001','费用单位','',0,'费用单位');
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'业务关系','businessShip',0,'00000001','货主','',0,'货主');
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'业务关系','businessShip',0,'00000001','质押单位','',0,'质押单位');
--单位类型
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'单位类型','companyClass',0,'00000001','贸易商','',0,'贸易商');
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'单位类型','companyClass',0,'00000001','电商','',0,'电商');
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'单位类型','companyClass',0,'00000001','生产商','',0,'生产商');
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'单位类型','companyClass',0,'00000001','其他','',0,'其他');
--行业
update t_data_dict t set t.name='生产/供应',t.value='生产/供应' where t.class_name='往来单位-所属行业' and t.name='煤炭行业';
update t_data_dict t set t.name='建筑工程',t.value='建筑工程' where t.class_name='往来单位-所属行业' and t.name='钢铁行业';
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'往来单位-所属行业','companyIndustry',0,'00000001','互联网','',0,'互联网');
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'往来单位-所属行业','companyIndustry',0,'00000001','仓储/物流','',0,'仓储/物流');
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'往来单位-所属行业','companyIndustry',0,'00000001','汽车生产','',0,'汽车生产');
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'往来单位-所属行业','companyIndustry',0,'00000001','贸易/进出口','',0,'贸易/进出口');
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'往来单位-所属行业','companyIndustry',0,'00000001','电子商务','',0,'电子商务');
--信用情况
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'信用情况','creditInfo',0,'00000001','良好','',0,'良好');
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'信用情况','creditInfo',0,'00000001','一般','',0,'一般');
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'信用情况','creditInfo',0,'00000001','较差','',0,'较差');
insert into t_data_dict t
(t.id,t.can_show,t.class_name,t.class_name_code,t.dd_order,t.member_code,t.name,t.remark,t.type,t.value)
VALUES (t_data_dict_seq.nextval,0,'信用情况','creditInfo',0,'00000001','极差','',0,'极差');








