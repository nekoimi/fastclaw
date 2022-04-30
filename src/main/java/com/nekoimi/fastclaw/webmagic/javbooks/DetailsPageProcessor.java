package com.nekoimi.fastclaw.webmagic.javbooks;

import cn.hutool.core.util.StrUtil;
import com.nekoimi.fastclaw.framework.utils.LocalDateTimeUtils;
import com.nekoimi.fastclaw.framework.webmagic.processor.BasePageProcessor;
import com.nekoimi.fastclaw.framework.webmagic.processor.PageContext;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>DetailsPageProcessor</p>
 *
 * @author nekoimi 2022/4/30
 */
@Slf4j
public class DetailsPageProcessor extends BasePageProcessor {

    @Override
    public boolean supports(Page page) {
        return page.getUrl().regex("content_censored/[\\d]+\\.htm").match();
    }

    @Override
    public void process(PageContext context, Page page) {
        String pageUrl = page.getUrl().get();
        Html html = page.getHtml();
        String title = removeBlanks(html.xpath("//*[@id=\"title\"]/b/text()").get());
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
        String movieNumber = removeBlanks(html.xpath("//*[@id=\"info\"]/div[2]/font/a/text()").get());
        if (StrUtil.isEmpty(movieNumber)) {
            log.warn("{} 未获取到番号!", pageUrl);
            page.setSkip(true);
            return;
        }
        page.putField("movieNumber", movieNumber);
        // 更新时间
        String updateAtFormat = html.xpath("//*[@id=\"info\"]/div[3]/text()").get();
        if (StrUtil.isNotEmpty(updateAtFormat)) {
            LocalDateTime updateAt = LocalDateTimeUtils.parseDate(updateAtFormat);
            page.putField("createdAt", updateAt);
            page.putField("updatedAt", updateAt);
        }
        // 演员
        List<String> actressList = html.xpath("//*[@id=\"info\"]/div[10]/div[@class=\"av_performer_cg_box\"]/div[@class=\"av_performer_name_box\"]/a/text()").all();
        page.putField("actressList", actressList);
        // 封面图
        String cover = html.xpath("//*[@id=\"info\"]/div[@class=\"info_cg\"]/img/@src").get();
        page.putField("cover", cover);
        // 图片列表
        List<String> imageList = html.xpath("/html/body/div[@class=\"gallery\"]/div[@class=\"hvr-grow\"]/a/@href").all();
        page.putField("imageList", imageList);
        // 磁力链接
        // <script>
        // $('#rid_826027213835').attr('href','magnet:?xt=urn:btih:'+reurl(''));
        // </script>
        List<String> encodeList = html.regex("'magnet:\\?xt=urn:btih:'\\+reurl\\('([01]+)'\\)").all();
        page.putField("magnetLink", encodeList);
        // 系列
        String series = html.xpath("//*[@id=\"info\"]/div[8]/a/text()").get();
        page.putField("series", series);
        // 时长
        String duration = html.xpath("//*[@id=\"info\"]/div[4]/text()").get();
        page.putField("duration", duration);
        // 制造商
        String manufacturer = html.xpath("//*[@id=\"info\"]/div[6]/a/text()").get();
        page.putField("manufacturer", manufacturer);
        // 标签
        List<String> labelList = html.xpath("//*[@id=\"info\"]/div[9]/a/text()").all();
        page.putField("labelList", labelList);
    }
}
