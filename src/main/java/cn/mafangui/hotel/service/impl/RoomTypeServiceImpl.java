package cn.mafangui.hotel.service.impl;


import cn.mafangui.hotel.entity.RoomType;
import cn.mafangui.hotel.mapper.RoomTypeMapper;
import cn.mafangui.hotel.service.RoomService;
import cn.mafangui.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Autowired
    private RoomService roomService;

    @Override
    public int decrement(int rest) {
        return roomTypeMapper.decrementMapper(rest);
    }

    @Override
    public int insert(RoomType roomType) {
        return roomTypeMapper.insertSelective(roomType);
    }

    @Override
    public int delete(int typeId) {
        return roomTypeMapper.deleteByPrimaryKey(typeId);
    }

    @Override
    public int batchDelete(String[] data) {
        return roomTypeMapper.batchDeleteByPrimaryKey(data);
    }

    @Override
    public int update(RoomType roomType) {
        return roomTypeMapper.updateByPrimaryKeySelective(roomType);
    }

    @Override
    public RoomType selectByName(String roomType) {
        return roomTypeMapper.selectByRoomType(roomType);
    }

    @Override
    public RoomType selectById(int typeId) {
        RoomType roomType = roomTypeMapper.selectByTypeId(typeId);
        List<String> strings = roomService.selectRoomIdByTypeId(typeId);
        roomType.setRoomId(strings);
        return roomType;
    }

    @Override
    public List<RoomType> findAllType() {
        List<RoomType> roomTypes = roomTypeMapper.selectAll();
        return roomTypes;
    }

    @Override
    public int updateRest(int typeId, int num) {
        RoomType rt =roomTypeMapper.selectByTypeId(typeId);
        if (rt.getRest() <= 0 && num < 0) return -1;
        rt.setRest(rt.getRest() + num);
        return roomTypeMapper.updateByPrimaryKeySelective(rt);
    }

    @Override
    public int addRest(int typeId) {
        RoomType rt =roomTypeMapper.selectByTypeId(typeId);
        rt.setTypeId(rt.getRest() +1);
        return roomTypeMapper.updateByPrimaryKeySelective(rt);
    }

    @Override
    public int minusRest(int typeId) {
        RoomType rt =roomTypeMapper.selectByTypeId(typeId);
        rt.setTypeId(rt.getRest() -1);
        return roomTypeMapper.updateByPrimaryKeySelective(rt);
    }

    @Override
    public List<RoomType> findAllRestType() {
        return roomTypeMapper.selectAllWithRest();
    }
}
