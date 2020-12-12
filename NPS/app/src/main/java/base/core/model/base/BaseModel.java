package base.core.model.base;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.IOException;

import base.core.model.AtmModel;
import base.core.model.ResultLoginModel;
import base.core.model.ServerModel;
import base.core.model.UserModel;
import base.core.model.result.ResultBase;
import base.core.model.result.ResultModel;

@Entity
public abstract class BaseModel {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BaseModel(int id) {
        this.id = id;
    }

    public BaseModel() {
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public abstract String getString();
}
