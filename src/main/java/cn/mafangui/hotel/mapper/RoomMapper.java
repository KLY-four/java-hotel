package cn.mafangui.hotel.mapper;

import cn.mafangui.hotel.entity.Room;
import cn.mafangui.hotel.entity.Worker;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface RoomMapper {
    Integer getCount();
    int deleteByPrimaryKey(Integer roomId);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer roomId);

    Room selectByNumber(String roomNumber);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    List<Room> selectByType(Integer typeId);

    List<Room> getByRoomNumberMapper(Integer roomId);

    List<Room> selectByStatus(Integer roomStatus);

    List<Room> selectAll();

    int batchDeleteByPrimaryKey(String[] data);

    Room randomSelectByTypeAndStatus(@Param("typeId") Integer typeId,@Param("roomStatus") Integer roomStatus);

    List<String> selectRoomIdByTypeId(Integer typeId);

    int updateStatusByRoomNumber(Map<String,Integer> map);

}
