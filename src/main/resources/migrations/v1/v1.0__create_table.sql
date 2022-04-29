CREATE TABLE IF NOT EXISTS sys_file_info
(
    id            varchar(32) primary key not null,
    created_at    timestamp                    default current_timestamp null,
    updated_at    timestamp                    default current_timestamp null,
    deleted       smallint                null default 0,

    filename      varchar(255)            null default null,
    file_size     bigint                  null default null,
    mime_type     varchar(255)            null default null,
    relative_path varchar(255)            null default null
);

COMMENT ON TABLE sys_file_info IS '文件信息';
COMMENT ON COLUMN sys_file_info.id IS '主键';
COMMENT ON COLUMN sys_file_info.created_at IS '创建时间';
COMMENT ON COLUMN sys_file_info.updated_at IS '最后更新时间';
COMMENT ON COLUMN sys_file_info.deleted IS '软删除字段；1 - 已删除，0 - 未删除';

COMMENT ON COLUMN sys_file_info.filename IS '文件名称';
COMMENT ON COLUMN sys_file_info.file_size IS '文件大小';
COMMENT ON COLUMN sys_file_info.mime_type IS '文件类型';
COMMENT ON COLUMN sys_file_info.relative_path IS '相对路径';



CREATE TABLE IF NOT EXISTS sys_user
(
    id         varchar(32) primary key not null,
    created_at timestamp               null,
    updated_at timestamp               null,
    deleted    smallint                null,

    username   varchar(255)            null default null,
    password   varchar(255)            null default null,
    enable     smallint                null default 1
);

COMMENT ON TABLE sys_user IS '账号信息';
COMMENT ON COLUMN sys_user.id IS '主键';
COMMENT ON COLUMN sys_user.created_at IS '创建时间';
COMMENT ON COLUMN sys_user.updated_at IS '最后更新时间';
COMMENT ON COLUMN sys_user.deleted IS '软删除字段；1 - 已删除，0 - 未删除';

COMMENT ON COLUMN sys_user.username IS '账号';
COMMENT ON COLUMN sys_user.password IS '密码';
COMMENT ON COLUMN sys_user.enable IS '是否被禁用：0 - 禁用，1 - 正常';



CREATE TABLE IF NOT EXISTS sys_user_info
(
    id         varchar(32) primary key not null,
    created_at timestamp               null,
    updated_at timestamp               null,
    deleted    smallint                null,

    nickname   varchar(255)            null default null,
    avatar     varchar(255)            null default null
);

COMMENT ON TABLE sys_user_info IS '用户信息';
COMMENT ON COLUMN sys_user_info.id IS '主键';
COMMENT ON COLUMN sys_user_info.created_at IS '创建时间';
COMMENT ON COLUMN sys_user_info.updated_at IS '最后更新时间';
COMMENT ON COLUMN sys_user_info.deleted IS '软删除字段；1 - 已删除，0 - 未删除';

COMMENT ON COLUMN sys_user_info.nickname IS '昵称';
COMMENT ON COLUMN sys_user_info.avatar IS '头像';
