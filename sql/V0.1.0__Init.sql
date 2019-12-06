-- ----------------------------
-- Sqlite 不能添加注释
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `username` TEXT NOT NULL,
  `password` TEXT NOT NULL,
  `deleted` INTEGER DEFAULT 0,
  `create_time` TEXT NOT NULL,
  `update_time` TEXT NOT NULL
);

CREATE UNIQUE INDEX `user_idx_username` ON `user`(`username`);

-- ----------------------------
-- Table structure for js_error
-- ----------------------------
CREATE TABLE `js_error` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `href` TEXT NOT NULL,
  `user_agent` TEXT NOT NULL,
  `cookie` TEXT NOT NULL,
  `message` TEXT NOT NULL,
  `source` TEXT NOT NULL,
  `line` TEXT NOT NULL,
  `column` TEXT NOT NULL,
  `error` TEXT NOT NULL,
  `stack` TEXT NOT NULL,
  `time` TEXT NOT NULL,
  `create_time` TEXT NOT NULL,
  `update_time` TEXT NOT NULL
);
