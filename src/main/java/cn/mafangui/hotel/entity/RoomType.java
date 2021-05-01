package cn.mafangui.hotel.entity;

import java.util.Date;
import java.util.List;

public class RoomType {
    private Integer typeId;

    private String roomType;

    private String remark;

    private Double price;

    private Double discount;

    private Integer area;

    private Integer bedNum;

    private String bedSize;

    private Integer chuanghu;

    private Integer rest;

    private List<String> roomId;

    public Integer getChuanghu() {
        return chuanghu;
    }

    public void setChuanghu(Integer chuanghu) {
        this.chuanghu = chuanghu;
    }

    public List<String> getRoomId() {
        return roomId;
    }

    public void setRoomId(List<String> roomId) {
        this.roomId = roomId;
    }

    public Integer getRest() {
        return rest;
    }

    public void setRest(Integer rest) {
        this.rest = rest;
    }

    private Date createTime;

    private Date updateTime;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType == null ? null : roomType.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getBedNum() {
        return bedNum;
    }

    public void setBedNum(Integer bedNum) {
        this.bedNum = bedNum;
    }

    public String getBedSize() {
        return bedSize;
    }

    public void setBedSize(String bedSize) {
        this.bedSize = bedSize == null ? null : bedSize.trim();
    }

    public Integer getWindow() {
        return chuanghu;
    }

    public void setWindow(Integer window) {
        this.chuanghu = window;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public RoomType() {
        this.rest = 0;
    }

    public RoomType(String roomType, String remark, Double price, Double discount, Integer area, Integer bedNum, String bedSize, Integer window) {
        this.roomType = roomType;
        this.remark = remark;
        this.price = price;
        this.discount = discount;
        this.area = area;
        this.bedNum = bedNum;
        this.bedSize = bedSize;
        this.chuanghu = window;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "typeId=" + typeId +
                ", roomType='" + roomType + '\'' +
                ", remark='" + remark + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", area=" + area +
                ", bedNum=" + bedNum +
                ", bedSize='" + bedSize + '\'' +
                ", chuanghu=" + chuanghu +
                ", rest=" + rest +
                ", roomId=" + roomId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
