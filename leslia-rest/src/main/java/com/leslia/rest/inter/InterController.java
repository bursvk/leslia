package com.leslia.rest.inter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.inter.api.BookService;
import com.leslia.inter.api.HelpCategoryService;
import com.leslia.inter.api.InitDemoService;
import com.leslia.inter.pojo.Book;
import com.leslia.util.data.Result;
import com.leslia.util.data.ResultUtil;
import com.leslia.util.enums.EnumCode;
import com.leslia.util.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/inter")
public class InterController{

    private Logger logger= LoggerFactory.getLogger(InterController.class);

    @Reference
    private InitDemoService initDemoService;
    @Reference
    private BookService bookService;
    @Reference
    private HelpCategoryService helpCategoryService;

    @RequestMapping("/index")
    @ResponseBody
    public Result index() throws Exception{
       Map<String,Object> map=new HashMap<>();
       map.put("username","suli");
       map.put("password","123456");
       return  new ResultUtil().setData("hello");
    }

    @RequestMapping("/index1")
    @ResponseBody
    public Result index1(){
        throw new BaseException(EnumCode.FAIL_2);
        //return new ResultUtil().setError(100,"神马错误");
    }


    /**
     * 从Request对象中获得客户端IP，处理了HTTP代理服务器和Nginx的反向代理截取了ip
     * @param request
     * @return ip
     */
    public static String getLocalIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if(forwarded != null){
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }

    @RequestMapping(value = "/user/{id}")
    @ResponseBody
    public String userId(@PathVariable Integer id) throws Exception{
        logger.info("id:"+id);
        if(id==1){
            throw new Exception("1");
        }
        return id+"";
    }



    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam String name){
        logger.info("name:{}",name);
        String backName="";
        try{
            backName=initDemoService.helloWorld(name);
            logger.info("backName:{}",backName);
        }catch (Exception e){
            logger.error("this witch dubbo service error",e);
        }
        return backName;
    }

    @RequestMapping("/book/insert")
    @ResponseBody
    public void insert(){
        Book book=new Book();
        book.setBookName("神奇校车r");
        book.setAuthor("乔安娜柯尔r");
        book.setCreateTime(new Date());
        bookService.insertBook(book);
    }

   /* @RequestMapping("/page")
    @ResponseBody
    public void page(){
        List<HelpCategory> list= helpCategoryService.getList(1,10);
        PageInfo<HelpCategory> pageInfo=new PageInfo(list);
        System.out.println(pageInfo.toString());
        for (HelpCategory helpCategory:pageInfo.getList()){
            System.out.println("pageInfo:"+helpCategory.getHelpCategoryId());
        }
       for(HelpCategory helpCategory:list){
            System.out.println("list:"+helpCategory.getHelpCategoryId());
       }

    }*/




}
