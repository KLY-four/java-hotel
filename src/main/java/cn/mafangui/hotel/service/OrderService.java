package cn.mafangui.hotel.service;

import cn.mafangui.hotel.entity.Order;

import java.util.List;

public interface OrderService {
    int updateOrderStatusService(Order order);

    int insert(Order order);

    int addOrder(Order order);

    int select();

    List<Order> getOrderByPhone(String phone);

    int delete(Integer orderId);

    int batchDeleteOrder(String[] data);

    Order selectByOrderId(Integer orderId);

    Order selectByNameAndPhone(String name,String phone,String i);


    int update(Order record);

    int payOrder(int orderId);

    int cancelOrder(int orderId);

    Integer getOrderCount();

    List<Order> selectByUserId(int userId);

    List<Order> AllOrders();

    List<Order> UsersAllOrders(int userId);
}
