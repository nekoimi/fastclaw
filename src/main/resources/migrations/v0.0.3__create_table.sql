CREATE TABLE IF NOT EXISTS book_actress
(
    id         varchar(32) primary key not null,
    created_at timestamp               null,
    updated_at timestamp               null,
    deleted    smallint                null,

    name       varchar(255)            null default null,
    cover      varchar(255)            null default null
);

COMMENT ON TABLE book_actress IS '书籍作者信息';
COMMENT ON COLUMN book_actress.id IS '主键';
COMMENT ON COLUMN book_actress.created_at IS '创建时间';
COMMENT ON COLUMN book_actress.updated_at IS '最后更新时间';
COMMENT ON COLUMN book_actress.deleted IS '软删除字段；1 - 已删除，0 - 未删除';

COMMENT ON COLUMN book_actress.name IS '书籍作者名称';
COMMENT ON COLUMN book_actress.name IS '书籍作者封面';



CREATE TABLE IF NOT EXISTS book_series
(
    id         varchar(32) primary key not null,
    created_at timestamp               null,
    updated_at timestamp               null,
    deleted    smallint                null,

    title      varchar(255)            null default null
);

COMMENT ON TABLE book_series IS '书籍系列信息';
COMMENT ON COLUMN book_series.id IS '主键';
COMMENT ON COLUMN book_series.created_at IS '创建时间';
COMMENT ON COLUMN book_series.updated_at IS '最后更新时间';
COMMENT ON COLUMN book_series.deleted IS '软删除字段；1 - 已删除，0 - 未删除';

COMMENT ON COLUMN book_series.title IS '书籍系列名称';



CREATE TABLE IF NOT EXISTS book
(
    id            varchar(32) primary key not null,
    created_at    timestamp               null,
    updated_at    timestamp               null,
    deleted       smallint                null,

    series_id     varchar(32)             null default null,
    title         varchar(255)            null default null,
    movie_number  varchar(255)            null default null,
    actress_id    varchar(32)             null default null,
    cover_url     varchar(255)            null default null,
    cover_file_id varchar(32)             null default null,
    duration      varchar(32)             null default null,
    manufacturer  varchar(255)            null default null
);

COMMENT ON TABLE book IS '书籍信息';
COMMENT ON COLUMN book.id IS '主键';
COMMENT ON COLUMN book.created_at IS '创建时间';
COMMENT ON COLUMN book.updated_at IS '最后更新时间';
COMMENT ON COLUMN book.deleted IS '软删除字段；1 - 已删除，0 - 未删除';

COMMENT ON COLUMN book.series_id IS '书籍系列ID';
COMMENT ON COLUMN book.title IS '书籍标题';
COMMENT ON COLUMN book.movie_number IS '书籍编号';
COMMENT ON COLUMN book.actress_id IS '书籍作者ID';
COMMENT ON COLUMN book.cover_url IS '书籍封面图URL';
COMMENT ON COLUMN book.cover_file_id IS '书籍封面图ID';
COMMENT ON COLUMN book.duration IS '书籍时长';
COMMENT ON COLUMN book.manufacturer IS '书籍制造商';



CREATE TABLE IF NOT EXISTS book_image
(
    id         varchar(32) primary key not null,
    created_at timestamp               null,
    updated_at timestamp               null,
    deleted    smallint                null,

    book_id    varchar(32)             null default null,
    url        varchar(255)            null default null,
    file_id    varchar(32)             null default null
);

COMMENT ON TABLE book_image IS '书籍图片';
COMMENT ON COLUMN book_image.id IS '主键';
COMMENT ON COLUMN book_image.created_at IS '创建时间';
COMMENT ON COLUMN book_image.updated_at IS '最后更新时间';
COMMENT ON COLUMN book_image.deleted IS '软删除字段；1 - 已删除，0 - 未删除';

COMMENT ON COLUMN book_image.book_id IS '书籍ID';
COMMENT ON COLUMN book_image.url IS '原始地址';
COMMENT ON COLUMN book_image.file_id IS '文件ID';



CREATE TABLE IF NOT EXISTS book_magnet
(
    id         varchar(32) primary key not null,
    created_at timestamp               null,
    updated_at timestamp               null,
    deleted    smallint                null,

    book_id    varchar(32)             null default null,
    link       text                    null default null
);

COMMENT ON TABLE book_magnet IS '书籍磁力链接';
COMMENT ON COLUMN book_magnet.id IS '主键';
COMMENT ON COLUMN book_magnet.created_at IS '创建时间';
COMMENT ON COLUMN book_magnet.updated_at IS '最后更新时间';
COMMENT ON COLUMN book_magnet.deleted IS '软删除字段；1 - 已删除，0 - 未删除';

COMMENT ON COLUMN book_magnet.book_id IS '书籍ID';
COMMENT ON COLUMN book_magnet.link IS '磁力链接';



CREATE TABLE IF NOT EXISTS book_torrent
(
    id         varchar(32) primary key not null,
    created_at timestamp               null,
    updated_at timestamp               null,
    deleted    smallint                null,

    book_id    varchar(32)             null default null,
    url        varchar(255)            null default null,
    file_id    varchar(32)             null default null
);

COMMENT ON TABLE book_torrent IS '书籍种子文件';
COMMENT ON COLUMN book_torrent.id IS '主键';
COMMENT ON COLUMN book_torrent.created_at IS '创建时间';
COMMENT ON COLUMN book_torrent.updated_at IS '最后更新时间';
COMMENT ON COLUMN book_torrent.deleted IS '软删除字段；1 - 已删除，0 - 未删除';

COMMENT ON COLUMN book_torrent.book_id IS '书籍ID';
COMMENT ON COLUMN book_torrent.url IS '种子文件链接';
COMMENT ON COLUMN book_torrent.file_id IS '种子文件ID';



CREATE TABLE IF NOT EXISTS book_label
(
    id         varchar(32) primary key not null,
    created_at timestamp               null,
    updated_at timestamp               null,
    deleted    smallint                null,

    name       varchar(255)            null default null
);

COMMENT ON TABLE book_label IS '书籍标签信息';
COMMENT ON COLUMN book_label.id IS '主键';
COMMENT ON COLUMN book_label.created_at IS '创建时间';
COMMENT ON COLUMN book_label.updated_at IS '最后更新时间';
COMMENT ON COLUMN book_label.deleted IS '软删除字段；1 - 已删除，0 - 未删除';

COMMENT ON COLUMN book_label.name IS '书籍标签名称';



CREATE TABLE IF NOT EXISTS book_label_rel
(
    id         varchar(32) primary key not null,
    created_at timestamp               null,
    updated_at timestamp               null,
    deleted    smallint                null,

    book_id    varchar(32)             null default null,
    label_id   varchar(32)             null default null
);

COMMENT ON TABLE book_label_rel IS '书籍标签Rel';
COMMENT ON COLUMN book_label_rel.id IS '主键';
COMMENT ON COLUMN book_label_rel.created_at IS '创建时间';
COMMENT ON COLUMN book_label_rel.updated_at IS '最后更新时间';
COMMENT ON COLUMN book_label_rel.deleted IS '软删除字段；1 - 已删除，0 - 未删除';

COMMENT ON COLUMN book_label_rel.book_id IS '书籍ID';
COMMENT ON COLUMN book_label_rel.label_id IS '标签ID';
