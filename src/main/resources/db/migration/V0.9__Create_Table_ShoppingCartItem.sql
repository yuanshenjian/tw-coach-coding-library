CREATE TABLE `t_shopping_cart_item` (
  `id`       VARCHAR(255) NOT NULL PRIMARY KEY,
  `user_id`  VARCHAR(255) NOT NULL,
  `item_id`  VARCHAR(255) NOT NULL,
  `quantity` BIGINT(20) DEFAULT 0,
  FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  FOREIGN KEY (`item_id`) REFERENCES `t_item` (`id`)
);