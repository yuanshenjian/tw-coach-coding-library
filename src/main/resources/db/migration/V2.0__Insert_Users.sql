INSERT INTO `t_role` (`symbol`, `name`) VALUES ('SYSTEM_ADMIN', '系统管理员');
INSERT INTO `t_role` (`symbol`, `name`) VALUES ('PROJECT_MANAGER', '项目经理');

INSERT INTO `t_user` (`id`, `name`, `password`, role) VALUES
  ('40b2dd57ca074dc0bd757c3e36fb2ffd', 'admin',
   '$2a$10$W3rO9JXdqvH01bYK/QRYCO7VYNsPDI2tK.4zzmAarYYlE1fLoXxfe', 'SYSTEM_ADMIN');

INSERT INTO `t_user` (`id`, `name`, `password`, role) VALUES
  ('40b2dd57ca074dc0bdjk8c3e36fb2ffd', 'pm2017',
   '$2a$10$W3rO9JXdqvH01bYK/QRYCO7VYNsPDI2tK.4zzmAarYYlE1fLoXxfe', 'PROJECT_MANAGER');


INSERT INTO `t_privilege` (`symbol`, `name`) VALUES ('CREATE_USER', '创建用户');
INSERT INTO `t_privilege` (`symbol`, `name`) VALUES ('UPDATE_USER', '更新用户');
INSERT INTO `t_privilege` (`symbol`, `name`) VALUES ('RETRIEVE_USER', '检索用户');
INSERT INTO `t_privilege` (`symbol`, `name`) VALUES ('DELETE_USER', '删除用户');
INSERT INTO `t_privilege` (`symbol`, `name`) VALUES ('RETRIEVE_ORDER', '检索订单');


INSERT INTO `t_role_privilege` (`role_symbol`, `privilege_symbol`) VALUES ('SYSTEM_ADMIN', 'CREATE_USER');
INSERT INTO `t_role_privilege` (`role_symbol`, `privilege_symbol`) VALUES ('SYSTEM_ADMIN', 'UPDATE_USER');
INSERT INTO `t_role_privilege` (`role_symbol`, `privilege_symbol`) VALUES ('SYSTEM_ADMIN', 'RETRIEVE_USER');
INSERT INTO `t_role_privilege` (`role_symbol`, `privilege_symbol`) VALUES ('SYSTEM_ADMIN', 'DELETE_USER');
INSERT INTO `t_role_privilege` (`role_symbol`, `privilege_symbol`) VALUES ('SYSTEM_ADMIN', 'RETRIEVE_ORDER');
