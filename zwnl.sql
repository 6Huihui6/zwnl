create database zwnl ;
use zwnl;
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY comment '用户ID',             -- 用户ID (主键)
                       username VARCHAR(255) NOT NULL UNIQUE comment '用户名',              -- 用户名
                       email VARCHAR(255) NOT NULL UNIQUE comment '邮箱',                 -- 邮箱
                       password VARCHAR(255) NOT NULL comment '密码',                -- 密码
                       role ENUM('employer', 'seeker') NOT NULL comment '用户角色',           -- 用户角色 (雇主/求职者)
                       avatar_url VARCHAR(255) comment '用户头像',                            -- 用户头像URL
                       status ENUM('ACTIVE', 'INACTIVE', 'BANNED') DEFAULT 'ACTIVE' comment '用户状态', -- 用户状态
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '创建时间',     -- 创建时间
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间', -- 更新时间
                       last_login TIMESTAMP NULL comment '最后登录时间'                          -- 最后登录时间
) comment '用户表';
CREATE TABLE jobs (
                      job_id INT AUTO_INCREMENT PRIMARY KEY comment '职位ID',              -- 职位ID (主键)
                      title VARCHAR(255) NOT NULL comment '职位名称',                        -- 职位名称
                      description TEXT comment '职位描述',                                   -- 职位描述
                      requirements TEXT comment '职位要求',                                  -- 职位要求
                      location VARCHAR(255) comment '工作地点',                              -- 工作地点
                      min_salary DECIMAL(10, 2) comment '最低薪资',                          -- 最低薪资
                      max_salary DECIMAL(10, 2) comment '最高薪资',                          -- 最高薪资
                      company_id INT comment '公司ID',                                     -- 公司ID (外键，关联companies表)
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '创建时间',     -- 创建时间
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'-- 更新时间
) comment '职位表';
CREATE TABLE applications (
                              application_id INT AUTO_INCREMENT PRIMARY KEY comment '申请ID',      -- 申请ID (主键)
                              job_id INT comment '职位ID',                                         -- 职位ID (外键，关联jobs表)
                              seeker_id INT comment '求职者ID',                                      -- 求职者ID (外键，关联users表)
                              cover_letter TEXT comment '求职信内容',                                  -- 求职信内容
                              expected_salary DECIMAL(10, 2) comment '期望薪资',                     -- 期望薪资
                              available_start_date DATE comment '可到岗日期',                          -- 可到岗日期
                              status ENUM('applied', 'interview', 'hired', 'rejected') DEFAULT 'applied' comment '申请状态',  -- 申请状态
                              applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '申请时间'    -- 申请时间
) comment '申请表';
create database zwnl_resumes ;
use zwnl_resumes;
CREATE TABLE resumes (
                         resume_id INT AUTO_INCREMENT PRIMARY KEY comment '简历ID',            -- 简历ID (主键)
                         seeker_id INT comment '求职者ID',                                       -- 求职者ID (外键，关联users表)
                         title VARCHAR(255) NOT NULL comment '简历标题',                         -- 简历标题
                         summary TEXT comment '个人简介',                                        -- 个人简介
                         contact_info VARCHAR(255) comment '联系方式',                           -- 联系方式 (可包括邮箱、电话)
                         resume_file_url VARCHAR(255) comment '简历附件URL',                        -- 简历附件URL
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '创建时间',      -- 创建时间
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'-- 更新时间
) comment '简历表';
CREATE TABLE skills (
                        skill_id INT AUTO_INCREMENT PRIMARY KEY comment '技能ID',             -- 技能ID (主键)
                        resume_id INT comment '简历ID',                                       -- 简历ID (外键，关联resumes表)
                        skill_name VARCHAR(255) NOT NULL comment '技能名称',                    -- 技能名称
                        proficiency ENUM('beginner', 'intermediate', 'expert') comment '熟练程度'-- 技能熟练程度
) comment '技能表';
CREATE TABLE work_experiences (
                                  experience_id INT AUTO_INCREMENT PRIMARY KEY comment '工作经历ID',        -- 工作经历ID (主键)
                                  resume_id INT comment '简历ID',                                       -- 简历ID (外键，关联resumes表)
                                  company_name VARCHAR(255) NOT NULL comment '公司名称',                  -- 公司名称
                                  position VARCHAR(255) NOT NULL comment '职位名称',                      -- 职位名称
                                  start_date DATE comment '开始时间',                                     -- 开始时间
                                  end_date DATE comment '结束时间',                                       -- 结束时间 (如果是当前职位则为空)
                                  responsibilities TEXT comment '职责和业绩'                              -- 主要职责和成就
) comment '工作经历表';
CREATE TABLE educations (
                            education_id INT AUTO_INCREMENT PRIMARY KEY comment '教育背景ID',         -- 教育背景ID (主键)
                            resume_id INT comment '简历ID',                                       -- 简历ID (外键，关联resumes表)
                            institution_name VARCHAR(255) NOT NULL comment '学校或教育机构名称',              -- 学校或教育机构名称
                            degree VARCHAR(255) comment '学位',                                 -- 学位
                            major VARCHAR(255) comment '专业',                                  -- 专业
                            start_date DATE comment '开始时间',                                     -- 开始日期
                            end_date DATE comment '结束时间'                                       -- 结束日期
) comment '教育经历表';
CREATE TABLE posts (
                       post_id INT AUTO_INCREMENT PRIMARY KEY comment '帖子ID',          -- 帖子ID (主键)
                       user_id INT comment '用户ID',                                     -- 用户ID (外键，关联users表)
                       post_type ENUM('text', 'image', 'video') comment '帖子类型',        -- 帖子类型 (文本、图片、视频)
                       content TEXT comment '帖子内容',                                    -- 帖子内容
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '发帖时间',  -- 发帖时间
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间', -- 更新时间
                       is_deleted BOOLEAN DEFAULT FALSE comment '是否删除'                -- 帖子是否被删除
) comment '帖子表';
CREATE TABLE videos (
                        video_id INT AUTO_INCREMENT PRIMARY KEY comment '视频ID',         -- 视频ID (主键)
                        user_id INT comment '用户ID',                                     -- 用户ID (外键，关联users表)
                        video_url VARCHAR(255) NOT NULL comment '视频URL',                 -- 视频文件的URL
                        title VARCHAR(255) comment '视频标题',                              -- 视频标题
                        description TEXT comment '视频描述',                                -- 视频描述
                        uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '上传时间', -- 上传时间
                        is_deleted BOOLEAN DEFAULT FALSE comment '是否删除'                -- 视频是否被删除
) comment '视频表';
CREATE TABLE comments (
                          comment_id INT AUTO_INCREMENT PRIMARY KEY comment '评论ID',       -- 评论ID (主键)
                          user_id INT comment '用户ID',                                     -- 用户ID (外键，关联users表)
                          post_id INT comment '帖子ID',                                     -- 帖子ID (外键，关联posts表，适用于帖子评论)
                          video_id INT comment '视频ID',                                    -- 视频ID (外键，关联videos表，适用于视频评论)
                          content TEXT comment '评论内容',                                    -- 评论内容
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '评论时间',  -- 评论时间
                          is_deleted BOOLEAN DEFAULT FALSE comment '是否删除'               -- 评论是否被删除
) comment '评论表';
CREATE TABLE likes (
                       like_id INT AUTO_INCREMENT PRIMARY KEY comment '点赞ID',          -- 点赞ID (主键)
                       user_id INT comment '用户ID',                                     -- 用户ID (外键，关联users表)
                       post_id INT comment '帖子ID',                                     -- 帖子ID (外键，关联posts表，适用于帖子点赞)
                       video_id INT comment '视频ID',                                    -- 视频ID (外键，关联videos表，适用于视频点赞)
                       liked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '点赞时间'   -- 点赞时间
) comment '点赞表';
CREATE TABLE shares (
                        share_id INT AUTO_INCREMENT PRIMARY KEY comment '分享ID',         -- 分享ID (主键)
                        user_id INT comment '用户ID',                                     -- 用户ID (外键，关联users表)
                        post_id INT comment '帖子ID',                                     -- 帖子ID (外键，关联posts表，适用于帖子分享)
                        video_id INT comment '视频ID',                                    -- 视频ID (外键，关联videos表，适用于视频分享)
                        shared_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '分享时间'   -- 分享时间
) comment '分享表';
CREATE TABLE notifications (
                               notification_id INT AUTO_INCREMENT PRIMARY KEY comment '通知ID',   -- 通知ID (主键)
                               user_id INT comment '用户ID',                                      -- 用户ID (外键，关联users表)
                               type ENUM('job_posted', 'resume_viewed', 'message_received', 'application_status') comment '通知类型', -- 通知类型 (职位发布、简历被查看、收到消息、申请状态更新)
                               content TEXT comment '通知内容',                                     -- 通知内容
                               is_read BOOLEAN DEFAULT FALSE comment '通知是否已读',                    -- 通知是否已读
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '通知生成时间'  -- 通知生成时间
) comment '通知表';
CREATE TABLE follows (
                         follow_id INT AUTO_INCREMENT PRIMARY KEY comment '关注ID',  -- 关注ID (主键)
                         follower_id INT comment '关注者ID',                           -- 关注者ID (外键，关联users表)
                         followed_id INT comment '被关注者ID',                            -- 被关注者ID (可以是用户或公司)
                         followed_type ENUM('user', 'company') comment '被关注者类型',     -- 被关注者的类型 (用户或公司)
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '关注时间'  -- 关注时间
) comment '关注表';
CREATE TABLE user_activity_logs (
                                    log_id INT AUTO_INCREMENT PRIMARY KEY comment '日志ID',    -- 行为日志ID (主键)
                                    user_id INT comment '用户ID',                              -- 用户ID (外键，关联users表)
                                    activity_type ENUM('view_job', 'search', 'apply', 'like', 'comment', 'share') comment '行为类型', -- 用户行为类型
                                    activity_data TEXT comment '行为数据',                       -- 行为相关的数据 (例如职位ID、搜索关键词等)
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '行为发生时间'  -- 行为发生时间
) comment '用户行为日志表';
CREATE TABLE recommendations (
                                 recommendation_id INT AUTO_INCREMENT PRIMARY KEY comment '推荐ID',  -- 推荐ID (主键)
                                 user_id INT comment '用户ID',                                       -- 用户ID (外键，关联users表)
                                 content_type ENUM('job', 'post', 'video') comment '推荐内容类型',         -- 推荐内容的类型 (职位、帖子或视频)
                                 content_id INT comment '推荐内容ID',                                    -- 推荐内容的ID (职位ID、帖子ID或视频ID)
                                 reason TEXT comment '推荐理由',                                       -- 推荐理由 (例如"基于浏览历史"、"与您的技能匹配")
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '推荐生成时间'   -- 推荐生成时间
) comment '推荐表';