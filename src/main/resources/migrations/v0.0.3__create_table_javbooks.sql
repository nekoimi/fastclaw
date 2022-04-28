CREATE TABLE IF NOT EXISTS javbooks
(
    id           varchar(32) primary key not null,
    created_at   timestamp               null,
    updated_at   timestamp               null,
    deleted      smallint                null,

    title        varchar(255)            null default null,
    movie_number varchar(255)            null default null,
    actress      varchar(255)            null default null,
    cover        varchar(255)            null default null,

    -- ext_table
    image_list   text                    null default null,
    magnet_link  text                    null default null,
    torrent_url  varchar(255)            null default null,
    torrent_path varchar(255)            null default null,
);

COMMENT ON TABLE javbooks IS 'Jav信息表';
COMMENT ON COLUMN javbooks.id IS '主键';
COMMENT ON COLUMN javbooks.created_at IS '创建时间';
COMMENT ON COLUMN javbooks.updated_at IS '最后更新时间';
COMMENT ON COLUMN javbooks.deleted IS '软删除字段；1 - 已删除，0 - 未删除';

COMMENT ON COLUMN javbooks.title IS '标题';
COMMENT ON COLUMN javbooks.movie_number IS '番号';
COMMENT ON COLUMN javbooks.actress IS '女优';
COMMENT ON COLUMN javbooks.cover IS '封面图';
COMMENT ON COLUMN javbooks.image_list IS '详细图片列表';
COMMENT ON COLUMN javbooks.magnet_link IS '磁力链接列表';
COMMENT ON COLUMN javbooks.torrent_url IS '种子文件远程路径';
COMMENT ON COLUMN javbooks.torrent_path IS '种子文件本地路径';