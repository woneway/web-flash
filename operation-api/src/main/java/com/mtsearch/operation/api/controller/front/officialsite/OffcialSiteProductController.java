package com.mtsearch.operation.api.controller.front.officialsite;

import com.mtsearch.operation.api.controller.BaseController;
import com.mtsearch.operation.bean.entity.cms.Article;
import com.mtsearch.operation.bean.enumeration.cms.BannerTypeEnum;
import com.mtsearch.operation.bean.enumeration.cms.ChannelEnum;
import com.mtsearch.operation.bean.vo.front.Rets;
import com.mtsearch.operation.bean.vo.offcialsite.BannerVo;
import com.mtsearch.operation.bean.vo.offcialsite.Product;
import com.mtsearch.operation.service.cms.ArticleService;
import com.mtsearch.operation.service.cms.BannerService;
import com.mtsearch.operation.utils.Maps;
import com.mtsearch.operation.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/offcialsite/product")
public class OffcialSiteProductController extends BaseController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Object index() {
        Map<String, Object> dataMap = Maps.newHashMap();

        BannerVo banner = bannerService.queryBanner(BannerTypeEnum.SOLUTION.getValue());
        dataMap.put("banner", banner);

        List<Product> products = new ArrayList<>();
        Page<Article> articlePage = articleService.query(1, 10, ChannelEnum.PRODUCT.getId());
        for (Article article : articlePage.getRecords()) {
            products.add(new Product(article.getId(), article.getTitle(), article.getImg()));
        }
        dataMap.put("productList", products);

        Map map = Maps.newHashMap("data", dataMap);
        return Rets.success(map);

    }
}
