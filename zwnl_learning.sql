create database if not exists zwnl_learning;
use zwnl_learning;
CREATE TABLE `sign_record` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `user_id` bigint NOT NULL COMMENT '用户id',
                               `year` year NOT NULL COMMENT '签到年份',
                               `month` tinyint NOT NULL COMMENT '签到月份',
                               `date` date NOT NULL COMMENT '签到日期',
                               `is_backup` bit(1) NOT NULL COMMENT '是否补签',
                               PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='签到记录表';
CREATE TABLE IF NOT EXISTS `points_record` (
                                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '积分记录表id',
                                               `user_id` bigint NOT NULL COMMENT '用户id',
                                               `type` tinyint NOT NULL COMMENT '积分方式：1-课程学习，2-每日签到，3-课程问答， 4-课程笔记，5-课程评价',
                                               `points` tinyint NOT NULL COMMENT '积分值',
                                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                               PRIMARY KEY (`id`) USING BTREE,
                                               KEY `idx_user_id` (`user_id`,`type`) USING BTREE,
                                               KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='学习积分记录，每个月底清零';

CREATE TABLE IF NOT EXISTS `points_board_season` (
                                                     `id` int NOT NULL AUTO_INCREMENT COMMENT '自增长id，season标示',
                                                     `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '赛季名称，例如：第1赛季',
                                                     `begin_time` date NOT NULL COMMENT '赛季开始时间',
                                                     `end_time` date NOT NULL COMMENT '赛季结束时间',
                                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
