package cn.mafangui.hotel.mapper;

import cn.mafangui.hotel.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderMapper {

    Integer deleteByPrimaryKey(Integer orderId);

    Integer batchDeleteByPrimaryKey(String[] data);

    Integer insert(Order record);

    Integer insertSelective(Order record);

    Order selectByOrderId(Integer orderId);

    Order selectByNameAndPhone(Order record);

    List<Order> getOrderByPhoneMapper(String phone);

    Integer updateByPrimaryKeySelective(Order record);

    Integer updateOrderStatusDao(Order status);

    int updateByPrimaryKey(Order record);

    Integer selectMapper();

    Integer getOrderCount();

    List<Order> selectAll();

    List<Order> selectByUserId(Integer userId);

    List<Order> selectAllByUser(@Param("userId") Integer userId,@Param("orderStatus") Integer orderStatus);


}
