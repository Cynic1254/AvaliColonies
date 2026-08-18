package com.cynic1254.proceduralcitizens.data.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListTypeAdapter implements TypeAdapterFactory {

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (!List.class.isAssignableFrom(type.getRawType()))
            return null;

        Type elementType = ((ParameterizedType) type.getType()).getActualTypeArguments()[0];
        TypeAdapter<?> elementAdapter = gson.getAdapter(TypeToken.get(elementType));

        return (TypeAdapter<T>) new TypeAdapter<List<?>>() {
            @Override
            public void write(com.google.gson.stream.JsonWriter out, List<?> value) {
                // Not strictly needed for read-only deserialization
            }

            @Override
            public List<?> read(com.google.gson.stream.JsonReader in) throws java.io.IOException {
                List<Object> list = new ArrayList<>();
                if (in.peek() == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
                    in.beginArray();
                    while (in.hasNext()) {
                        list.add(elementAdapter.read(in));
                    }
                    in.endArray();
                } else if (in.peek() != com.google.gson.stream.JsonToken.NULL) {
                    // Single item fallback
                    list.add(elementAdapter.read(in));
                } else {
                    in.nextNull();
                }
                return list;
            }
        };
    }
}
