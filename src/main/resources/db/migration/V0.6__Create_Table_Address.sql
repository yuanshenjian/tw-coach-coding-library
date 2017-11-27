CREATE TABLE `t_address` (
  `id`          VARCHAR(255) NOT NULL PRIMARY KEY,
  `description` VARCHAR(255) DEFAULT NULL,
  `user_id`     VARCHAR(255) DEFAULT NULL,
  KEY `FKib1tav6peo3hd297p0bw4qng9` (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
);