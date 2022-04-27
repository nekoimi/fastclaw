package com.nekoimi.vasashi.framework.webmagic;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.nekoimi.vasashi.framework.utils.JsonUtils;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.MonitorableScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * <p>RedisScheduler</p>
 *
 * @author nekoimi 2022/4/27
 * @see us.codecraft.webmagic.scheduler.RedisScheduler
 */
public class RedisPoolDuplicateScheduler extends DuplicateRemovedScheduler implements MonitorableScheduler, DuplicateRemover {
    private final static String QUEUE_KEY = "webmagic-scheduler-queue";
    private final static String SET_KEY = "webmagic-scheduler-set";
    private final static String ITEM_KEY = "webmagic-scheduler-item";
    private final RedisConnectionFactory connectionFactory;

    public RedisPoolDuplicateScheduler(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        setDuplicateRemover(this);
    }

    /**
     * <p>Static create</p>
     *
     * @param connectionFactory Redis connectionFactory
     * @return
     */
    public static Scheduler of(RedisConnectionFactory connectionFactory) {
        return new RedisPoolDuplicateScheduler(connectionFactory);
    }

    @Override
    public synchronized Request poll(Task task) {
        String url = bytesValue(connection().listCommands().lPop(createQueueKey(task)));
        if (StrUtil.isEmpty(url)) {
            return null;
        }
        String shaHex = DigestUtil.sha256Hex(url);
        byte[] cacheBytes = connection().hashCommands().hGet(createItemKey(task), valueBytes(shaHex));
        if (cacheBytes != null) {
            Request request = JsonUtils.readBytes(cacheBytes, Request.class);
            if (request != null) {
                return request;
            }
        }
        return new Request(url);
    }

    @Override
    protected void pushWhenNoDuplicate(Request request, Task task) {
        String url = request.getUrl();
        connection().listCommands().rPush(createQueueKey(task), valueBytes(url));
        if (isNotEmptyRequest(request)) {
            String shaHex = DigestUtil.sha256Hex(url);
            byte[] requestBytes = JsonUtils.writeBytes(request);
            if (requestBytes != null) {
                connection().hashCommands().hSet(createItemKey(task), valueBytes(shaHex), requestBytes);
            }
        }
    }

    @Override
    public boolean isDuplicate(Request request, Task task) {
        return Optional.ofNullable(connection().setCommands().sAdd(createSetKey(task), valueBytes(request.getUrl()))).orElse(0L).intValue() == 0;
    }

    @Override
    public void resetDuplicateCheck(Task task) {
        connection().del(createSetKey(task));
    }

    @Override
    public int getLeftRequestsCount(Task task) {
        return Optional.ofNullable(connection().listCommands().lLen(createQueueKey(task))).orElse(0L).intValue();
    }

    @Override
    public int getTotalRequestsCount(Task task) {
        return Optional.ofNullable(connection().setCommands().sCard(createSetKey(task))).orElse(0L).intValue();
    }

    /**
     * <p>Get connection</p>
     *
     * @return
     */
    private RedisConnection connection() {
        return connectionFactory.getConnection();
    }

    /**
     * <p>是否是非空请求</p>
     *
     * @param request
     * @return
     */
    public boolean isNotEmptyRequest(Request request) {
        if (request == null) {
            return false;
        }

        if (!request.getHeaders().isEmpty() || !request.getCookies().isEmpty()) {
            return true;
        }

        if (StrUtil.isNotBlank(request.getCharset()) || StrUtil.isNotBlank(request.getMethod())) {
            return true;
        }

        if (request.isBinaryContent() || request.getRequestBody() != null) {
            return true;
        }

        if (request.getExtras() != null && !request.getExtras().isEmpty()) {
            return true;
        }

        return request.getPriority() != 0L;
    }

    /**
     * <p>queueKey</p>
     *
     * @param task
     * @return
     */
    private byte[] createQueueKey(Task task) {
        String key = QUEUE_KEY + ":" + task.getUUID();
        return key.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * <p>setKey</p>
     *
     * @param task
     * @return
     */
    private byte[] createSetKey(Task task) {
        String key = SET_KEY + ":" + task.getUUID();
        return key.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * <p>itemKey</p>
     *
     * @param task
     * @return
     */
    private byte[] createItemKey(Task task) {
        String key = ITEM_KEY + ":" + task.getUUID();
        return key.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * <p>toBytes</p>
     *
     * @param value
     * @return
     */
    private byte[] valueBytes(String value) {
        return value == null ? new byte[0] : value.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * <p>toValue</p>
     *
     * @param bytes
     * @return
     */
    private String bytesValue(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
