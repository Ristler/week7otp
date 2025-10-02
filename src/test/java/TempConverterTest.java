import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TempConverterTest {

    @Test
    void celsiusToFahrenheit() {
        assertEquals(212, TempConverter.convert("Celsius to fahrenheit", 100));
    }
    @Test
    void fahrenheitToCelsius() {
        assertEquals(100, TempConverter.convert("Fahrenheit to celsius", 212));
    }
    @Test
    void kelvinToCelsius() {
        assertEquals(100, TempConverter.convert("Kelvin to celsius", 373.15));
    }
    @Test
    void celsiusToKelvin() {
        assertEquals(373.15, TempConverter.convert("Celsius to kelvin", 100));
    }
}