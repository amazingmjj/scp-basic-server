-- 员工表视图
create view v_t_employee as
SELECT t2.*, t1.org_names, d.name as dpt_name FROM T_EMPLOYEE t2, (SELECT te.id,wm_concat(org.name) AS org_names  FROM T_EMPLOYEE te, t_org org WHERE INSTR(te.ORG_CODES, org.code) > 0 GROUP BY te.id) t1, t_dpt d WHERE t2.id = t1.id(+) and t2.dpt_code = d.code(+);

-- 物资代码视图
create view v_t_goods as
SELECT t.*, t2.name AS PARENT_NAME FROM T_GOODS t LEFT JOIN T_GOODS_PARENT_NAME t2 ON t2.CODE = t.parent_code;
