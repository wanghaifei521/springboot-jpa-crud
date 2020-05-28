package com.qf.controller;

import com.qf.domain.Items;
import com.qf.service.ItemsService;
import com.qf.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by 54110 on 2019/11/21.
 */
@Controller
public class ItemsController {

    @Autowired
    private ItemsService itemsService;
    @Value("${qiniu.url}")
    private  String url;
    @Autowired
    private UploadUtils up;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Items> all = itemsService.findAll();
        model.addAttribute("all",all);
        return "itemList";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveItems(Items items, @RequestParam("file")MultipartFile muli){
        try {
            //上传图片
            String upload = up.upload(muli);
            items.setPic(url+upload);
            itemsService.saveItems(items);
            return "redirect:findAll";
        }catch (Exception e){
            return "404";
        }

    }

    @RequestMapping("/findById/{id}")
    public String selectById(@PathVariable("id")Integer id, Model model){
        Items items = itemsService.findById(id);
        model.addAttribute("itmes",items);

        return "update";
    }

    @RequestMapping(value = "/updateById",method = RequestMethod.POST)
    public String updateById(Items items){
        itemsService.updateItems(items);

        return "redirect:/findAll";
    }

    @RequestMapping("/deleteById/{id}")
    public String deletById(@PathVariable("id")Integer id){
        itemsService.deleteItems(id);
        return "redirect:/findAll";
    }

    @RequestMapping("/insertPage")
    public String insertPage(){
        return "save";
    }

}
