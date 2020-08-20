package phonebook;

import java.util.List;

public class JumpSearch implements SearchAlgorithm {
    //jump search with bubble sort, takes a while
    private List<String> list;
    private long limit;
    private boolean isFinished = true;

    private int jumpSearch(String[] array, String target) {
        int currentRight = 0;
        int prevRight = 0;

        if (array.length == 0) return -1;

        int block = (int) Math.sqrt(array.length);
        while (currentRight < array.length - 1) {
            currentRight = Math.min(array.length - 1, currentRight + block);

            if (array[currentRight].compareTo(target) >= 0) break;

            prevRight = currentRight;
        }

        if (currentRight == array.length - 1 && target.compareTo(array[currentRight]) > 0)
            return -1;

        return blockLinearSearch(array, target, prevRight, currentRight);
    }

    private int blockLinearSearch(String[] array, String target, int left, int right) {
        for (int i = right; i > left; i--) {
            if (array[i].equals(target)) return i;
        }

        return -1;
    }

    void setLimit(long limit) {
        this.limit = limit;
    }

    boolean isFinished() {
        return isFinished;
    }

    @Override
    public void sortArray(List<String> list) {
        this.list = list;

        String temp;
        long start = System.currentTimeMillis();

        bubbleSort:
        for (int j = 0; j < list.size(); j++) {
            for (int i = j + 1; i < list.size(); i++) {
                if (list.get(i).compareTo(list.get(j)) < 0) {
                    if ((System.currentTimeMillis() - start) > limit) {
                        isFinished = false;
                        break bubbleSort;
                    }

                    temp = list.get(j);
                    list.set(j, list.get(i));
                    list.set(i, temp);
                }
            }
        }

        if (isFinished) {
            this.list = list;
        }
    }

    @Override
    public int findInArray(List<String> requests) {
        String[] arr = list.toArray(new String[0]);
        int found = 0;

        for (String req : requests) {
            if (jumpSearch(arr, req) > -1) found++;
        }

        return found;
    }
}
