package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<String> book = new ArrayList<>();
    private static final List<String> names = new ArrayList<>();

    private static SearchAlgorithm algorithm;
    private static List<String> requests;
    private static MessageBuilder builder;

    public static void main(String[] args) {
        init();

        long timePassed = performLinearSearch();
        performJumpSearch(timePassed);
        performBinarySearch();
        performHashSearch();
    }

    private static void performJumpSearch(long timePassed) {
        int found;
        long start;

        System.out.println("Start searching (bubble sort + jump search)...");

        JumpSearch search = new JumpSearch();
        search.setLimit(timePassed / 2);
        long startSort = System.currentTimeMillis();
        search.sortArray(names);

        if (!search.isFinished()) {
            start = System.currentTimeMillis();
            algorithm = new LinearSearch();
            algorithm.sortArray(names);
            found = algorithm.findInArray(requests);

            builder.produceMessage(found, start, startSort, 1);
        } else {
            start = System.currentTimeMillis();
            found = search.findInArray(requests);
            builder.produceMessage(found, start, startSort, 0);
        }
    }

    private static long performLinearSearch() {
        algorithm = new LinearSearch();
        algorithm.sortArray(names);
        System.out.println("Start searching...");
        long start = System.currentTimeMillis();
        int found = algorithm.findInArray(requests);
        long timePassed = System.currentTimeMillis() - start;

        builder.produceMessage(found, start);

        return timePassed;
    }

    private static void performBinarySearch() {
        System.out.println("Start searching (quick sort + binary search)...");
        algorithm = new BinarySearch();
        long startSort = System.currentTimeMillis();
        algorithm.sortArray(names);

        long start = System.currentTimeMillis();
        int found = algorithm.findInArray(requests);

        builder.produceMessage(found, start, startSort, 0);
    }

    private static void performHashSearch() {
        System.out.println("Start searching (hash table)...");
        HashSearch hashSearch = new HashSearch();
        long startSort = System.currentTimeMillis();
        hashSearch.setNumbers(book);
        hashSearch.sortArray(names);

        long start = System.currentTimeMillis();
        int found = hashSearch.findInArray(requests);

        builder.produceMessage(found, start, startSort, 2);
    }

    private static void init() {
        try {
            List<String> directory = Files.readAllLines(
                    Paths.get("C:\\Users\\steve\\OneDrive\\Documents\\directory.txt"));
            directory.forEach(line -> {
                book.add(line.substring(0, line.indexOf(" ")));
                names.add(line.substring(line.indexOf(" ") + 1));
            });
            requests = Files.readAllLines(
                    Paths.get("C:\\Users\\steve\\OneDrive\\Documents\\find.txt"));
            builder = new MessageBuilder(requests.size());
        } catch (IOException e) {
            System.out.println("File requests not found.");
            System.exit(1);
        }
    }
}
