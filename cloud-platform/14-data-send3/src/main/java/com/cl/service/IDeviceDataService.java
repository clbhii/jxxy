package com.cl.service;

public interface IDeviceDataService {

    public void reportData(String topic, String data);

    public void sendData(String topic, String data);
}
