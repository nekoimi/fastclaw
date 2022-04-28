CREATE TABLE IF NOT EXISTS book
(
    id            varchar(32) primary key not null,
    created_at    timestamp               null,
    updated_at    timestamp               null,
    deleted       smallint                null,

    title         varchar(255)            null default null,
    movie_number  varchar(255)            null default null,
    actress       varchar(255)            null default null,
    cover_file_id varchar(255)            null default null
);

COMMENT ON TABLE book IS '书籍信息表';
COMMENT ON COLUMN book.id IS '主键';
COMMENT ON COLUMN book.created_at IS '创建时间';
COMMENT ON COLUMN book.updated_at IS '最后更新时间';
COMMENT ON COLUMN book.deleted IS '软删除字段；1 - 已删除，0 - 未删除';

COMMENT ON COLUMN book.title IS '书籍标题';
COMMENT ON COLUMN book.movie_number IS '书籍编号';
COMMENT ON COLUMN book.actress IS '书籍作者';
COMMENT ON COLUMN book.cover_file_id IS '书籍封面图ID';



CREATE TABLE IF NOT EXISTS book_image
(
    id         varchar(32) primary key not null,
    created_at timestamp               null,
    updated_at timestamp               null,
    deleted    smallint                null,

    book_id    varchar(255)            null default null,
    url        varchar(255)            null default null,
    file_id    varchar(255)            null default null
);

COMMENT ON TABLE book_image IS '书籍图片表';
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

    book_id    varchar(255)            null default null,
    link       varchar(255)            null default null,
    file_id    varchar(255)            null default null
);

-- COMMENT ON COLUMN books.image_list IS '详细图片列表';
-- COMMENT ON COLUMN books.magnet_link IS '磁力链接列表';
-- COMMENT ON COLUMN books.torrent_url IS '种子文件远程路径';
-- COMMENT ON COLUMN books.torrent_path IS '种子文件本地路径';
