CREATE TABLE `t_role_privilege` (
  `role_symbol`      VARCHAR(255) NOT NULL,
  `privilege_symbol` VARCHAR(255) NOT NULL,

  FOREIGN KEY (role_symbol) REFERENCES `t_role` (symbol),
  FOREIGN KEY (privilege_symbol) REFERENCES `t_privilege` (symbol)
);