package com.example.fruitshop.controller;

import com.example.fruitshop.domain.Student;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AjaxFileUpLoadController {
    @RequestMapping("/testIndex.do")
    public String testIndex(){
        return "testIndex";
    }

    /**
     * ajax文件异步上传
     * @param ajFile
     * @return
     */
    @RequestMapping("/play.do")
    public @ResponseBody Object play(MultipartFile ajFile,HttpServletRequest httpServletRequest){
        /**
         * 获取文件的真实名字
         */
        String name=ajFile.getOriginalFilename();
        /**
         * 获取文件的后缀
         */
        String hou= StringUtils.substringAfter(name,".");
        /**
         * uuid生成16位随机字符串拼接后缀形成新的名字，防止相同图片名字覆盖问题
         */
        String newName=UUID.randomUUID().toString().replaceAll("-","").concat(".").concat(hou);
        /**
         * 获取文件的真实路径(放在target目录下)
         */
        String path= ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        try {
            ajFile.transferTo(new File(path+File.separator+"img"+File.separator+newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 获取访问机器的协议、ip、port
         * 上下文路径
         */
        String scheme=httpServletRequest.getScheme();
        String ip=httpServletRequest.getServerName();
        int port=httpServletRequest.getServerPort();
        String contextPath=httpServletRequest.getContextPath();
        /**
         * 用于临时传输json的对象
         */
        Student st=new Student();
        st.setId(0);
        st.setAge(0);
        st.setName(scheme+"://"+ip+":"+port+contextPath+"/img/"+newName);
        return st;
    }

}
