import java.util.*;
import java.util.stream.Collectors;

/**
 *
 *  This program is written in Java 11
 *  Its related to set operation implemented using List
 *
 */
public class SetOperations {

    private static final String ELEMENT_IS_NOT_WITH_THE_RANGE_0_9_PLEASE_REENTER = "Element is not with in the range[0,9], please reenter.";
    private final static List<Integer> universalSet = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    private static final String VALID = "valid";
    private static final String ELEMENT_ALREADY_EXISTS_PLEASE_REENTER = "Element already exists, please reenter.";
    private static final String SET_A = "Set A: ";
    private static final String SET_B = "Set B: ";
    private static final String UNION_OF_SET_A_AND_B = "Union of Set A and B: ";
    private static final String INTERSECTION_OF_SET_A_AND_B = "Intersection of Set A and B: ";
    private static final String SET_B_S_COMPLEMENT = "Set B's Complement: ";
    private static final String PLEASE_ENTER_UNIQUE_ELEMENT_FOR_SET = "Please enter unique element for Set ";
    private static final String AND_ENTER_99_TO_END_THE_ENTERING = " and enter -99 to end the entering:";
    private static final String A = "A";
    private static final String B = "B";

    private static final String EMPTY_SET = "{}";
    private static final String PRODUCT_OF_INTERSECTION_OF_SET_A_AND_B_AND_A = "Product of (intersection of Set A and B) and A: ";

    public static void main(String[] args) {

        var scanner = new Scanner(System.in);

        // Input for set A
        var setA = getSetInput(scanner, A);
        // Input for set B
        var setB = getSetInput(scanner, B);

        // Perform set operations
        var union = union(setA, setB);
        var intersection = intersection(setA, setB);
        var complementB = complement(setB);
        var product = product(intersection, setA);

        // Print the results
        System.out.println(SET_A + arrayToString(setA));
        System.out.println(SET_B + arrayToString(setB));
        System.out.println(UNION_OF_SET_A_AND_B + arrayToString(union));
        System.out.println(INTERSECTION_OF_SET_A_AND_B + arrayToString(intersection));
        System.out.println(SET_B_S_COMPLEMENT + arrayToString(complementB));
        System.out.println(PRODUCT_OF_INTERSECTION_OF_SET_A_AND_B_AND_A + arrayToProductString(product));
    }

    private static List<Integer> getSetInput(Scanner scanner, String setName) {
        System.out.println(PLEASE_ENTER_UNIQUE_ELEMENT_FOR_SET + setName + AND_ENTER_99_TO_END_THE_ENTERING);
        var set = new ArrayList<Integer>(10);
        int num;
        do {
            // Read Input
            num = scanner.nextInt();

            if (num != -99) {
                // Validate
                var validationMessage = validate(set, num);
                if (!validationMessage.equalsIgnoreCase(VALID)) {
                    System.out.println(validationMessage);
                } else {
                    // If element is valid add the element to the set
                    set.add(num);
                }
            }
        } while (num != -99);
        return set;
    }

    private static String validate(ArrayList<Integer> set, Integer num) {
        return num >= 0 && num <= 9 ? (set.contains(num) ? ELEMENT_ALREADY_EXISTS_PLEASE_REENTER : VALID) : ELEMENT_IS_NOT_WITH_THE_RANGE_0_9_PLEASE_REENTER ;
    }

    private static List<Integer> union(List<Integer> setA, List<Integer> setB) {

        var union = new ArrayList<Integer>(setA.size() * setB.size());

        // Add all the unique elements of setA
        union.addAll(setA);

        //If setA is empty
        if(setA.isEmpty())
            union.addAll(setB);
        // Filter out duplicate elements of setA U setB for union set
        else
            setB.stream().filter(ele -> !union.contains(ele)).map(union::add);

        return union;
    }

    private static List<Integer> intersection(List<Integer> setA, List<Integer> setB) {
        return setA.size() >= setB.size() ? intersectionSet(setA, setB) : intersectionSet(setB, setA);
    }

    private static List<Integer> intersectionSet(List<Integer> primarySet, List<Integer> secondarySet) {
        return primarySet.stream().filter(secondarySet::contains).collect(Collectors.toList());
    }

    private static List<Integer> complement(List<Integer> set) {
        return universalSet.stream().filter(ele -> !set.contains(ele)).collect(Collectors.toList());
    }

    private static ArrayList<ArrayList<Integer>> product(List<Integer> intersection, List<Integer> setA) {
        var product = new ArrayList<ArrayList<Integer>>(intersection.size() * setA.size());

        for (var ele1 : intersection) {
            for (var ele2 : setA) {
                var list = new ArrayList<Integer>(2);
                list.add(ele1);
                list.add(ele2);
                product.add(list);
            }
        }

        return product;
    }

    private static String arrayToString(List<Integer> array) {

        var sb = new StringBuilder("{");

        if(!array.isEmpty()) {
            array.forEach(ele -> {
                // Last Element
                if (array.lastIndexOf(ele) == array.size() - 1)
                    sb.append(ele);
                    // Rest of the Elements
                else
                    sb.append(ele).append(", ");
            });
        }

        return sb.append("}").toString();
    }

    private static String arrayToProductString(ArrayList<ArrayList<Integer>> product) {
        var sb = new StringBuilder("{");
        if (!product.isEmpty()) {
            product.forEach(ele -> {
                // Last Element
                if (product.indexOf(ele) == product.lastIndexOf(ele))
                    sb.append("(").append(ele.get(0)).append(", ").append(ele.get(1)).append(")");
                    // Rest of the Elements
                else
                    sb.append("(").append(ele.get(0)).append(", ").append(ele.get(1)).append(")").append(", ");
            });
        }
        return sb.append("}").toString();
    }
}
