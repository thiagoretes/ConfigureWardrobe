import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WardRobeTest {

    private WardRobe wardRobe;

    @BeforeEach
    void setUp() {
        wardRobe = new WardRobe();
    }

    @Test
    void shouldReturnAllWardrobeElements() {
        Set<List<WardRobeElement>> elements = Set.of(List.of(WardRobeElement.Fifty),
                List.of(WardRobeElement.SeventyFive),
                List.of(WardRobeElement.OneHundred),
                List.of(WardRobeElement.OneHundredTwenty));

        assertEquals(elements, wardRobe.combine());
    }

    @Test
    void shouldCombineElementWithAllElements() {
        var element = Set.of(List.of(WardRobeElement.Fifty));
        var elements = Set.of(List.of(WardRobeElement.Fifty, WardRobeElement.Fifty),
                List.of(WardRobeElement.Fifty, WardRobeElement.SeventyFive),
                List.of(WardRobeElement.Fifty, WardRobeElement.OneHundred),
                List.of(WardRobeElement.Fifty, WardRobeElement.OneHundredTwenty)
        );
        assertEquals(elements, wardRobe.combine(element));
    }

    @Test
    void shouldCheckHowManyCanStillFitTheWall() {
        var elements = Set.of(List.of(WardRobeElement.Fifty, WardRobeElement.Fifty),
                List.of(WardRobeElement.Fifty, WardRobeElement.SeventyFive),
                List.of(WardRobeElement.Fifty, WardRobeElement.OneHundred),
                List.of(WardRobeElement.Fifty, WardRobeElement.OneHundredTwenty),
                List.of(WardRobeElement.Fifty, WardRobeElement.Fifty, WardRobeElement.Fifty),
                List.of(WardRobeElement.Fifty, WardRobeElement.OneHundredTwenty, WardRobeElement.OneHundred),
                List.of(WardRobeElement.Fifty, WardRobeElement.Fifty, WardRobeElement.Fifty, WardRobeElement.Fifty, WardRobeElement.Fifty)
        );

        assertEquals(5, wardRobe.countCandidateCombinations(elements));
    }

    @Test
    void shouldStoreCombinationsThatFitTheWall() {

        var combinations = Set.of(List.of(WardRobeElement.Fifty, WardRobeElement.Fifty),
                List.of(WardRobeElement.Fifty, WardRobeElement.SeventyFive),
                List.of(WardRobeElement.Fifty, WardRobeElement.OneHundred),
                List.of(WardRobeElement.Fifty, WardRobeElement.OneHundred, WardRobeElement.OneHundred),
                List.of(WardRobeElement.Fifty, WardRobeElement.OneHundredTwenty),
                List.of(WardRobeElement.Fifty, WardRobeElement.Fifty, WardRobeElement.Fifty),
                List.of(WardRobeElement.Fifty, WardRobeElement.OneHundredTwenty, WardRobeElement.OneHundred),
                List.of(WardRobeElement.Fifty, WardRobeElement.Fifty, WardRobeElement.Fifty, WardRobeElement.Fifty, WardRobeElement.Fifty)
        );
        var validCombinations = Set.of(
                List.of(WardRobeElement.Fifty, WardRobeElement.Fifty, WardRobeElement.Fifty, WardRobeElement.Fifty, WardRobeElement.Fifty),
                List.of(WardRobeElement.Fifty, WardRobeElement.OneHundred, WardRobeElement.OneHundred)
        );

        wardRobe.storeValidCombinations(combinations);

        assertEquals(validCombinations, wardRobe.getValidCombinations());
    }
}