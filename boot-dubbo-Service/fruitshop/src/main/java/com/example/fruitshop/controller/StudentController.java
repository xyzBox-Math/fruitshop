package com.example.fruitshop.controller;

import com.example.fruitshop.constant.Constant;
import com.example.fruitshop.domain.RetObject;
import com.example.fruitshop.domain.Student;
import com.example.fruitshop.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentController {
    @Resource
    private StudentService studentService;
    //懒得写自增了，插入的时候用一下就可以了
    private Integer maxIdAtNow;
    /**
     * 小测试
     * @param id
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/showStudent.do/{id}")
    public String showStudent(@PathVariable Integer id, HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("st",studentService.queryStudentById(id));
        return "showStudent";
    }

    /**
     * 中转视图
     * @return
     */
    @RequestMapping("/toStudent.do")
    public String toStudent(){
        return "student";
    }

    /**
     * 分页展示
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/student.do/{pageNum}/{pageSize}")
    public @ResponseBody  Object allStudent(@PathVariable String pageNum,@PathVariable String pageSize,HttpSession session){
        PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
        List<Student> list=studentService.queryAll();
        PageInfo<Student> pageInfo=new PageInfo<>(list);
        /**
         * 只在第一次分页时获取最大id，防止获取的是当前页最大id而不是想要的结果集里最大id
         */
        if(Integer.parseInt(pageNum)==1)
            maxIdAtNow=list.get(0).getId();
        /**
         * 获取详情时不去数据库。使用pageInfo查询到的内容即可
         */
        session.setAttribute("pageInfo",pageInfo);
        return pageInfo;
    }

    /**
     * 按照id单个删除（局部按钮）
     * @param id
     * @return
     */
    @RequestMapping("/reStudentById.do")
    public @ResponseBody Object reStudentById(Integer id){
        RetObject retObject=new RetObject();
        try {
            int ret = studentService.removeStudentById(id);
            if(ret>0){
                retObject.setCode(Constant.STATUS_TRUE_CODE);
            }else {
                maxIdAtNow--;
                retObject.setCode(Constant.STATUS_FALSE_CODE);
                retObject.setSomeThing(Constant.ERROR_MESSAGE);
            }
        }catch(Exception e){
            retObject.setCode(Constant.STATUS_FALSE_CODE);
            retObject.setSomeThing(Constant.ERROR_MESSAGE);
        }
        return retObject;
    }


    /**
     * 测试id的获取
     */
    @RequestMapping("/reTest.do")
    public @ResponseBody Object reTest(Integer id){
       RetObject retObject=new RetObject();
       retObject.setCode(id);
        return retObject;
    }


    /**
     * 单击记录去往学生详情页面
     */
    @RequestMapping("/details.do")
    public @ResponseBody Object details(Integer id,HttpSession httpSession){
        Student stu=new Student();
        PageInfo<Student> pageInfo=(PageInfo<Student>) httpSession.getAttribute("pageInfo");
        List<Student> list=pageInfo.getList();
        for (Student st:list) {
            if(st.getId().equals(id)){
                stu=st;
            }
        }
        return stu;
    }

    /**
     * 添加单个学生
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("/addStudentOne.do")
    public @ResponseBody Object addStudentOne(String name,Integer age){
        RetObject retObject=new RetObject();
        Student student=new Student();
        student.setId(++maxIdAtNow);
        student.setName(name);
        student.setAge(age);
        try {
            int ret = studentService.addStudentOne(student);
            if(ret>0){
                retObject.setCode(Constant.STATUS_TRUE_CODE);
            }else {
                retObject.setCode(Constant.STATUS_FALSE_CODE);
                retObject.setSomeThing(Constant.ERROR_MESSAGE);
            }
        }catch(Exception e){
            retObject.setCode(Constant.STATUS_FALSE_CODE);
            retObject.setSomeThing(Constant.ERROR_MESSAGE);
        }
        return retObject;
    }


    @RequestMapping("/listOfStudent.do")
    public @ResponseBody Object listOfDeStudent(String id){
        String[] ids=id.split(",");
        int idSize= ids.length;
        Integer[] idss=new Integer[idSize];
        for(int i=0;i<idSize;i++){
            idss[i]=Integer.parseInt(ids[i]);
        }
        List<Student> list=studentService.queryStudentListById(idss);
        return list;
    }
}
