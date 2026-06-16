package EcommerceSearch;

public class BinarySearch {
    public static Product searchById(Product[] sortedProducts, int targetId) {
        int low  = 0;
        int high = sortedProducts.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;

            if (sortedProducts[mid].getProductId() == targetId) {
                System.out.println("  Binary Search: found at index " + mid
                        + " after " + comparisons + " comparison(s)");
                return sortedProducts[mid];

            } else if (sortedProducts[mid].getProductId() < targetId) {
                low = mid + 1;

            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}