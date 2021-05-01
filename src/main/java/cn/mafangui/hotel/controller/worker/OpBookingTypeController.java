package cn.mafangui.hotel.controller.worker;

import cn.mafangui.hotel.entity.OrderType;
import cn.mafangui.hotel.response.AjaxResult;
import cn.mafangui.hotel.response.ResponseTool;
import cn.mafangui.hotel.service.OrderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/op/order-type")
public class OpBookingTypeController {
    @Autowired
    private OrderTypeService orderTypeService;

    @RequestMapping(value = "/add")
    public AjaxResult addOrderType(String type,String remark){
        OrderType orderType = new OrderType(type,remark);
        int re = orderTypeService.insertOrderType(orderType);
        if(re!=1) return ResponseTool.failed();
        return ResponseTool.success();
    }

    @RequestMapping(value = "/delete/{typeId}")
    public AjaxResult deleteOrderType(@PathVariable Integer typeId){
        int re= orderTypeService.deleteOrderType(typeId);
        if(re!=1) return ResponseTool.failed();
        return ResponseTool.success();
    }

    @RequestMapping(value = "/batchDelete")
    public AjaxResult batchDeleteOrderType(String data){
        String substring = data.substring(0, data.length() - 1);
        String[] split = substring.split(",");
        int re= orderTypeService.batchDeleteOrderType(split);
        if(re<=0) return ResponseTool.failed();
        return ResponseTool.success();
    }



    @RequestMapping(value = "/update")
    public AjaxResult updateOrderType(Integer typeId,String type,String remark){
        OrderType orderType = new OrderType(type,remark);
        orderType.setTypeId(typeId);
        int re = orderTypeService.updateOrderType(orderType);
        if(re!=1) return ResponseTool.failed();
        return ResponseTool.success();
    }

    @RequestMapping(value = "/{orderTypeId}")
    public AjaxResult getById(@PathVariable Integer orderTypeId){
        OrderType s=orderTypeService.selectById(orderTypeId);
        System.out.println(s);
        return ResponseTool.success(s);
    }

    @RequestMapping(value = "")
    public AjaxResult getAllType(){
        return ResponseTool.success(orderTypeService.selectAll());
    }

}
