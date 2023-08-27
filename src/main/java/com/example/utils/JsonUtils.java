package com.example.utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JsonUtils {

    private JsonUtils() {}

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper defaultObjectMapper = newDefaultMapper();
    private static volatile ObjectMapper objectMapper = null;

    private static ObjectMapper newDefaultMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //mapper.registerModule(new JsonOrgModule());
        return mapper;
    }

    /**
     * Get the ObjectMapper used to serialize and deserialize objects to and from JSON values.
     *
     * <p>This can be set to a custom implementation using Json.setObjectMapper.
     *
     * @return the ObjectMapper currently being used
     */
    public static ObjectMapper mapper() {
        if (objectMapper == null) {
            return defaultObjectMapper;
        } else {
            return objectMapper;
        }
    }

    public static Object convertJsonStringToObject(Class clazz, String content) {
        try {
            LOG.info("[convertJsonStringToObject]  mapping objectMapper {} to class {}", content ,clazz);
            return new ObjectMapper().readValue(content, clazz);
        } catch (Exception e) {
            LOG.error("[convertJsonStringToObject] Exception occurred", e);
            return null;
        }
    }

    public static <T> T getObjectFromString(Class<T> clazz, String content) {
        try {
            LOG.info("[convertJsonStringToObject]  mapping objectMapper {} to class {}", content ,clazz);
            return new ObjectMapper().readValue(content, clazz);
        } catch (Exception e) {
            LOG.error("[convertJsonStringToObject] Exception occurred", e);
            return null;
        }
    }

    private static String generateJson(Object o, boolean prettyPrint, boolean escapeNonASCII) {
        try {
            ObjectWriter writer = mapper().writer();
            if (prettyPrint) {
                writer = writer.with(SerializationFeature.INDENT_OUTPUT);
            }
            if (escapeNonASCII) {
                writer = writer.with(JsonGenerator.Feature.ESCAPE_NON_ASCII);
            }
            return writer.writeValueAsString(o);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateJson(Object o) {
        return generateJson(0, false, false);
    }

    /**
     * Convert an object to JsonNode.
     *
     * @param data Value to convert in Json.
     */
    public static JsonNode toJson(Object data) {
        try {
            return mapper().valueToTree(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert a JsonNode to a Java value
     *
     * @param json Json value to convert.
     * @param clazz Expected Java value type.
     */
    public static <A> A fromJson(JsonNode json, Class<A> clazz) {
        try {
            return mapper().treeToValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Creates a new empty ObjectNode. */
    public static ObjectNode newObject() {
        return mapper().createObjectNode();
    }

    /** Creates a new empty ArrayNode. */
    public static ArrayNode newArray() {
        return mapper().createArrayNode();
    }

    /** Convert a JsonNode to its string representation. */
    public static String stringify(JsonNode json) {
        return generateJson(json, false, false);
    }

    /** Convert a JsonNode to its string representation, escaping non-ascii characters. */
    public static String asciiStringify(JsonNode json) {
        return generateJson(json, false, true);
    }

    /** Convert a JsonNode to its string representation. */
    public static String prettyPrint(JsonNode json) {
        return generateJson(json, true, false);
    }

    /** Parse a String representing a json, and return it as a JsonNode. */
    public static JsonNode parse(String src) {
        try {
            return mapper().readTree(src);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    /** Parse a InputStream representing a json, and return it as a JsonNode. */
    public static JsonNode parse(java.io.InputStream src) {
        try {
            return mapper().readTree(src);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    /** Parse a byte array representing a json, and return it as a JsonNode. */
    public static JsonNode parse(byte[] src) {
        try {
            return mapper().readTree(src);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static void setObjectMapper(ObjectMapper mapper) {
        objectMapper = mapper;
    }

    /*public static <A> A fromJSONObject(JSONObject jsonObject, Class<A> clazz) {
        try {
            JsonNode jsonNode = mapper().readTree(jsonObject.toString());
            return fromJson(jsonNode, clazz);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   public static JSONObject toJSONObject(Object data) {
        try {
            JSONObject obj = mapper().convertValue(data, JSONObject.class);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    public static String asJsonString(Object o) {
        try {
            return mapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            String msg = "Could not convert object to Json";
            throw new IllegalArgumentException(msg, e);
        }
    }

    public static <A> A parseObject(String json, Class<A> clazz) {
        try {
            return mapper().readValue(json, clazz);
        } catch (IOException e) {
            String msg = "Could not parse Json to given object class";
            throw new IllegalArgumentException(msg, e);
        }
    }

    public static <T> List<T> readList(String str, Class<T> type) {
        return readList(str, ArrayList.class, type);
    }

    public static <T> List<T> readList(String str, Class<? extends Collection> type, Class<T> elementType) {
        try {
            return mapper().readValue(str, mapper().getTypeFactory().constructCollectionType(type, elementType));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isValidJson(String jsonInString) {
        try {
            objectMapper.readTree(jsonInString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}