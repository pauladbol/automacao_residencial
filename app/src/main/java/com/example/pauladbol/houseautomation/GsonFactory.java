package com.example.pauladbol.houseautomation;

/*import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializer;*/

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.sql.Timestamp;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by pauladbol on 2017-06-12.
 */
public class GsonFactory
{
    public static Gson createGson()
    {
        return new GsonBuilder().registerTypeAdapter(Date.class, new DateParser())
                .registerTypeAdapter(java.sql.Date.class, new SqlDateParser())
                .registerTypeAdapter(java.sql.Time.class, new SqlTimeParser())
                .registerTypeAdapter(Timestamp.class, new TimestampParser()).create();
    }

    private static class DateParser implements JsonDeserializer<Date>, JsonSerializer<Date>
    {

        @Override
        public Date deserialize(JsonElement jsonElement, Type typeOF,
                                JsonDeserializationContext context) throws JsonParseException
        {
            Date date = TimeToolkit.parseString(jsonElement.getAsString(), TimeToolkit.STANDARD_DATE_TIME_FORMAT);

            if (date == null)
            {
                date = TimeToolkit.parseString(jsonElement.getAsString(), TimeToolkit.STANDARD_DATE_FORMAT);
            }

            if (date == null)
            {
                date = TimeToolkit.parseString(jsonElement.getAsString(), TimeToolkit.STANDARD_TIME_FORMAT);
            }

            return date;
        }

        @Override
        public JsonElement serialize(Date date, Type type, JsonSerializationContext context)
        {
            return new JsonPrimitive(TimeToolkit.toString(date, TimeToolkit.STANDARD_DATE_TIME_FORMAT));
        }
    }

    private static class TimestampParser implements JsonDeserializer<Timestamp>, JsonSerializer<Timestamp>
    {
        @Override
        public Timestamp deserialize(JsonElement jsonElement, Type typeOF,
                                     JsonDeserializationContext context) throws JsonParseException
        {
            Date date = TimeToolkit.parseString(jsonElement.getAsString(), TimeToolkit.STANDARD_DATE_TIME_FORMAT);

            if (date == null)
            {
                date = TimeToolkit.parseString(jsonElement.getAsString(), TimeToolkit.STANDARD_DATE_FORMAT);
            }

            if (date == null)
            {
                date = TimeToolkit.parseString(jsonElement.getAsString(), TimeToolkit.STANDARD_TIME_FORMAT);
            }

            return date == null ? null : new Timestamp(date.getTime());
        }

        @Override
        public JsonElement serialize(Timestamp date, Type type, JsonSerializationContext context)
        {
            return new JsonPrimitive(TimeToolkit.toString(date, TimeToolkit.STANDARD_DATE_TIME_FORMAT));
        }
    }

    private static class SqlDateParser implements JsonDeserializer<java.sql.Date>, JsonSerializer<java.sql.Date>
    {

        @Override
        public java.sql.Date deserialize(JsonElement jsonElement, Type typeOF,
                                         JsonDeserializationContext context) throws JsonParseException
        {
            try
            {
                return new java.sql.Date(TimeToolkit.parseString(jsonElement.getAsString(), TimeToolkit.STANDARD_DATE_FORMAT).getTime());
            } catch (Exception e)
            {
                return null;
            }
        }

        @Override
        public JsonElement serialize(java.sql.Date date, Type type, JsonSerializationContext context)
        {
            return new JsonPrimitive(TimeToolkit.toString(date, TimeToolkit.STANDARD_DATE_FORMAT));
        }
    }

    private static class SqlTimeParser implements JsonDeserializer<java.sql.Time>, JsonSerializer<java.sql.Time>
    {

        @Override
        public java.sql.Time deserialize(JsonElement jsonElement, Type typeOF,
                                         JsonDeserializationContext context) throws JsonParseException
        {
            try
            {
                return new java.sql.Time(TimeToolkit.parseString(jsonElement.getAsString(), TimeToolkit.STANDARD_TIME_FORMAT).getTime());
            } catch (Exception e)
            {
                return null;
            }
        }

        @Override
        public JsonElement serialize(java.sql.Time date, Type type, JsonSerializationContext context)
        {
            return new JsonPrimitive(TimeToolkit.toString(date, TimeToolkit.STANDARD_TIME_FORMAT));
        }
    }
}

