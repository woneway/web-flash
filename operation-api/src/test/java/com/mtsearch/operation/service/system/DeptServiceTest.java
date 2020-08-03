package com.mtsearch.operation.service.system;

import com.mtsearch.operation.BaseApplicationStartTest;
import com.mtsearch.operation.bean.vo.node.ZTreeNode;
import com.mtsearch.operation.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created  on 2018/4/5 0005.
 *
 * @author enilu
 */
public class DeptServiceTest extends BaseApplicationStartTest {


    @Autowired
    private DeptService deptService;

    @Test
    public void tree() {
        List<ZTreeNode> list = deptService.tree();
        for (ZTreeNode treeNode : list) {
            System.out.println(JsonUtil.toJson(treeNode));
        }
    }

}
