package com.nekoimi.vasashi.mapper;


import com.nekoimi.vasashi.framework.mybatis.BaseMapper;
import com.nekoimi.vasashi.entity.Torrent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Torrent Mapper 接口
 *
 * nekoimi  2022-04-29
 */
@Mapper
@Component
public interface TorrentMapper extends BaseMapper<Torrent> {
}
