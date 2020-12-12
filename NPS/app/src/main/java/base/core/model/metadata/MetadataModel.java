package base.core.model.metadata;

import androidx.room.Dao;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import base.MainActivity;
import base.core.lib.util;
import base.core.model.base.BaseModel;
import base.core.model.result.ResultBase;
import base.core.mudules.database.AppDatabase;
import base.core.mudules.database.CookieDB;
import base.core.mudules.database.DAO.BaseDAO;
import base.core.mudules.database.DAO.DAO;

public class MetadataModel extends BaseModel {
    private List<ErrorModel> errorModels;
    private List<ProvinceModel> provinceModels;
    private List<StockModel> stockModels;
    private List<CustomerModel> customerModels;
    private List<BranchModel> branchModels;
    private List<ErrorGroupModel> errorGroupModels;
    private List<ErrorHandleModel> errorHandleModels;
    private List<ErrorReasonModel> errorReasonModels;
    private List<ErrorReasonDetailModel> errorReasonDetailModels;
    private List<ErrorReasonGroupModel> errorReasonGroupModels;
    private List<RequestTypeModel> requestTypeModels;
    private List<EmployeeModel> employeeModels;
    private List<RequestStatusModel> requestStatusModels;
    private List<ProcessStatusModel> processStatusModels;
    private List<ChannelModel> channelModels;
    private List<ContractModel> contractModels;

    public List<RequestStatusModel> getRequestStatusModels() {
        return requestStatusModels;
    }

    public void setRequestStatusModels(List<RequestStatusModel> requestStatusModels) {
        this.requestStatusModels = requestStatusModels;
    }

    public List<ProcessStatusModel> getProcessStatusModels() {
        return processStatusModels;
    }

    public void setProcessStatusModels(List<ProcessStatusModel> processStatusModels) {
        this.processStatusModels = processStatusModels;
    }

    public List<ChannelModel> getChannelModels() {
        return channelModels;
    }

    public void setChannelModels(List<ChannelModel> channelModels) {
        this.channelModels = channelModels;
    }

    public List<ContractModel> getContractModels() {
        return contractModels;
    }

    public void setContractModels(List<ContractModel> contractModels) {
        this.contractModels = contractModels;
    }

    public List<EmployeeModel> getEmployeeModels() {
        return employeeModels;
    }

    public void setEmployeeModels(List<EmployeeModel> employeeModels) {
        this.employeeModels = employeeModels;
    }

    public List<RequestTypeModel> getRequestTypeModels() {
        return requestTypeModels;
    }

    public void setRequestTypeModels(List<RequestTypeModel> requestTypeModels) {
        this.requestTypeModels = requestTypeModels;
    }

    public List<CustomerModel> getCustomerModels() {
        return customerModels;
    }

    public void setCustomerModels(List<CustomerModel> customerModels) {
        this.customerModels = customerModels;
    }

    public List<BranchModel> getBranchModels() {
        return branchModels;
    }

    public void setBranchModels(List<BranchModel> branchModels) {
        this.branchModels = branchModels;
    }

    public List<ErrorGroupModel> getErrorGroupModels() {
        return errorGroupModels;
    }

    public void setErrorGroupModels(List<ErrorGroupModel> errorGroupModels) {
        this.errorGroupModels = errorGroupModels;
    }

    public List<ErrorHandleModel> getErrorHandleModels() {
        return errorHandleModels;
    }

    public void setErrorHandleModels(List<ErrorHandleModel> errorHandleModels) {
        this.errorHandleModels = errorHandleModels;
    }

    public List<ErrorReasonModel> getErrorReasonModels() {
        return errorReasonModels;
    }

    public void setErrorReasonModels(List<ErrorReasonModel> errorReasonModels) {
        this.errorReasonModels = errorReasonModels;
    }

    public List<ErrorReasonDetailModel> getErrorReasonDetailModels() {
        return errorReasonDetailModels;
    }

    public void setErrorReasonDetailModels(List<ErrorReasonDetailModel> errorReasonDetailModels) {
        this.errorReasonDetailModels = errorReasonDetailModels;
    }

    public List<ErrorReasonGroupModel> getErrorReasonGroupModels() {
        return errorReasonGroupModels;
    }

    public void setErrorReasonGroupModels(List<ErrorReasonGroupModel> errorReasonGroupModels) {
        this.errorReasonGroupModels = errorReasonGroupModels;
    }

    public List<ErrorModel> getErrorModels() {
        return errorModels;
    }

    public void setErrorModels(List<ErrorModel> errorModels) {
        this.errorModels = errorModels;
    }

    public List<ProvinceModel> getProvinceModels() {
        return provinceModels;
    }

    public void setProvinceModels(List<ProvinceModel> provinceModels) {
        this.provinceModels = provinceModels;
    }

    public List<StockModel> getStockModels() {
        return stockModels;
    }

    public void setStockModels(List<StockModel> stockModels) {
        this.stockModels = stockModels;
    }

    public void load() throws Exception {
        try {
            String[] methodName = new String[]{"deleteAll", "insertAll", "findAll"};
            Field[] fields = MetadataModel.class.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                //Get value Meta Data
                Field field = fields[i];
                String name = field.getName().replace("Models", "");
                String nameClass = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, (name + "Model"));
                Class<?> model = Class.forName("base.core.model.metadata." + nameClass);
                ArrayList<?> al = util.listOf(model);
                al = (ArrayList<?>) util.getProperty(this, field.getName());

                //Get Dao
                Method methodDao = MainActivity.appDatabase.getClass().getDeclaredMethod(name + "DAO", null);
                Object dao = methodDao.invoke(MainActivity.appDatabase, null);
                //Load Meta Data

                Method methodFindAll = dao.getClass().getMethod(methodName[2], null);
                al = (ArrayList<?>) methodFindAll.invoke(dao, null);
                this.getClass().getMethod("set" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field.getName()), List.class).invoke(this, al);
            }
        } catch (Exception e) {
            throw e;
        }

    }

    public void localProcess() throws Exception {
        try {
            String[] methodName = new String[]{"deleteAll", "insertAll", "findAll"};
            Field[] fields = MetadataModel.class.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                //Get value Meta Data
                Field field = fields[i];
                String name = field.getName().replace("Models", "");
                String nameClass = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, (name + "Model"));
                Class<?> model = Class.forName("base.core.model.metadata." + nameClass);
                ArrayList<?> al = util.listOf(model);
                al = (ArrayList<?>) util.getProperty(this, field.getName());

                //Get Dao
                Method methodDao = MainActivity.appDatabase.getClass().getDeclaredMethod(name + "DAO", null);
                Object dao = methodDao.invoke(MainActivity.appDatabase, null);
                //Delete Meta Data

                Method methodDeleteAll = dao.getClass().getMethod(methodName[0], List.class);
                methodDeleteAll.invoke(dao, al);
                //Add Meta Data
                Method methodInsertAll = dao.getClass().getMethod(methodName[1], List.class);
                methodInsertAll.invoke(dao, al);
                //Load Meta Data

                Method methodFindAll = dao.getClass().getMethod(methodName[2], null);
                al = (ArrayList<?>) methodFindAll.invoke(dao, null);
                this.getClass().getMethod("set" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field.getName()), List.class).invoke(this, al);
            }
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public String getString() {
        return null;
    }
}
