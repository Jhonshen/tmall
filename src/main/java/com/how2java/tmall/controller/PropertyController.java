package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.PropertyService;
import com.how2java.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    PropertyService propertyService;
    @Autowired
    CategoryService categoryService;
    @RequestMapping("admin_property_list")
    public String list(int cid, Model model, Page page) {
        System.out.println("property");
        Category c = categoryService.get(cid);

        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> ps = propertyService.list(cid);

        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+c.getId());

        model.addAttribute("ps", ps);
        model.addAttribute("c", c);
        model.addAttribute("page", page);

        return "admin/listProperty";
    }
    @RequestMapping("admin_property_delete")
    public  String delete(int id, int cid){
        propertyService.delete(id);
        return "redirect:admin_property_list?cid=" + cid;
    }
    @RequestMapping("admin_property_edit")
    public String edit(int id,int cid, Model model){
       Property p = propertyService.get(id);
       model.addAttribute("p", p);
       model.addAttribute("cid", cid);
       return "admin/updateProperty";
    }
    @RequestMapping("admin_property_update")
    public String update(Property p, int cid){
        propertyService.update(p);
        return "redirect:admin_property_list?cid="+cid;
    }
    @RequestMapping("admin_property_add")
    public String add(Property p, int cid){
        propertyService.add(p);

        return "redirect:admin_property_list?cid="+cid;
    }
}
