package com.example.restservice.model;

import com.example.restservice.model.BookingOutput;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class BookingOutputTest {

    @Test
    public void givenNameOfFieldIsChanged_whenSerializing_thenCorrect()
            throws JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        BookingOutput bookingOutput = new BookingOutput(1, 100);

        final String dtoAsString = mapper.writeValueAsString(bookingOutput);
        assertThat(dtoAsString, containsString("car_id"));
    }
}
