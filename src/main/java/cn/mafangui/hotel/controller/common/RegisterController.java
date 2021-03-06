package cn.mafangui.hotel.controller.common;

import cn.mafangui.hotel.entity.User;
import cn.mafangui.hotel.entity.Worker;
import cn.mafangui.hotel.enums.Role;
import cn.mafangui.hotel.response.AjaxResult;
import cn.mafangui.hotel.response.ResponseTool;
import cn.mafangui.hotel.service.UserService;
import cn.mafangui.hotel.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/user")
public class RegisterController {

    @Autowired
    UserService userService;
    @Autowired
    WorkerService workerService;

    @RequestMapping(method = RequestMethod.POST,value = "/password")
    public AjaxResult userRegister(String username, String idcard){
        User user = new User();
        user.setUsername(username);
        user.setIdcard(idcard);
        String result = userService.getPassword(user);
        if(result!=null){
            return ResponseTool.success(result);
        }
        else return ResponseTool.failed("请检查填写信息");
    }

    @RequestMapping(method = RequestMethod.POST,value = "/register")
    public AjaxResult userRegister(User user){
        int result = userService.insertUser(user);
        if(result==1){
            return ResponseTool.success(result);
        }
        else return ResponseTool.failed("注册失败,请稍后再试");
    }

    @RequestMapping(method = RequestMethod.POST,value = "/admin")
    public AjaxResult adminRegister(String username, String password, String name, String gender, String phone, String email, String address){
        Worker worker = new Worker(username,password,name,gender,phone,email,address);
        worker.setRole(Role.ADMIN.getValue());
        int result = workerService.insert(worker);
        if(result==1){
            return ResponseTool.success(result);
        }
        else return ResponseTool.failed("注册失败,请稍后再试");
    }

    @RequestMapping(method = RequestMethod.POST,value = "/operator")
    public AjaxResult operatorRegister(String username,String password,String name,String gender,String phone,String email,String address){
        Worker worker = new Worker(username,password,name,gender,phone,email,address);
        worker.setRole(Role.OPERATOR.getValue());
        int result = workerService.insert(worker);
        if(result==1){
            return ResponseTool.success(result);
        }
        else return ResponseTool.failed("注册失败,请稍后再试");
    }

}
