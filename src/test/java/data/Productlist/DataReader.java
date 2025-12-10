package data.Productlist;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader
{
    public List<HashMap<String,String>> getData() throws IOException
    {
        // Step 1 : convert json to string
       String file =  FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\data\\Productlist\\ProductList.json"),
               StandardCharsets.UTF_8);

       // Step 2 : string to Jackson data binder
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> list =  mapper.readValue(file, new TypeReference<List<HashMap<String, String>>>()
        {
        });
        return list;
    }
}
