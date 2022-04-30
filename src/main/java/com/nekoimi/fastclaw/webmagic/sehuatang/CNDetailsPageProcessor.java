package com.nekoimi.fastclaw.webmagic.sehuatang;

import cn.hutool.core.util.StrUtil;
import com.nekoimi.fastclaw.framework.holder.ContextHolder;
import com.nekoimi.fastclaw.framework.utils.LocalDateTimeUtils;
import com.nekoimi.fastclaw.framework.webmagic.processor.IPageProcessor;
import com.nekoimi.fastclaw.framework.webmagic.processor.PageContext;
import com.nekoimi.fastclaw.mq.RabbitMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>CNDetailsPageProcessor</p>
 *
 * @author nekoimi 2022/4/26
 */
@Slf4j
public class CNDetailsPageProcessor implements IPageProcessor {
    private final RabbitMessageService rabbitMessageService;

    public CNDetailsPageProcessor() {
        ApplicationContext context = ContextHolder.getInstance();
        this.rabbitMessageService = (RabbitMessageService) context.getBean("downloadTorrentMessageService");
    }

    @Override
    public boolean supports(Page page) {
        return page.getUrl().regex("thread-[\\d]+-1-1\\.html").match();
    }

    @Override
    public void process(PageContext context, Page page) {
        String pageUrl = page.getUrl().get();
        Html html = page.getHtml();
        String title = html.xpath("//*[@id=\"thread_subject\"]/text()").get();
        if (StrUtil.isEmpty(title)) {
            log.warn("{} 未获取到标题!", pageUrl);
            page.setSkip(true);
            return;
        }
        // 标题
        page.putField("title", title);
        // 原始地址
        page.putField("originalUrl", pageUrl);
        // 番号
        String movieNumber = null;
        Html titleHtml = Html.create(title.toUpperCase());
        if (titleHtml.regex("([\\w]+-[\\d]+)").match()) {
            movieNumber = titleHtml.regex("([\\w]+-[\\d]+)").get();
            page.putField("movieNumber", removeBlanks(movieNumber));
        }
        if (StrUtil.isEmpty(movieNumber)) {
            log.warn("{} 未获取到番号!", pageUrl);
            page.setSkip(true);
            return;
        }
        // 更新时间
        String updateAtCssId = html.regex("id=\"(authorposton[\\d]+)\"").get();
        String updateAtFormat = html.xpath("//*[@id=\"" + updateAtCssId + "\"]/span/@title").get();
        LocalDateTime updateAt = LocalDateTimeUtils.parse(updateAtFormat);
        page.putField("createdAt", updateAt);
        page.putField("updatedAt", updateAt);
        // 详细信息
        String id = html.regex("id=\"postmessage_(\\d+)\"").get();
        List<Selectable> selectableList = html.xpath(StrUtil.format("//*[@id=\"postmessage_{}\"]", id)).nodes();
        selectableList.forEach(selectable -> {
            // 演员
            String actress = removeBlanks(selectable.regex("【出演女优】：(.*?)<br>").get());
            page.putField("actress", actress);
            // 封面图
            String cover = selectable.xpath("//img[1]/@zoomfile").get();
            page.putField("cover", cover);
            // 图片列表
            List<String> imageList = selectable.xpath("//img/@zoomfile").all();
            page.putField("imageList", imageList);
            // 磁力链接
            String magnetCssId = selectable.regex("id=\"(code_[\\w]+)\">").get();
            String magnetLink = selectable.xpath("//*[@id=\"" + magnetCssId + "\"]/ol/li/text()").get();
            page.putField("magnetLink", List.of(magnetLink));
        });
        // 种子文件
        String torrentUrl = html.xpath("//p[@class=\"attnm\"]/a/@href").get();
        page.putField("torrentUrl", List.of(torrentUrl));
        String torrentFilename = html.xpath("//p[@class=\"attnm\"]/a/text()").get();
        // 下载种子文件
        if (StrUtil.isNotEmpty(torrentUrl) && torrentUrl.startsWith("http")) {
            rabbitMessageService.send(Map.of("filename", torrentFilename,
                    "movieNumber", movieNumber));
        }
    }

    private String removeBlanks(String str) {
        return StrUtil.trim(StrUtil.removeAll(str, "&nbsp;"));
    }
}
