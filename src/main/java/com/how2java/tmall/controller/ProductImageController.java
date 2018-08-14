package com.how2java.tmall.controller;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductImageController {
   @Autowired
    ProductImageService productImageService;
   @Autowired
    ProductService productService;
   @RequestMapping("admin_productImage_list")
    public String list(Model model, int pid){
        List<ProductImage> pisSingle = productImageService.list(pid, productImageService.type_single);
        List<ProductImage> pisDetail = productImageService.list(pid, productImageService.type_detail);
        Product p = productService.get(pid);
        model.addAttribute("pisSingle", pisSingle);
        model.addAttribute("pisDetail", pisDetail);
        model.addAttribute("p", p);
        return "admin/listProductImage";
   }
   @RequestMapping("admin_productImage_add")
    public String  add(int pid, String type, HttpSession session , MultipartFile image) throws IOException {
        ProductImage p = new ProductImage();
        p.setPid(pid);
        p.setType(type);
        productImageService.add(p);
        File imageFolder = new File(session.getServletContext().getRealPath("img/productImage"));
        File file = new File(imageFolder, p.getId()+ ".jpg");
       if(!file.getParentFile().exists())
           file.getParentFile().mkdirs();
       image.transferTo(file);
       BufferedImage img = ImageUtil.change2jpg(file);
       ImageIO.write(img, "jpg", file);
       File imageFolder2 = new File(session.getServletContext().getRealPath("img/productSingle"));
       File file2 = new File(imageFolder2, pid+ ".jpg");
       FileInputStream fis = new FileInputStream(file);
       FileOutputStream fos = new FileOutputStream(file2);
       byte[] all =new byte[(int) file.length()];
       //以字节流的形式读取文件所有内容
       fis.read(all);

       fos.write(all);
       fos.close();
       fis.close();
       return "redirect:admin_productImage_list?pid=" + pid;
   }
   @RequestMapping("admin_productImage_delete")
    public String delete(int id, int pid,HttpSession session){
        productImageService.delete(id);
       File imageFolder = new File(session.getServletContext().getRealPath("img/productImage"));
       File file = new File(imageFolder, id + ".jpg");
       file.delete();
       return "redirect:admin_productImage_list?pid=" + pid;
   }
}
