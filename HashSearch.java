package phonebook;

import java.util.Hashtable;
import java.util.List;

public class HashSearch implements SearchAlgorithm {
    //puts numbers into hash table, the quickest search of them all
    private Hashtable<String, String> hashTable = new Hashtable<>();
    private List<String> book;

    void setNumbers(List<String> book) {
        this.book = book;
    }

    @Override
    public void sortArray(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            hashTable.put(list.get(i), book.get(i));
        }
    }

    @Override
    public int findInArray(List<String> requests) {
        int found = 0;
        for (String r : requests) {
            if (hashTable.get(r) != null) {
                found++;
            }
        }

        return found;
    }
}
