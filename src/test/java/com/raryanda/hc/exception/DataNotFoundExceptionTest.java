package com.raryanda.hc.exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DataNotFoundExceptionTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void dataNotFoundException() {
        expectedException.expectMessage("User not found with id = 4");
        throw new DataNotFoundException("User", "id", 4);
    }
}
