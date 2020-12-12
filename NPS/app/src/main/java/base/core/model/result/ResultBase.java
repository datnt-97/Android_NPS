package base.core.model.result;

import base.core.lib.constant;
import base.core.model.base.BaseModel;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultBase<Model extends BaseModel> {
    Model model;
    ObjectMapper objectMapper;

    public ResultBase(Model model) {
        this.model = model;
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        DateFormat df = new SimpleDateFormat(constant.FORMAT_TIME);
        objectMapper.setDateFormat(df);
    }

    public Model Parse(String jsons) throws IOException {
        model = (Model) objectMapper.readValue(jsons, model.getClass());
        return model;
    }

    public Model Parse(String jsons, PropertyNamingStrategy namingStrategy) throws IOException {
        objectMapper.setPropertyNamingStrategy(namingStrategy);
        model = (Model) objectMapper.readValue(jsons, model.getClass());
        return model;
    }

    public Model Parse(Object jsonsObj) throws IOException {
        try {
            String jsons = objectMapper.writeValueAsString(jsonsObj);
            model = (Model) objectMapper.readValue(jsons, model.getClass());
        } catch (Exception e) {
            throw e;
        }
        return model;
    }

    public Model Parse(Object jsonsObj, PropertyNamingStrategy namingStrategy) throws IOException {
        try {
            objectMapper.setPropertyNamingStrategy(namingStrategy);
            String jsons = objectMapper.writeValueAsString(jsonsObj);
            model = (Model) objectMapper.readValue(jsons, model.getClass());
        } catch (Exception e) {
            throw e;
        }
        return model;
    }

    public List<Model> ParseList(String jsons) throws IOException {
        List<Model> models = objectMapper.readValue(jsons, new TypeReference<List<Model>>() {
        });
        return models;
    }

    public List<Model> ParseList(String jsons, PropertyNamingStrategy namingStrategy) throws IOException {
        objectMapper.setPropertyNamingStrategy(namingStrategy);

        List<Model> models = objectMapper.readValue(jsons, new TypeReference<List<Model>>() {
        });
        return models;
    }


    public List<Model> ParseList(Object jsonsObj) throws IOException {
        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(List.class, model.getClass());
        String jsons = objectMapper.writeValueAsString(jsonsObj);
        List<Model> models = objectMapper.readValue(jsons, typeReference);
        return models;
    }

    public List<Model> ParseList(Object jsonsObj, PropertyNamingStrategy namingStrategy) throws IOException {
        objectMapper.setPropertyNamingStrategy(namingStrategy);

        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(List.class, model.getClass());
        String jsons = objectMapper.writeValueAsString(jsonsObj);
        List<Model> models = objectMapper.readValue(jsons, typeReference);
        return models;
    }
}
