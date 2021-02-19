package cn.mafangui.hotel.service;

import cn.mafangui.hotel.entity.Room;

import java.util.List;


public interface RoomService {
    int insert(Room room);
    int delete(int roomId);
    List<Room> getByRoomNumber(Integer RoomNumber);
    int update(Room room);
    Room selectById(int roomId);
    Room selectByNumber(String roomNumber);
    List<Room> selectByStatus(int roomStatus);
    List<Room> selectByType(int typeId);
    List<Room> selectAll();
    public int batchDelete(String[] data);
    int orderRoom(int typeId);
    int inRoom(int typeId);
    int outRoom(int typeId);
}
