import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {
    public static void main(String[] args) throws Exception {
        String json = "{\"name\":\"Brennan\",\"age\":23,\"city\":\"Knoxville\"}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        String name = root.get("name").asText();
        int age = root.get("age").asInt();
        String city = root.get("city").asText();
        
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
    }
}