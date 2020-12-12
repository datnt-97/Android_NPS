package base.core.mudules.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import base.core.model.ServerModel;
import base.core.model.UserModel;
import base.core.model.metadata.BranchModel;
import base.core.model.metadata.ChannelModel;
import base.core.model.metadata.ContractModel;
import base.core.model.metadata.CustomerModel;
import base.core.model.metadata.EmployeeModel;
import base.core.model.metadata.ErrorGroupModel;
import base.core.model.metadata.ErrorHandleModel;
import base.core.model.metadata.ErrorModel;
import base.core.model.metadata.ErrorReasonDetailModel;
import base.core.model.metadata.ErrorReasonGroupModel;
import base.core.model.metadata.ErrorReasonModel;
import base.core.model.metadata.ProcessStatusModel;
import base.core.model.metadata.ProvinceModel;
import base.core.model.metadata.RequestStatusModel;
import base.core.model.metadata.RequestTypeModel;
import base.core.model.metadata.StockModel;
import base.core.mudules.database.DAO.BranchDAO;
import base.core.mudules.database.DAO.ChannelDAO;
import base.core.mudules.database.DAO.ContractDAO;
import base.core.mudules.database.DAO.CookieDAO;
import base.core.mudules.database.DAO.CustomerDAO;
import base.core.mudules.database.DAO.EmployeeDAO;
import base.core.mudules.database.DAO.ErrorDAO;
import base.core.mudules.database.DAO.ErrorGroupDAO;
import base.core.mudules.database.DAO.ErrorHandleDAO;
import base.core.mudules.database.DAO.ErrorReasonDAO;
import base.core.mudules.database.DAO.ErrorReasonDetailDAO;
import base.core.mudules.database.DAO.ErrorReasonGroupDAO;
import base.core.mudules.database.DAO.ProcessStatusDAO;
import base.core.mudules.database.DAO.ProvinceDAO;
import base.core.mudules.database.DAO.RequestStatusDAO;
import base.core.mudules.database.DAO.RequestTypeDAO;
import base.core.mudules.database.DAO.ServerDAO;
import base.core.mudules.database.DAO.StockDAO;
import base.core.mudules.database.DAO.UserDAO;

@Database(exportSchema = false, version = 18, entities = {CookieDB.class, UserModel.class, ServerModel.class,
        StockModel.class, ErrorModel.class, ProvinceModel.class, ErrorHandleModel.class, ErrorGroupModel.class,
        ErrorReasonModel.class, ErrorReasonDetailModel.class, ErrorReasonGroupModel.class, BranchModel.class, CustomerModel.class,
        EmployeeModel.class, RequestTypeModel.class, RequestStatusModel.class, ProcessStatusModel.class, ContractModel.class,
        ChannelModel.class})
public abstract class AppDatabase extends RoomDatabase {
    private static String DB_NAME = "app_db";
    private static AppDatabase instance;

    public abstract CookieDAO cookieDAO();

    public abstract UserDAO userDAO();

    public abstract ServerDAO serverDAO();

    public abstract StockDAO stockDAO();

    public abstract ProvinceDAO provinceDAO();

    public abstract BranchDAO branchDAO();

    public abstract CustomerDAO customerDAO();

    public abstract ErrorDAO errorDAO();

    public abstract ErrorGroupDAO errorGroupDAO();

    public abstract ErrorHandleDAO errorHandleDAO();

    public abstract ErrorReasonDAO errorReasonDAO();

    public abstract ErrorReasonDetailDAO errorReasonDetailDAO();

    public abstract ErrorReasonGroupDAO errorReasonGroupDAO();

    public abstract EmployeeDAO employeeDAO();

    public abstract RequestTypeDAO requestTypeDAO();

    public abstract RequestStatusDAO requestStatusDAO();

    public abstract ProcessStatusDAO processStatusDAO();

    public abstract ChannelDAO channelDAO();

    public abstract ContractDAO contractDAO();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
