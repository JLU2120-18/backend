create database prophet_salary;

use prophet_salary;

create table auth
(
    id       varchar(32) primary key,
    username varchar(32) comment '用户名',
    password varchar(64) comment '密码',
    role     varchar(16) comment '角色: employee-普通员工    commission-委托员工    payroll-管理员'
);

INSERT INTO `prophet_salary`.`auth` (`id`, `username`, `password`, `role`)
VALUES ('aotuo1', '奥托', '123456', 'employee');
INSERT INTO `prophet_salary`.`auth` (`id`, `username`, `password`, `role`)
VALUES ('dingzhen2', '顶真', '555555', 'commission');
INSERT INTO `prophet_salary`.`auth` (`id`, `username`, `password`, `role`)
VALUES ('daoge3', '刀哥', 'daoge', 'employee');
INSERT INTO `prophet_salary`.`auth` (`id`, `username`, `password`, `role`)
VALUES ('manguanzhi4', '满广志', '123456', 'employee');
INSERT INTO `prophet_salary`.`auth` (`id`, `username`, `password`, `role`)
VALUES ('diangun5', '电棍', '123456', 'employee');
INSERT INTO `prophet_salary`.`auth` (`id`, `username`, `password`, `role`)
VALUES ('boerbute6', '波尔布特', '123456', 'commission');
INSERT INTO `prophet_salary`.`auth` (`id`, `username`, `password`, `role`)
VALUES ('shoushangqiangou7', '手上牵狗', '123456', 'commission');
INSERT INTO `prophet_salary`.`auth` (`id`, `username`, `password`, `role`)
VALUES ('admin8', 'admin', '123456', 'payroll');

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

INSERT INTO `prophet_salary`.`user` (`id`, `username`, `address`, `socsec_id`, `tax_rate`, `other_cast`, `phone`,
                                     `hour_wage`, `salary`, `commission_rate`, `duration_limit`, `type`, `payment`,
                                     `mail_address`, `bank_name`, `bank_account`)
VALUES ('aotuo1', '奥托', '辽宁省鞍山市', 'otto11111', '0.2', '300', '13336336666', '20', '5000', '0.1', '1', 'salary',
        'bank', 'otto12306@douyu.com', '中国银行', 'ottootto');
INSERT INTO `prophet_salary`.`user` (`id`, `username`, `address`, `socsec_id`, `tax_rate`, `other_cast`, `phone`,
                                     `hour_wage`, `salary`, `commission_rate`, `duration_limit`, `type`, `payment`,
                                     `mail_address`, `bank_name`, `bank_account`)
VALUES ('dingzhen2', '顶真', '四川省理塘市', 'dingzhen', '0.1', '400', '14639566685', '20', '3000', '0.1', '0',
        'commission', 'mail', 'dingzhen@gmail.com', '中国银行', 'dingzhen');
INSERT INTO `prophet_salary`.`user` (`id`, `username`, `address`, `socsec_id`, `tax_rate`, `other_cast`, `phone`,
                                     `hour_wage`, `salary`, `commission_rate`, `duration_limit`, `type`, `payment`,
                                     `mail_address`, `bank_name`, `bank_account`)
VALUES ('daoge3', '刀哥', '辽宁省沈阳市', 'daoge11111', '0.15', '320', '15966584895', '21', '4000', '0.2', '1', 'wage',
        'receive', 'fwdao@163.com', '交通银行', 'daoge');
INSERT INTO `prophet_salary`.`user` (`id`, `username`, `address`, `socsec_id`, `tax_rate`, `other_cast`, `phone`,
                                     `hour_wage`, `salary`, `commission_rate`, `duration_limit`, `type`, `payment`,
                                     `mail_address`, `bank_name`, `bank_account`)
VALUES ('manguanzhi4', '满广志', '山东省青岛市', 'guangzhi11111', '0.08', '500', '17264828084', '18', '3000', '0.3',
        '1', 'salary', 'mail', 'guangzhi@163.com', '工商银行', 'guangzhi');
INSERT INTO `prophet_salary`.`user` (`id`, `username`, `address`, `socsec_id`, `tax_rate`, `other_cast`, `phone`,
                                     `hour_wage`, `salary`, `commission_rate`, `duration_limit`, `type`, `payment`,
                                     `mail_address`, `bank_name`, `bank_account`)
VALUES ('diangun5', '电棍', '浙江省宁波市', 'diangun11111', '0.2', '500', '19280806523', '20', '3000', '0.21', '0',
        'salary', 'bank', 'diangun@163.com', '工商银行', 'diangun4444');
INSERT INTO `prophet_salary`.`user` (`id`, `username`, `address`, `socsec_id`, `tax_rate`, `other_cast`, `phone`,
                                     `hour_wage`, `salary`, `commission_rate`, `duration_limit`, `type`, `payment`,
                                     `mail_address`, `bank_name`, `bank_account`)
VALUES ('boerbute6', '波尔布特', '四川省成都市', 'boerbute11111', '0.2', '500', '19265658584', '18', '3000', '0.07',
        '1', 'commission', 'receive', 'boerbute@qq.com', '工商银行', 'boerbute');
INSERT INTO `prophet_salary`.`user` (`id`, `username`, `address`, `socsec_id`, `tax_rate`, `other_cast`, `phone`,
                                     `hour_wage`, `salary`, `commission_rate`, `duration_limit`, `type`, `payment`,
                                     `mail_address`, `bank_name`, `bank_account`)
VALUES ('shoushangqiangou7', '手上牵狗', '河北省石家庄市', 'shoushangqiangou11111', '0.2', '500', '17756589942', '18',
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

INSERT INTO `prophet_salary`.`purchase_order` (`id`, `employee_id`, `product_name`, `pay`, `phone`, `address`, `date`)
VALUES ('1', 'aotuo1', '槟榔', '800', '13336336666', '辽宁省鞍山市', '2023-04-07 13:21:55');
INSERT INTO `prophet_salary`.`purchase_order` (`id`, `employee_id`, `product_name`, `pay`, `phone`, `address`, `date`)
VALUES ('2', 'dingzhen2', '服装', '1800', '14639566685', '四川省理塘市', '2023-04-07 15:21:55');
INSERT INTO `prophet_salary`.`purchase_order` (`id`, `employee_id`, `product_name`, `pay`, `phone`, `address`, `date`)
VALUES ('3', 'daoge3', '打火机', '310', '15966584895', '辽宁省沈阳市', '2023-04-13 12:23:55');
INSERT INTO `prophet_salary`.`purchase_order` (`id`, `employee_id`, `product_name`, `pay`, `phone`, `address`, `date`)
VALUES ('4', 'boerbute6', '猫头鹰', '6000', '17264828084', '四川省成都市', '2023-04-12 10:21:55');
INSERT INTO `prophet_salary`.`purchase_order` (`id`, `employee_id`, `product_name`, `pay`, `phone`, `address`, `date`)
VALUES ('5', 'diangun5', '口罩', '1000', '19280806523', '浙江省宁波市', '2023-04-12 10:52:55');
INSERT INTO `prophet_salary`.`purchase_order` (`id`, `employee_id`, `product_name`, `pay`, `phone`, `address`, `date`)
VALUES ('6', 'manguanzhi4', '96-A', '6000.1', '17264828084', '山东省青岛市', '2023-04-10 10:37:40');
INSERT INTO `prophet_salary`.`purchase_order` (`id`, `employee_id`, `product_name`, `pay`, `phone`, `address`, `date`)
VALUES ('7', 'shoushangqiangou7', '狗', '2000', '17756589942', '河北省石家庄市', '2023-04-12 9:21:55');

create table time_card
(
    id          varchar(32) primary key,
    employee_id varchar(32) comment '员工id',
    duration    decimal(8, 2) comment '工作时长',
    is_save     boolean comment '是否保存',
    start_time  varchar(32) comment '开始时间',
    end_time    varchar(32) comment '结束时间'
);

INSERT INTO `prophet_salary`.`time_card` (`id`, `employee_id`, `duration`, `is_save`, `start_time`, `end_time`)
VALUES ('1', 'aotuo1', '10', '1', '2023-04-07 10:21:55', '2023-04-08 10:21:55');
INSERT INTO `prophet_salary`.`time_card` (`id`, `employee_id`, `duration`, `is_save`, `start_time`, `end_time`)
VALUES ('2', 'dingzhen2','20', '0', '2023-04-07 10:21:55', '2023-04-08 10:21:55');
INSERT INTO `prophet_salary`.`time_card` (`id`, `employee_id`, `duration`, `is_save`, `start_time`, `end_time`)
VALUES ('3', 'daoge3', '30', '0', '2023-04-12 16:21:55', '2023-04-13 11:21:55');
INSERT INTO `prophet_salary`.`time_card` (`id`, `employee_id`, `duration`, `is_save`, `start_time`, `end_time`)
VALUES ('4', 'boerbute6', '25.5', '1', '2023-04-12 14:21:55', '2023-04-14 17:21:55');
INSERT INTO `prophet_salary`.`time_card` (`id`, `employee_id`, `duration`, `is_save`, `start_time`, `end_time`)
VALUES ('5', 'diangun5', '60', '1', '2023-04-12 10:21:55', '2023-04-17 10:21:50');
INSERT INTO `prophet_salary`.`time_card` (`id`, `employee_id`, `duration`, `is_save`, `start_time`, `end_time`)
VALUES ('6', 'manguanzhi4', '15.6', '1', '2023-04-11 09:21:55', '2023-04-14 10:21:55');
INSERT INTO `prophet_salary`.`time_card` (`id`, `employee_id`, `duration`, `is_save`, `start_time`, `end_time`)
VALUES ('7', 'shoushangqiangou7', '10', '1', '2023-04-12 13:41:55', '2023-04-14 10:57:55');
INSERT INTO `prophet_salary`.`time_card` (`id`, `employee_id`, `duration`, `is_save`, `start_time`, `end_time`)
VALUES ('8', 'aotuo1', '40', '0', '2023-04-09 13:41:55', '2023-04-11 10:57:55');
INSERT INTO `prophet_salary`.`time_card` (`id`, `employee_id`, `duration`, `is_save`, `start_time`, `end_time`)
VALUES ('9', 'dingzhen2', '35', '0', '2023-04-12 17:41:55', '2023-04-14 10:55:15');

create table time_card_project
(
    id           varchar(32) primary key,
    project_name varchar(32) comment '项目名称',
    duration     decimal(8, 2) comment '工作时长'
);

INSERT INTO `prophet_salary`.`time_card_project` (`id`, `project_name`, `duration`)
VALUES ('1', '顶真的世界', '100');
INSERT INTO `prophet_salary`.`time_card_project` (`id`, `project_name`, `duration`)
VALUES ('2', '口味王槟榔', '200');
INSERT INTO `prophet_salary`.`time_card_project` (`id`, `project_name`, `duration`)
VALUES ('3', '锐克五代', '150');
INSERT INTO `prophet_salary`.`time_card_project` (`id`, `project_name`, `duration`)
VALUES ('4', 'gaijin', '300');
INSERT INTO `prophet_salary`.`time_card_project` (`id`, `project_name`, `duration`)
VALUES ('5', '吉大微服务', '100');
INSERT INTO `prophet_salary`.`time_card_project` (`id`, `project_name`, `duration`)
VALUES ('6', '智慧餐厅', '100');
INSERT INTO `prophet_salary`.`time_card_project` (`id`, `project_name`, `duration`)
VALUES ('7', '致远服务', '50');