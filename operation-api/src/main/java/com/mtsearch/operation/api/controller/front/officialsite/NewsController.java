package com.mtsearch.operation.api.controller.front.officialsite;

import com.mtsearch.operation.api.controller.BaseController;
import com.mtsearch.operation.bean.entity.cms.Article;
import com.mtsearch.operation.bean.enumeration.cms.BannerTypeEnum;
import com.mtsearch.operation.bean.enumeration.cms.ChannelEnum;
import com.mtsearch.operation.bean.vo.front.Rets;
import com.mtsearch.operation.bean.vo.offcialsite.BannerVo;
import com.mtsearch.operation.bean.vo.offcialsite.News;
import com.mtsearch.operation.service.cms.ArticleService;
import com.mtsearch.operation.service.cms.BannerService;
import com.mtsearch.operation.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/offcialsite/news")
public class NewsController extends BaseController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Object list() {
        Map<String, Object> dataMap = new HashMap<>(10);
        BannerVo banner = bannerService.queryBanner(BannerTypeEnum.NEWS.getValue());
        dataMap.put("banner", banner);

        List<News> newsList = new ArrayList<>();
        Page<Article> articlePage = articleService.query(1, 10, ChannelEnum.NEWS.getId());

        for (Article article : articlePage.getRecords()) {
            News news = new News();
            news.setDesc(article.getTitle());
            news.setUrl("/article?id=" + article.getId());
            news.setSrc("static/images/icon/user.png");
            newsList.add(news);
        }

        dataMap.put("list", newsList);

        Map map = new HashMap(2);

        map.put("data", dataMap);
        return Rets.success(map);

    }
}
