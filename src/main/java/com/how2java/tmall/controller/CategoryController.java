package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.PropertyService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.io.File;
@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyService propertyService;
    @RequestMapping("admin_category_list")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> cs = categoryService.list();
        int total = (int)new PageInfo<>(cs).getTotal();
        page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }
    @RequestMapping("admin_category_add")
    public ModelAndView add(Category c, HttpSession session , MultipartFile image) throws IOException{
        ModelAndView mav = new ModelAndView("redirect:admin_category_list");
        categoryService.add(c);
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, c.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();//上传文件
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return mav;
    }
    @RequestMapping("admin_category_delete")
    public String delete(Category category,HttpSession session) {
        List<Property> ps = propertyService.list(category.getId());
        for(Property p : ps) {
            propertyService.delete(p.getId());
        }
        categoryService.delete(category);
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, category.getId() + ".jpg");
        file.delete();
        return "redirect:admin_category_list";
    }
    @RequestMapping("admin_category_edit")
    public  String edit(Category category, Model model){
        Category c = categoryService.get(category.getId());
        model.addAttribute("category", c);
        return "admin/updateCategory";
    }
    @RequestMapping("admin_category_update")
    public String update(Category category, HttpSession session , MultipartFile image)throws IOException{
        ModelAndView mav = new ModelAndView("redirect:admin_category_list");
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, category.getId() + ".jpg");
        file.delete();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        categoryService.update(category);
        return "redirect:admin_category_list";
    }

}

