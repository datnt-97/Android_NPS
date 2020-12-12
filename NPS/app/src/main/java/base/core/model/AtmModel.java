package base.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import org.json.JSONObject;

import base.core.model.base.BaseModel;
import base.core.model.result.ResultBase;
import base.core.model.result.ResultModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AtmModel extends BaseModel {
    private String serialno;
    private String address;
    private String type;
    private String model;
    private String area;
    private String customer;
    private long handed;
    private long deploy;
    private String key;
    private double lat;
    private double lng;
    private String province;
    private String tid;
    private String stock_name;
    private int stock;


    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public long getHanded() {
        return handed;
    }

    public void setHanded(long handed) {
        this.handed = handed;
    }

    public long getDeploy() {
        return deploy;
    }

    public void setDeploy(long deploy) {
        this.deploy = deploy;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public AtmModel() {
    }

    @Override
    public String getString() {
        return null;
    }
}
