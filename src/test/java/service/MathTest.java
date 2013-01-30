package service;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MathTest {

    @Test
    public void twoPlusTwo() { // Press Ctrl-Shift-F10 here to run only twoPlusTwo test
        assertThat( 2+2, is( 4 ) );
    }

    @Test
    public void twoPlusThree() { // Press Ctrl-Shift-F10 here to run only twoPlusThree test
        assertThat( 2+3, is( 5 ) );
    }
}
