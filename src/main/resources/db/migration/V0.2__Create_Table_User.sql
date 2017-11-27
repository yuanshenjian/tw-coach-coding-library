CREATE TABLE `t_user` (
  `id`              VARCHAR(255) NOT NULL PRIMARY KEY,
  `name`            VARCHAR(255) NOT NULL UNIQUE KEY,
  `password`        VARCHAR(255) NOT NULL,
  `default_address` VARCHAR(255)          DEFAULT NULL,
  `role`            VARCHAR(255) NOT NULL,
  `age`             INT(11)      NOT NULL DEFAULT 0,
  FOREIGN KEY (`role`) REFERENCES `t_role` (`symbol`)
);