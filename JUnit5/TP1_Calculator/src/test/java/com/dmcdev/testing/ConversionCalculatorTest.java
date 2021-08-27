package com.dmcdev.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.assertj.core.api.Assertions.*;

/*
(1) @Tag désigne tous les tests de la classe comme étant des tests de conversion, avec un tag nommé "ConversionTests".
(2) @DisplayName vous permet de nommer vos tests de façon lisible par tous.
(3) @Nested vous permet de grouper vos tests dans une classe interne. Avec @Nested, si un seul test échoue, tout le groupe désigné par cette annotation échoue !
(4) Vous pouvez ajouter  @Displayname et  @Tag à chaque bloc  @Test et  @Nested.
*/

@Tag("ConversionTests") // (1)
@DisplayName("Réussir à convertir entre différentes unités.") // (2)
public class ConversionCalculatorTest {

    private ConversionCalculator calculatorUnderTest = new ConversionCalculator();

    @Nested // (3)
    @Tag("TemperatureTests") // (4)
    @DisplayName("Réussir à convertir des températures") // (4)
    class TemperatureTests {
        @Test
        @DisplayName("Soit une T° à 0°C, lorsque l'on convertit en °F, alors on obtient 32°F.")
        public void celsiusToFahrenheit_returnsAFahrenheitTempurature_whenCelsiusIsZero() {
            Double actualFahrenheit = calculatorUnderTest.celsiusToFahrenheit(0.0);
            assertThat(actualFahrenheit).isCloseTo(32.0, withinPercentage(0.01));
        }

        @Test
        @DisplayName("Soit une T° à 32°F, lorsque l'on convertit en °C, alors on obtient 0°C.")
        public void fahrenheitToCelsius_returnsZeroCelciusTempurature_whenThirtyTwo() {
            Double actualCelsius = calculatorUnderTest.fahrenheitToCelsius(32.0);
            assertThat(actualCelsius).isCloseTo(0.0, withinPercentage(0.01));
        }
    }

    @Test
    @DisplayName("Soit un volume de 3.78541 litres, en gallons, on obtient 1 gallon.")
    public void litresToGallons_returnsOneGallon_whenConvertingTheEquivalentLitres() {
        Double actualLitres = calculatorUnderTest.litresToGallons(3.78541);
        assertThat(actualLitres).isCloseTo(1.0, withinPercentage(0.01));
    }

    @Test
    @DisplayName("L'aire d'un disque de rayon 1 doit valoir PI.")
    public void radiusToAreaOfCircle_returnsPi_whenWeHaveARadiusOfOne() {
        Double actualArea = calculatorUnderTest.radiusToAreaOfCircle(1.0);
        assertThat(actualArea).isCloseTo(PI, withinPercentage(0.01));
    }
}

/*
(1) Ligne 1 : @Tag désigne tous les tests de la classe comme étant des tests de conversion, avec un tag nommé "ConversionTests".
(2) Ligne 2 : @DisplayName vous permet de nommer vos tests de façon lisible par tous.
(3) Ligne 7 : @Nested vous permet de grouper vos tests dans une classe interne. Avec @Nested, si un seul test échoue, tout le groupe désigné par cette annotation échoue !
(4) Ligne 8-9 : vous pouvez ajouter  @Displayname et  @Tag à chaque bloc  @Test et  @Nested.
*/
