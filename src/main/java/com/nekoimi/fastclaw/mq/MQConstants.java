package com.nekoimi.fastclaw.mq;

/**
 * <p>MQConstants</p>
 *
 * @author nekoimi 2022/4/29
 */
public interface MQConstants {
    String EXCHANGE = "fastclaw-exchange";
    String DOWNLOAD_IMAGE_QUEUE = "fastclaw.download.image";
    String DOWNLOAD_TORRENT_QUEUE = "fastclaw.download.torrent";
    String DOWNLOAD_IMAGE_ROUTE_KEY = "download.image";
    String DOWNLOAD_TORRENT_ROUTE_KEY = "download.torrent";
}
