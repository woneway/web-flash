package com.mtsearch.operation.api.controller.test;

import com.mtsearch.operation.api.controller.BaseController;
import com.mtsearch.operation.bean.entity.test.Boy;
import com.mtsearch.operation.service.test.BoyService;

import com.mtsearch.operation.bean.core.BussinessLog;
import com.mtsearch.operation.bean.constant.factory.PageFactory;
import com.mtsearch.operation.bean.enumeration.BizExceptionEnum;
import com.mtsearch.operation.bean.exception.ApplicationException;
import com.mtsearch.operation.bean.vo.front.Rets;

import com.mtsearch.operation.utils.factory.Page;


import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test/boy")
public class BoyController extends BaseController {
	private  Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BoyService boyService;

	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@RequiresPermissions(value = "/test/boy")
	public Object list(@RequestParam(required = false) Long id) {
		Page<Boy> page = new PageFactory<Boy>().defaultPage();
		page.addFilter("id",id);
		page = boyService.queryPage(page);
		return Rets.success(page);
	}
	@RequestMapping(method = RequestMethod.POST)
	@BussinessLog(value = "新增男孩", key = "name")
	@RequiresPermissions(value = "/test/boy/add")
	public Object add(@ModelAttribute Boy boy){
		boyService.insert(boy);
		return Rets.success();
	}
	@RequestMapping(method = RequestMethod.PUT)
	@BussinessLog(value = "更新男孩", key = "name")
	@RequiresPermissions(value = "/test/boy/update")
	public Object update(@ModelAttribute Boy boy){
		boyService.update(boy);
		return Rets.success();
	}
	@RequestMapping(method = RequestMethod.DELETE)
	@BussinessLog(value = "删除男孩", key = "id")
	@RequiresPermissions(value = "/test/boy/delete")
	public Object remove(Long id){
		if (id == null) {
			throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
		}
		boyService.delete(id);
		return Rets.success();
	}
}