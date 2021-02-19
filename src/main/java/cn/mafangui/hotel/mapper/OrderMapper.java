package cn.mafangui.hotel.mapper;

import cn.mafangui.hotel.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderMapper {

    int deleteByPrimaryKey(Integer orderId);

    int batchDeleteByPrimaryKey(String[] data);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByOrderId(Integer orderId);

    Order selectByNameAndPhone(Order record);

    List<Order> getOrderByPhoneMapper(String phone);

    int updateByPrimaryKeySelective(Order record);

    int updateOrderStatusDao(Order status);

    int updateByPrimaryKey(Order record);

    int selectMapper();

    Integer getOrderCount();

    List<Order> selectAll();

    List<Order> selectByUserId(Integer userId);

    List<Order> selectAllByUser(@Param("userId") Integer userId,@Param("orderStatus") Integer orderStatus);


}
