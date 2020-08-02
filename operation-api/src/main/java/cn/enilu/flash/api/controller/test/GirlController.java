package cn.enilu.flash.api.controller.test;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.entity.test.Girl;
import cn.enilu.flash.service.test.GirlService;

import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.enumeration.BizExceptionEnum;
import cn.enilu.flash.bean.exception.ApplicationException;
import cn.enilu.flash.bean.vo.front.Rets;

import cn.enilu.flash.utils.factory.Page;


import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test/girl")
public class GirlController extends BaseController {
	private  Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private GirlService girlService;

	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@RequiresPermissions(value = "/test/girl")
	public Object list(@RequestParam(required = false) Long id) {
		Page<Girl> page = new PageFactory<Girl>().defaultPage();
		page.addFilter("id",id);
		page = girlService.queryPage(page);
		return Rets.success(page);
	}
	@RequestMapping(method = RequestMethod.POST)
	@BussinessLog(value = "新增女孩", key = "name")
	@RequiresPermissions(value = "/test/girl/add")
	public Object add(@ModelAttribute Girl girl){
		girlService.insert(girl);
		return Rets.success();
	}
	@RequestMapping(method = RequestMethod.PUT)
	@BussinessLog(value = "更新女孩", key = "name")
	@RequiresPermissions(value = "/test/girl/update")
	public Object update(@ModelAttribute Girl girl){
		girlService.update(girl);
		return Rets.success();
	}
	@RequestMapping(method = RequestMethod.DELETE)
	@BussinessLog(value = "删除女孩", key = "id")
	@RequiresPermissions(value = "/test/girl/delete")
	public Object remove(Long id){
		if (id == null) {
			throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
		}
		girlService.delete(id);
		return Rets.success();
	}
}