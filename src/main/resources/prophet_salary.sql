create database prophet_salary;

use prophet_salary;

create table auth(
    id varchar(32) primary key,
    username varchar(32) comment '用户名',
    password varchar(64) comment '密码',
    role varchar(16) comment '角色: employee-普通员工    commission-委托员工    payroll-管理员'
);

create table user(
    id varchar(32) primary key,
    username varchar(32) comment '用户名',
    address varchar(256) comment '通讯地址',
    socsec_id varchar(32) comment '社保',
    tax_rate decimal comment '税收',
    other_cast decimal comment '其他扣除',
    phone varchar(11) comment '电话',
    hour_wage decimal(10, 2) comment '时薪',
    salary decimal(10, 2) comment '薪资',
    commission_rate decimal(10, 2) comment '佣金率',
    duration_limit int comment '工作时间限制',
    type varchar(16) comment '员工类型: salary-工资制   commission-委托制    wage-时薪制',
    payment varchar(16) comment '支付类型: mail-邮寄    receive-领取    bank-银行',
    mail_address varchar(32) comment '邮箱地址',
    bank_name varchar(16) comment '银行',
    bank_account varchar(16) comment '银行账号'
);

create table purchase_order(
    id varchar(32) primary key,
    employee_id varchar(32) comment '员工id',
    product_name varchar(32) comment '产品名称',
    pay decimal(10, 2) comment '支付金额',
    phone varchar(11) comment '电话',
    address varchar(256) comment '地址',
    date varchar(32) comment '日期'
);

create table time_card(
    id varchar(32) primary key,
    employee_id varchar(32) comment '员工id',
    project_name varchar(32) comment '项目名称',
    is_save boolean comment '是否保存',
    start_time varchar(32) comment '开始时间',
    end_time varchar(32) comment '结束时间',
    duration decimal(8, 2) comment '工作时长'
);

