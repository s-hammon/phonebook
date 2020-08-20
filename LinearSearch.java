package phonebook;

import java.util.List;

public class LinearSearch implements SearchAlgorithm {
    //simple linear search, takes about 4 seconds
    private List<String> list;

    @Override
    public void sortArray(List<String> list) {
        this.list = list;
    }

    @Override
    public int findInArray(List<String> requests) {
        int found = 0;
        for (String r : requests) {
            for (String name : list) {
                if (r.equals(name)) {
                    found++;
                    break;
                }
            }
        }

        return found;
    }
}
