package EcommerceSearch;

public class LinearSearch {

    public static Product searchById(Product[] products, int targetId) {
        for (int i = 0; i < products.length; i++) {   // check every element
            if (products[i].getProductId() == targetId) {
                System.out.println("  Linear Search: found at index " + i
                        + " after " + (i + 1) + " comparison(s)");
                return products[i];
            }
        }
        return null;  // not found
    }
    public static Product searchByName(Product[] products, String name) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductName().equalsIgnoreCase(name)) {
                System.out.println("  Linear Search: found at index " + i
                        + " after " + (i + 1) + " comparison(s)");
                return products[i];
            }
        }
        return null;
    }
}