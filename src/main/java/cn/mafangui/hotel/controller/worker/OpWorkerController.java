package cn.mafangui.hotel.controller.worker;

import cn.mafangui.hotel.entity.Worker;
import cn.mafangui.hotel.enums.Role;
import cn.mafangui.hotel.response.AjaxResult;
import cn.mafangui.hotel.response.ResponseTool;
import cn.mafangui.hotel.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/operator")
public class OpWorkerController {
    @Autowired
    private WorkerService workerService;


    @RequestMapping(method = RequestMethod.POST,value = "/delete/{workerId}")
    public AjaxResult deleteOperator(@PathVariable Integer workerId){
        int re = workerService.delete(workerId);
        if(re!=1) ResponseTool.failed();
        return ResponseTool.success("删除成功");
    }

    @RequestMapping(method = RequestMethod.POST,value = "/batchDelete")
    public AjaxResult batchDelete(String data){
        String substring = data.substring(0, data.length() - 1);
        String[] split = substring.split(",");
        int re = workerService.batchDelete(split);
        if(re!=1) ResponseTool.failed();
        return ResponseTool.success("删除成功");
    }

    @RequestMapping(value = "")
    public AjaxResult getAllOperator(){
        return ResponseTool.success(workerService.selectByRole(Role.OPERATOR.getValue()));
    }

    @RequestMapping(method = RequestMethod.POST,value = "/{workerId}")
    public AjaxResult getOperator(@PathVariable Integer workerId){
        System.out.println(333);
        Worker worker = workerService.selectById(workerId);
        System.out.println(worker);
        return ResponseTool.success(worker);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public AjaxResult addOperator(String username,String password,String name,String gender,String phone,String email,String address){
        Worker worker = new Worker(username,password,name,gender,phone,email,address);
        worker.setRole(Role.OPERATOR.getValue());
        int re = workerService.insert(worker);
        if(re!=1) return ResponseTool.failed();
        return ResponseTool.success("添加成功");
    }


    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public AjaxResult updateOperator(int workerId,String name,String gender,String phone,String email,String address){
        Worker worker = new Worker();
        worker.setWorkerId(workerId);
        worker.setName(name);
        worker.setGender(gender);
        worker.setPhone(phone);
        worker.setEmail(email);
        worker.setAddress(address);
        int re =  workerService.updateById(worker);
        if(re!=1) return ResponseTool.failed();
        return ResponseTool.success("更新成功");
    }
    @RequestMapping(value = "/by/phone/{data}")
    public AjaxResult getWorkerByPhone(@PathVariable String data){
        List<Worker> worker = workerService.getWorker(data);
        if(worker!=null){
            return ResponseTool.success(worker);
        }else {
            return ResponseTool.failed("没有该操作员");
        }

    }

}
