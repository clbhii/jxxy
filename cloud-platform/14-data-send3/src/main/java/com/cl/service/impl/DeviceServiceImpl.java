package com.cl.service.impl;

import com.cl.common.Page;
import com.cl.dao.DeviceDAO;
import com.cl.dao.DeviceFieldDAO;
import com.cl.model.DeviceDO;
import com.cl.model.DeviceFieldDO;
import com.cl.model.dto.DeviceInfoDTO;
import com.cl.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * by cl at 2020/3/23 0023
 */
@Service
public class DeviceServiceImpl implements IDeviceService {
    @Autowired
    private DeviceDAO deviceDAO;
    @Autowired
    private DeviceFieldDAO deviceFieldDAO;

    @Transactional
    @Override
    public void insert(DeviceInfoDTO deviceInfoDTO) {
        DeviceDO deviceDO = deviceInfoDTO.getDeviceDO();
        deviceDAO.insert(deviceDO);
        for(DeviceFieldDO deviceFieldDO : deviceInfoDTO.getDeviceFieldDOList()) {
            deviceFieldDO.setDevId(deviceDO.getId());
            deviceFieldDAO.insert(deviceFieldDO);
        }
    }

    @Override
    public DeviceInfoDTO selectByPrimaryKey(Long id) {
        DeviceDO deviceDO = deviceDAO.selectByPrimaryKey(id);
        List<DeviceFieldDO> deviceFieldDOS = deviceFieldDAO.selectByDevId(id);
        DeviceInfoDTO deviceInfoDTO = new DeviceInfoDTO();
        deviceInfoDTO.setDeviceDO(deviceDO);
        deviceInfoDTO.setDeviceFieldDOList(deviceFieldDOS);
        return deviceInfoDTO;
    }

    @Transactional
    @Override
    public void updateByPrimaryKey(DeviceInfoDTO deviceInfoDTO) {
        DeviceDO deviceDO = deviceInfoDTO.getDeviceDO();
        deviceDAO.updateByPrimaryKey(deviceDO);
        deviceFieldDAO.deleteByDevId(deviceDO.getId());
        for(DeviceFieldDO deviceFieldDO : deviceInfoDTO.getDeviceFieldDOList()) {
            deviceFieldDO.setDevId(deviceDO.getId());
            deviceFieldDAO.insert(deviceFieldDO);
        }
        return ;
    }
    @Override
    public void deleteByPrimaryKey(Long id) {
        deviceDAO.deleteByPrimaryKey(id);
        deviceFieldDAO.deleteByDevId(id);;
    }

    @Override
    public Page<DeviceDO> pageDevice(String devNo, Integer curPage, Integer pageSize) {
        Page<DeviceDO> page = new Page<>();
        page.setCurrentIndex(curPage);
        page.setPageSize(pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("devNo", devNo);
        int total = deviceDAO.countList(map);
        List<DeviceDO> list = deviceDAO.selectList(map);
        page.setTotalNumber(total);
        page.setItems(list);
        return page;
    }
}
