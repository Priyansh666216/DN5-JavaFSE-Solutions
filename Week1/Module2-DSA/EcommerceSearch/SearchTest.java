package EcommerceSearch;

public class SearchTest {

    public static void main(String[] args) {

        Product[] products = {
                new Product(105, "Laptop",        "Electronics"),
                new Product(102, "Running Shoes", "Footwear"),
                new Product(108, "Novel Book",    "Books"),
                new Product(101, "Smartphone",    "Electronics"),
                new Product(107, "Coffee Maker",  "Appliances"),
                new Product(103, "T-Shirt",       "Clothing"),
                new Product(106, "Headphones",    "Electronics"),
                new Product(104, "Yoga Mat",      "Sports"),
        };

        Product[] sortedProducts = {
                new Product(101, "Smartphone",    "Electronics"),
                new Product(102, "Running Shoes", "Footwear"),
                new Product(103, "T-Shirt",       "Clothing"),
                new Product(104, "Yoga Mat",      "Sports"),
                new Product(105, "Laptop",        "Electronics"),
                new Product(106, "Headphones",    "Electronics"),
                new Product(107, "Coffee Maker",  "Appliances"),
                new Product(108, "Novel Book",    "Books"),
        };

        System.out.println("========================================");
        System.out.println("   E-COMMERCE SEARCH DEMO");
        System.out.println("========================================\n");
        int targetId = 106;
        System.out.println("Searching for Product ID: " + targetId);
        System.out.println("----------------------------------------");

        Product r1 = LinearSearch.searchById(products, targetId);
        System.out.println("  Result: " + (r1 != null ? r1 : "Not found") + "\n");

        Product r2 = BinarySearch.searchById(sortedProducts, targetId);
        System.out.println("  Result: " + (r2 != null ? r2 : "Not found") + "\n");

        System.out.println("Searching by Name: 'Yoga Mat'");
        System.out.println("----------------------------------------");
        Product r3 = LinearSearch.searchByName(products, "Yoga Mat");
        System.out.println("  Result: " + (r3 != null ? r3 : "Not found") + "\n");
        System.out.println("Searching for Product ID: 999 (not in list)");
        System.out.println("----------------------------------------");
        Product r4 = LinearSearch.searchById(products, 999);
        System.out.println("  Linear Result : " + (r4 != null ? r4 : "Not found"));
        Product r5 = BinarySearch.searchById(sortedProducts, 999);
        System.out.println("  Binary Result : " + (r5 != null ? r5 : "Not found") + "\n");
        System.out.println("========================================");
        System.out.println("   TIME COMPLEXITY ANALYSIS");
        System.out.println("========================================");
        System.out.println("                 Best     Average   Worst");
        System.out.println("Linear Search:   O(1)     O(n)      O(n)");
        System.out.println("Binary Search:   O(1)     O(log n)  O(log n)");
        System.out.println();
        System.out.println("For n=1000 products:");
        System.out.println("  Linear Search (worst): 1000 comparisons");
        System.out.printf ("  Binary Search (worst): %d comparisons%n",
                (int)(Math.log(1000) / Math.log(2)) + 1);
    }
}