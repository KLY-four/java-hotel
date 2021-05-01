package cn.mafangui.hotel.controller.worker;

import cn.mafangui.hotel.entity.Order;
import cn.mafangui.hotel.enums.OrderStatus;
import cn.mafangui.hotel.response.AjaxResult;
import cn.mafangui.hotel.response.MsgType;
import cn.mafangui.hotel.response.ResponseTool;
import cn.mafangui.hotel.service.OrderService;
import cn.mafangui.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 订单接口
 */
@RestController
@RequestMapping(value = "/op/order")
public class OpOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomService roomService;

    /**
     * 添加预订
     * 订单状态默认为未付款状态
     */
    @RequestMapping(value = "/add")
    public AjaxResult addOrder(int orderTypeId,String orderType, int userId,String name, String phone,int roomTypeId, String roomType,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate, Integer orderDays, Double orderCost,Integer roomId,Integer orderStatus){
        Order order = new Order();
        order.setOrderDate(orderDate);
        order.setOrderCost(orderCost);
        order.setOrderDays(orderDays);
        order.setPhone(phone);
        order.setName(name);
        order.setUserId(userId);
        order.setOrderType(orderType);
        order.setOrderStatus(orderStatus);
        order.setOrderTypeId(orderTypeId);
        order.setRoomNumber(roomId);
        order.setRoomTypeId(roomTypeId);
        order.setRoomType(roomType);
        int re = orderService.addOrder(order);
        if(re!=1) return ResponseTool.failed(MsgType.FAILED);
        return ResponseTool.success("添加成功.");
    }


    @RequestMapping(value = "/delete")
    public AjaxResult deleteOrder(int orderId){
        int re = orderService.delete(orderId);
        if(re!=1) return ResponseTool.failed(MsgType.FAILED);
        return ResponseTool.success("删除成功.");
    }


    @RequestMapping(value = "/batchDelete")  //批量删除订单
    public AjaxResult batchDeleteOrder(String data){
        String substring = data.substring(0, data.length() - 1);
        String[] split = substring.split(",");
        System.out.println(Arrays.toString(split));
        Integer re = orderService.batchDeleteOrder(split);
        System.out.println(re);
        if(re<=0) {return ResponseTool.failed(MsgType.FAILED);}else {
            return ResponseTool.success("删除成功.");
        }
    }


    @RequestMapping(value = "/update")
    public AjaxResult updateOrder(int orderId,int orderTypeId,String orderType, int userId,String name, String phone,int roomTypeId, String roomType,
                           @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate, Integer orderDays, Double orderCost,Integer roomId,Integer orderStatus){
        Order order = new Order();
        order.setOrderDate(orderDate);
        order.setOrderCost(orderCost);
        order.setOrderDays(orderDays);
        order.setPhone(phone);
        order.setName(name);
        order.setUserId(userId);
        order.setOrderType(orderType);
        order.setOrderStatus(orderStatus);
        order.setOrderTypeId(orderTypeId);
        order.setRoomNumber(roomId);
        order.setRoomTypeId(roomTypeId);
        order.setRoomType(roomType);
        int re =  orderService.update(order);
        if(re!=1) return ResponseTool.failed(MsgType.FAILED);
        return ResponseTool.success("修改成功.");
    }

    /**
     * 订单支付
     * @param orderId
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/pay")
    public AjaxResult payOrder(int orderId){
        int re = orderService.payOrder(orderId);
        if(re!=1) return ResponseTool.failed(MsgType.FAILED);
        return ResponseTool.success("支付成功.");
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/cancel")
    public AjaxResult cancelOrder(int orderId){
        int re= orderService.cancelOrder(orderId);
        if(re!=1) return ResponseTool.failed(MsgType.FAILED);
        return ResponseTool.success("取消成功.");
    }

    /**
     * 订单超时
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/overtime")
    public int orderOver(int orderId){
        Order order = new Order(orderId,OrderStatus.OVERTIME.getCode());
        return orderService.update(order);
    }


    @RequestMapping(value = "")
    public AjaxResult getAllOrder(){
        return ResponseTool.success(orderService.AllOrders());
    }

    @RequestMapping(value = "/count")
    public AjaxResult getOrderCount(){
        return ResponseTool.success(orderService.getOrderCount());
    }

    /**
     * 根据userID查询所有订单
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/{userId}")
    public AjaxResult getByUser(@PathVariable  int userId){
        return ResponseTool.success(orderService.selectByUserId(userId));
    }


    /**
     * 根据订单号查询订单
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/{orderId}")
    public AjaxResult getById(@PathVariable Integer orderId){
        return ResponseTool.success(orderService.selectByOrderId(orderId));
    }

    /**
     * 根据姓名、预留手机号查找订单
     * 主要用于客户入住
     * @param name
     * @param phone
     * @return
     */
    @RequestMapping(value = "/withNameAndPhone/{i}")
    public AjaxResult getByNameAndPhone(String name,String phone,@PathVariable String i) {
        Order order = orderService.selectByNameAndPhone(name, phone,i);
        if(order!=null){
            return ResponseTool.success(order);
        }else {
           return ResponseTool.failed("没有该用户");
        }

    }

    @RequestMapping(value = "/update/status/{i}")
    @Transactional(rollbackFor = {Exception.class})
    public AjaxResult updateOrderStatus(String name,String phone,@PathVariable Integer i){
        Order order1 = orderService.selectByNameAndPhone(name, phone,String.valueOf(i-1));
        if(order1!=null){
            Order order = new Order();
            order.setName(name);
            order.setPhone(phone);
            if(i==2) {
                order.setOrderStatus(2);
                int i1=orderService.updateOrderStatusService(order);
                int i2=roomService.updateStatusByRoomNumber(order1.getRoomNumber(),3);
                return ResponseTool.success(1);
            }else if(i==3){
                order.setOrderStatus(3);
                int i1=orderService.updateOrderStatusService(order);
                int i2=roomService.updateStatusByRoomNumber(order1.getRoomNumber(),1);
                    return ResponseTool.success(1);
            }else {
                return ResponseTool.failed("请求错误");
            }
        }else {
            return ResponseTool.failed("没有该用户或该用户不符合入住/退房条件");
        }
    }

    @RequestMapping(value = "/by/phone/{data}")
    public AjaxResult getDataByPhone(@PathVariable String data){
        List<Order> orderByPhone = orderService.getOrderByPhone(data);
        if(orderByPhone!=null){
            return ResponseTool.success(orderByPhone);
        }else {
            return ResponseTool.failed("没有该订单");
        }

    }

    @RequestMapping(value = "/select") //查询已接受的所有订单，包括已经删除的订单
    public AjaxResult select(){
        Integer count = orderService.select();
        System.out.println(count);
        if(count==null){
            count=0;
            return ResponseTool.success(count);
        }else {
            return ResponseTool.success(count);
        }

    }


}
