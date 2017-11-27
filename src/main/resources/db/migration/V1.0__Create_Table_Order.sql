CREATE TABLE `t_order` (
  `id`       VARCHAR(255) NOT NULL PRIMARY KEY,
  `price`    DOUBLE       NOT NULL,
  `user_id` VARCHAR(255) NOT NULL
);