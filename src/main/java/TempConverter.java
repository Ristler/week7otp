
public class TempConverter {

        public static double convert(String choice, double value) {
            return switch (choice) {
                case "Celsius to fahrenheit" -> value * 9 / 5 + 32;
                case "Fahrenheit to celsius" -> (value - 32) * 5 / 9;
                case "Kelvin to celsius" -> value - 273.15;
                case "Celsius to kelvin" -> value + 273.15;
                default -> Double.NaN;
            };
        }
    }
