package com.mtsearch.operation.api.controller.cms;

import com.mtsearch.operation.api.controller.BaseController;
import com.mtsearch.operation.bean.constant.factory.PageFactory;
import com.mtsearch.operation.bean.core.BussinessLog;
import com.mtsearch.operation.bean.entity.cms.Article;
import com.mtsearch.operation.bean.enumeration.Permission;
import com.mtsearch.operation.bean.vo.front.Rets;
import com.mtsearch.operation.bean.vo.query.SearchFilter;
import com.mtsearch.operation.service.cms.ArticleService;
import com.mtsearch.operation.utils.DateUtil;
import com.mtsearch.operation.utils.factory.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章管理
 */
@RestController
@RequestMapping("/article")
public class ArticleMgrController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑文章", key = "name")
    @RequiresPermissions(value = {Permission.ARTICLE_EDIT})
    public Object save() {
        Article article = getFromJson(Article.class);
        if (article.getId() != null) {
            Article old = articleService.get(article.getId());
            old.setAuthor(article.getAuthor());
            old.setContent(article.getContent());
            old.setIdChannel(article.getIdChannel());
            old.setImg(article.getImg());
            old.setTitle(article.getTitle());
            articleService.update(old);
        } else {
            articleService.insert(article);
        }
        return Rets.success();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除文章", key = "id")
    @RequiresPermissions(value = {Permission.ARTICLE_DEL})
    public Object remove(Long id) {
        articleService.delete(id);
        return Rets.success();
    }

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.ARTICLE})
    public Object get(@Param("id") Long id) {
        Article article = articleService.get(id);
        return Rets.success(article);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.ARTICLE})
    public Object list(@RequestParam(required = false) String title,
                       @RequestParam(required = false) String author,
                       @RequestParam(required = false) String startDate,
                       @RequestParam(required = false) String endDate
    ) {
        Page<Article> page = new PageFactory<Article>().defaultPage();
        page.addFilter("title", SearchFilter.Operator.LIKE, title);
        page.addFilter("author", SearchFilter.Operator.EQ, author);
        page.addFilter("createTime", SearchFilter.Operator.GTE, DateUtil.parse(startDate, "yyyyMMddHHmmss"));
        page.addFilter("createTime", SearchFilter.Operator.LTE, DateUtil.parse(endDate, "yyyyMMddHHmmss"));
        page = articleService.queryPage(page);
        return Rets.success(page);
    }
}
