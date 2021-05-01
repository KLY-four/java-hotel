package cn.mafangui.hotel.controller.worker;

import cn.mafangui.hotel.entity.RoomType;
import cn.mafangui.hotel.response.AjaxResult;
import cn.mafangui.hotel.response.ResponseTool;
import cn.mafangui.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/op/room-type")
public class OpRoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;


    /**
     * 所有房型
     * @return
     */
    @RequestMapping(value = "")
    public AjaxResult getAllRoomType(){
        List<RoomType> rooms = roomTypeService.findAllType();
        return ResponseTool.success(rooms);
    }

    /**
     * 查找有余量的房型
     * @return
     */
    @RequestMapping(value = "/rest")
    public AjaxResult findAllRestRoomType(){
        return ResponseTool.success(roomTypeService.findAllRestType());
    }

    /**
     * 根据id查找房型
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/{typeId}")
    public AjaxResult getById(@PathVariable int typeId){
        return ResponseTool.success(roomTypeService.selectById(typeId));
    }


    /**
     * 添加房型
     * @param roomType
     * @param price
     * @param discount
     * @param area
     * @param bedNum
     * @param bedSize
     * @param chuanghu
     * @param remark
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public AjaxResult addRoomType(String roomType,Double price,Double discount,Integer area,
                           Integer bedNum,String bedSize,Integer chuanghu,String remark,Integer rest){
        RoomType rt = new RoomType(roomType,remark,price,discount,area,bedNum,bedSize,chuanghu);
        rt.setRest(rest);
        int result = roomTypeService.insert(rt);
        if(result!=1) return ResponseTool.failed("添加失败");
        return ResponseTool.success("添加成功");
    }

    /**
     * 修改房型
     * @param typeId
     * @param roomType
     * @param price
     * @param discount
     * @param area
     * @param bedNum
     * @param bedSize
     * @param chuanghu
     * @param rest
     * @param remark
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public AjaxResult updateRoomType(Integer typeId,String roomType,Double price,Double discount,Integer area,
                                     Integer bedNum,String bedSize,Integer chuanghu,Integer rest,String remark){
        RoomType rt = new RoomType(roomType,remark,price,discount,area,bedNum,bedSize,chuanghu);
        rt.setTypeId(typeId);
        rt.setRest(rest);
        System.out.println(rt);
        int result = roomTypeService.update(rt);
        if(result!=1) return ResponseTool.failed("修改失败");
        return ResponseTool.success("修改成功");
    }

    /**
     * 删除房型
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/delete/{typeId}")
    public AjaxResult deleteRoomType(@PathVariable Integer typeId){
        int result = roomTypeService.delete(typeId);
        if(result!=1) return ResponseTool.failed("删除失败");
        return ResponseTool.success("删除成功");
    }
    @RequestMapping(method = RequestMethod.POST,value = "/delete")
    public AjaxResult deleteRoomType(String data){
        String substring = data.substring(0, data.length() - 1);
        String[] split = substring.split(",");
        int result=roomTypeService.batchDelete(split);
        if(result<=0){ return ResponseTool.failed("删除失败");}else {
        return ResponseTool.success("删除成功");}
    }



}
