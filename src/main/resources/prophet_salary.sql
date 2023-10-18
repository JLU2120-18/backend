create database prophet_salary;

use prophet_salary;

create table auth
(
    id       varchar(32) primary key,
    username varchar(32) comment '用户名',
    password varchar(64) comment '密码',
    role     varchar(16) comment '角色: employee-普通员工    commission-委托员工    payroll-管理员'
);

insert into `prophet_salary`.`auth` (`id`, `username`, `password`, `role`)
values ('aotuo1', '奥托', 'e10adc3949ba59abbe56e057f20f883e', 'employee'),
       ('dingzhen2', '顶真', '5b1b68a9abf4d2cd155c81a9225fd158', 'commission'),
       ('daoge3', '刀哥', '8e6754dec9cdb8a211de0e4fb8aea903', 'employee'),
       ('manguanzhi4', '满广志', 'e10adc3949ba59abbe56e057f20f883e', 'employee'),
       ('diangun5', '电棍', 'e10adc3949ba59abbe56e057f20f883e', 'employee'),
       ('boerbute6', '波尔布特', 'e10adc3949ba59abbe56e057f20f883e', 'commission'),
       ('shoushangqiangou7', '手上牵狗', 'e10adc3949ba59abbe56e057f20f883e', 'commission'),
       ('admin8', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'payroll');



create table user
(
    id              varchar(32) primary key,
    username        varchar(32) comment '用户名',
    address         varchar(256) comment '通讯地址',
    socsec_id       varchar(32) comment '社保',
    tax_rate        decimal(10, 2) comment '税收',
    other_cast      decimal(10, 2) comment '其他扣除',
    phone           varchar(11) comment '电话',
    hour_wage       decimal(10, 2) comment '时薪',
    salary          decimal(10, 2) comment '薪资',
    commission_rate decimal(10, 2) comment '佣金率',
    duration_limit  int comment '工作时间限制',
    type            varchar(16) comment '员工类型: salary-工资制   commission-委托制    wage-时薪制',
    payment         varchar(16) comment '支付类型: mail-邮寄    receive-领取    bank-银行',
    mail_address    varchar(32) comment '邮箱地址',
    bank_name       varchar(16) comment '银行',
    bank_account    varchar(16) comment '银行账号'
);

insert into `prophet_salary`.`user` (`id`, `username`, `address`, `socsec_id`, `tax_rate`, `other_cast`, `phone`,
                                     `hour_wage`, `salary`, `commission_rate`, `duration_limit`, `type`, `payment`,
                                     `mail_address`, `bank_name`, `bank_account`)
values ('aotuo1', '奥托', '辽宁省鞍山市', 'otto11111', '0.2', '300', '13336336666', '10', '5000', '0.1', '1', 'salary',
        'bank', 'otto12306@douyu.com', '中国银行', 'ottootto'),
       ('dingzhen2', '顶真', '四川省理塘市', 'dingzhen', '0.1', '400', '14639566685', '10', '3000', '0.1', '0',
        'commission', 'mail', 'dingzhen@gmail.com', '中国银行', 'dingzhen'),
       ('daoge3', '刀哥', '辽宁省沈阳市', 'daoge11111', '0.15', '320', '15966584895', '8', '4000', '0.2', '1', 'wage',
        'receive', 'fwdao@163.com', '交通银行', 'daoge'),
       ('manguanzhi4', '满广志', '山东省青岛市', 'guangzhi11111', '0.08', '500', '17264828084', '8', '3000', '0.3', '1',
        'salary', 'mail', 'guangzhi@163.com', '工商银行', 'guangzhi'),
       ('diangun5', '电棍', '浙江省宁波市', 'diangun11111', '0.2', '500', '19280806523', '8', '3000', '0.21', '0',
        'salary', 'bank', 'diangun@163.com', '工商银行', 'diangun4444'),
       ('boerbute6', '波尔布特', '四川省成都市', 'boerbute11111', '0.2', '500', '19265658584', '8', '3000', '0.07', '1',
        'commission', 'receive', 'boerbute@qq.com', '工商银行', 'boerbute'),
       ('shoushangqiangou7', '手上牵狗', '河北省石家庄市', 'shoushangqiangou11111', '0.2', '500', '17756589942', '8',
        '3000', '0.14', '1', 'commission', 'bank', 'shoushangqiangou@qq.com', '工商银行', 'shoushangqiangou');



create table purchase_order
(
    id           varchar(32) primary key,
    employee_id  varchar(32) comment '员工id',
    product_name varchar(32) comment '产品名称',
    pay          decimal(10, 2) comment '支付金额',
    phone        varchar(11) comment '电话',
    address      varchar(256) comment '地址',
    date         varchar(32) comment '日期'
);

insert into `prophet_salary`.`purchase_order` (`id`, `employee_id`, `product_name`, `pay`, `phone`, `address`, `date`)
values ('1', 'aotuo1', '槟榔', '800', '13336336666', '辽宁省鞍山市', '2023-04-07 13:21:55'),
       ('2', 'dingzhen2', '服装', '1800', '14639566685', '四川省理塘市', '2023-04-07 15:21:55'),
       ('3', 'daoge3', '打火机', '310', '15966584895', '辽宁省沈阳市', '2023-04-13 12:23:55'),
       ('4', 'boerbute6', '猫头鹰', '6000', '17264828084', '四川省成都市', '2023-04-12 10:21:55'),
       ('5', 'diangun5', '口罩', '1000', '19280806523', '浙江省宁波市', '2023-04-12 10:52:55'),
       ('6', 'manguanzhi4', '96-A', '6000.1', '17264828084', '山东省青岛市', '2023-04-10 10:37:40'),
       ('7', 'shoushangqiangou7', '狗', '2000', '17756589942', '河北省石家庄市', '2023-04-12 9:21:55');



create table time_card
(
    id          varchar(32) primary key,
    employee_id varchar(32) comment '员工id',
    duration    decimal(8, 2) comment '工作时长',
    is_save     boolean comment '是否保存',
    start_time  varchar(32) comment '开始时间',
    end_time    varchar(32) comment '结束时间'
);

insert into `prophet_salary`.`time_card` (`id`, `employee_id`, `duration`, `is_save`, `start_time`, `end_time`)
values ('1', 'aotuo1', '10', '1', '2023-04-07 10:21:55', '2023-04-08 10:21:55'),
       ('2', 'dingzhen2', '8', '0', '2023-04-07 10:21:55', '2023-04-08 10:21:55'),
       ('3', 'daoge3', '8', '0', '2023-04-12 16:21:55', '2023-04-13 11:21:55'),
       ('4', 'boerbute6', '8.5', '1', '2023-04-12 14:21:55', '2023-04-14 17:21:55'),
       ('5', 'diangun5', '8', '1', '2023-04-12 10:21:55', '2023-04-17 10:21:50'),
       ('6', 'manguanzhi4', '9', '1', '2023-04-11 09:21:55', '2023-04-14 10:21:55'),
       ('7', 'shoushangqiangou7', '10', '1', '2023-04-12 13:41:55', '2023-04-14 10:57:55'),
       ('8', 'aotuo1', '8', '0', '2023-04-09 13:41:55', '2023-04-11 10:57:55'),
       ('9', 'dingzhen2', '8', '0', '2023-04-12 17:41:55', '2023-04-14 10:55:15');



create table time_card_project
(
    id           varchar(32) primary key,
    project_name varchar(32) comment '项目名称',
    duration     decimal(8, 2) comment '工作时长'
);

insert into `prophet_salary`.`time_card_project` (`id`, `project_name`, `duration`)
values ('1', '顶真的世界', '100'),
       ('2', '口味王槟榔', '200'),
       ('3', '锐克五代', '150'),
       ('4', 'gaijin', '300'),
       ('5', '吉大微服务', '100'),
       ('6', '智慧餐厅', '100'),
       ('7', '致远服务', '50');