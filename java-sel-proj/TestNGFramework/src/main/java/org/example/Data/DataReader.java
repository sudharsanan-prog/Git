package org.example.Data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

//This class method was used in BaseClass for access to main class so we put that in the baseclass
public class DataReader {

    public List<HashMap<String,String>> getJsonDataToMap() throws IOException {
        //reading the file from the source and giving the encoding format as second argument
       String jsonContent =  FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\main\\java\\org\\example\\Data\\purchaseOrder.json"), StandardCharsets.UTF_8);

        //Converting String to HashMap
        //For converting this we require Jackson DataBind dependency
        ObjectMapper mapper = new ObjectMapper();//this method is from the Jackson which provides classes for converting json to String
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
            //This will take the json in jsonContent and using TypeReference we are giving the 2d array to convert to Hashmap of string array and return as List
        });
        return data;
    }
}
