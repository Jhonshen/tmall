package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categorySertvice;
    @RequestMapping("admin_product_list")
    public  String list( int cid, Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> ps = productService.list(cid);
        Category c = categorySertvice.get(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+c.getId());
        model.addAttribute("ps", ps);
        model.addAttribute("c", c);
        model.addAttribute("page", page);
        return "admin/listProduct";
    }
    @RequestMapping("admin_product_add")
    public  String add(int cid, Product p){
        productService.add(p);
        return "redirect:admin_product_list?cid=" + cid;
    }
    @RequestMapping("admin_product_delete")
    public String delete(int id, int cid, Model model){
        productService.delete(id);
        return "redirect:admin_product_list?cid=" + cid;
    }
    @RequestMapping("admin_product_edit")
    public String edit(int id, int cid, Model model){
        Product p = productService.get(id);
        model.addAttribute("p", p);
        model.addAttribute("cid", cid);
        return "admin/updateProduct";
    }
    @RequestMapping("admin_product_update")
    public String update(Product p, int cid, Model model) {
        productService.update(p);
        return "redirect:admin_product_list?cid=" + cid;
    }
}
