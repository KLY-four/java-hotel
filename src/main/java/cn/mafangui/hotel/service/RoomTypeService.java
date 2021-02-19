package cn.mafangui.hotel.service;

import cn.mafangui.hotel.entity.RoomType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RoomTypeService {
    int decrement(int rest);

    int insert(RoomType roomType);

    int delete(int typeId);

    public int batchDelete(String[] data) ;

    int update(RoomType roomType);

    RoomType selectByName(String roomType);

    RoomType selectById(int typeId);

    List<RoomType> findAllType();

    int updateRest(int typeId,int num);

    int addRest(int typeId);

    int minusRest(int typeId);

    List<RoomType> findAllRestType();
}
