package ru.rombok.stub.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class TestObjectMapper extends ObjectMapper {

    public TestObjectMapper() {
        this.registerModule(new JavaTimeModule());
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.setDateFormat(new SimpleDateFormat("MM-dd-yyyy"));
        this.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        this.enable(SerializationFeature.INDENT_OUTPUT);
        this.setDefaultPrettyPrinter(new MyDefaultPrettyPrinter());
    }

    protected static class MyDefaultPrettyPrinter extends DefaultPrettyPrinter {
        private static final long serialVersionUID = -7435088368407606203L;

        public MyDefaultPrettyPrinter() {
            this._objectIndenter = new DefaultIndenter("  ", "\n");
            this._arrayIndenter = _objectIndenter;
        }

        @Override
        public DefaultPrettyPrinter createInstance() {
            return new MyDefaultPrettyPrinter();
        }

        @Override
        public void writeObjectFieldValueSeparator(JsonGenerator jg) throws IOException {
            jg.writeRaw(": ");
        }
    }
}
