package cn.mafangui.hotel.controller.user;

import cn.mafangui.hotel.entity.Order;
import cn.mafangui.hotel.entity.RoomType;
import cn.mafangui.hotel.entity.User;
import cn.mafangui.hotel.enums.OrderStatus;
import cn.mafangui.hotel.response.AjaxResult;
import cn.mafangui.hotel.response.MsgType;
import cn.mafangui.hotel.response.ResponseTool;
import cn.mafangui.hotel.service.OrderService;
import cn.mafangui.hotel.service.RoomService;
import cn.mafangui.hotel.service.RoomTypeService;
import cn.mafangui.hotel.service.UserService;
import com.mysql.cj.util.StringUtils;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 订单接口
 */
@RestController
@RequestMapping(value = "/user/order")
public class UserOrderController {

    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    /**
     * 添加预订
     * 订单状态默认为未付款状态
     * @return
     */
    @RequestMapping(value = "/add")
    public AjaxResult addOrder(@DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate,Integer roomId,String name,Double discount
                                ,Integer orderTypeId,String orderType,Integer userId,String phone,Integer roomTypeId,String roomType,
                                Integer orderDays, Integer orderStatus,Double orderCost, String vip){
        if(vip.equals("true")) {
             orderCost=orderCost*orderDays*discount/10;
        }
        String.format("%.2f", orderCost);
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
        order.setRoomTypeId(roomTypeId);
        order.setRoomType(roomType);
        if(roomId==null){
            RoomType type = roomTypeService.selectById(roomTypeId);
            List<String> typeId = type.getRoomId();
            roomId=Integer.valueOf(typeId.get(0));
        }
        order.setRoomNumber(roomId);
        int re = orderService.addOrder(order);
        if(re==1){
            int re1 = roomService.updateStatusByRoomNumber(roomId,2);
            return ResponseTool.success(MsgType.SUCCESS);
        }else {
            return ResponseTool.failed(MsgType.FAILED);
        }
    }


    /**
     * 删除订单
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/delete")
    public AjaxResult deleteOrderByUser(int orderId){
        Order order = new Order(orderId,OrderStatus.WAS_DELETED.getCode());
        int re =  orderService.update(order);
        if(re!=1) ResponseTool.failed(MsgType.FAILED);
        return ResponseTool.success(MsgType.SUCCESS);
    }


    /**
     * 订单支付
     * @param orderId
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/pay")
    public AjaxResult payOrder(Integer orderId,String username,String password){
        System.out.println(username+password);
        User user = userService.selectByUsernameAndPassword(username,password);
        if(user==null) return ResponseTool.failed("密码错误");
        int re = orderService.payOrder(orderId);
        if(re!=1) ResponseTool.failed(MsgType.FAILED);
        return ResponseTool.success("支付成功");
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/cancel")
    public AjaxResult cancelOrder(int orderId){
        int re = orderService.cancelOrder(orderId);
        if(re!=1) ResponseTool.failed(MsgType.FAILED);
        return ResponseTool.success();
    }


    /**
     * 客户查询个人所有订单（不包括被自己删除的）
     * @param userId
     * @return
     */
    @RequestMapping(value = "")
    public AjaxResult getAllByUser(int userId){
        return ResponseTool.success(orderService.UsersAllOrders(userId));
    }

    /**
     * 根据订单号查询订单
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/{orderId}")
    public AjaxResult getById(@PathVariable int orderId){
        Order order = orderService.selectByOrderId(orderId);
        if(order!=null) {
            return ResponseTool.success(order);
        }else {
            return ResponseTool.failed("");
        }
    }

}
