import java.util.*;
import java.util.stream.Collectors;

public class WardRobe {
    private static final WardRobeElement[] wardRobeElements = new WardRobeElement[]{
            WardRobeElement.Fifty,
            WardRobeElement.SeventyFive,
            WardRobeElement.OneHundred,
            WardRobeElement.OneHundredTwenty};
    private final int sizeOfWall = 250;

    private Set<List<WardRobeElement>> validCombinations;

    public WardRobe() {
        this.validCombinations = new HashSet<>();
    }

    public Set<List<WardRobeElement>> getValidCombinations() {
        return validCombinations;
    }

    public Set<List<WardRobeElement>> combine() {
        return Set.of(List.of(WardRobeElement.Fifty),
                List.of(WardRobeElement.SeventyFive),
                List.of(WardRobeElement.OneHundred),
                List.of(WardRobeElement.OneHundredTwenty));
    }

    public Set<List<WardRobeElement>> combine(Set<List<WardRobeElement>> combinations) {
        Set<List<WardRobeElement>> nextCombinations = new HashSet<>();
        for (List<WardRobeElement> list : combinations) {
            for (int i = 0; i < 4; i++) {
                var newList = new ArrayList<>(list);
                newList.add(wardRobeElements[i]);
                newList.sort(Comparator.naturalOrder());
                nextCombinations.add(newList);
            }
        }
        return nextCombinations;
    }

    public int countCandidateCombinations(Set<List<WardRobeElement>> combinations) {
        int amountOfCandidateCombinations = 0;
        for (List<WardRobeElement> combination : combinations) {
            int size = combination.stream().reduce(0, (partialResult, element) -> partialResult + element.size, Integer::sum);
            if (size < sizeOfWall) amountOfCandidateCombinations++;
        }
        return amountOfCandidateCombinations;
    }

    public void storeValidCombinations(Set<List<WardRobeElement>> combinations) {
        for (List<WardRobeElement> combination : combinations) {
            if (isValidCombination(combination)) {
                validCombinations.add(combination);
            }
        }
    }

    private boolean isValidCombination(List<WardRobeElement> combination) {
        int size = combination.stream().reduce(0, (partialResult, element) -> partialResult + element.size, Integer::sum);
        return size == 250;
    }

    public static void main(String[] args) {
        var wardRobe = new WardRobe();

        var combinations = wardRobe.combine();

        while (wardRobe.countCandidateCombinations(combinations) > 0) {
            wardRobe.storeValidCombinations(combinations);
            combinations = wardRobe.combine(combinations);
        }

        Comparator<List<WardRobeElement>> combinationComparator = (e1, e2) -> e1.stream().reduce(
                0, (partialResult, element) -> partialResult + element.value, Integer::sum).compareTo(e2.stream().reduce(
                0, (partialResult, element) -> partialResult + element.value, Integer::sum
        ));
        var validCombinations = wardRobe.getValidCombinations().stream().sorted(combinationComparator).collect(Collectors.toList());

        for (var combination : validCombinations) {
            int value = combination.stream().reduce(0, (partialResult, element) -> partialResult + element.value, Integer::sum);
            System.out.println(combination + " costs: " + value);
        }

    }
}
