package cn.mafangui.hotel.service;

import cn.mafangui.hotel.entity.Worker;

import java.util.List;

public interface WorkerService {
    int insert(Worker worker);
    List<Worker> getWorker(String phone);
    int delete(int workerId);
    int batchDelete(String[] data);
    int updateById(Worker worker);
    Worker selectById(int workerId);
    Worker selectByUsername(String username);
    List<Worker> findAll();
    List<Worker> selectByRole(String role);
    Worker login(String username,String password,String role);
    Worker login(String username,String password);
}
