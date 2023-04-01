import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import store.AppConfig;
import store.Customer;
import store.cart.Cart;
import store.entity.Product;
import store.repository.ProductRepoControl;
import store.repository.ProductRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class MyApp {
    private static ProductRepoControl productRepo;
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        productRepo = context.getBean("productRepository", ProductRepository.class);

//
        showAllProductsStore();
        try (Reader reader = new InputStreamReader(System.in);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String command;
            System.out.print("Want to start shopping select Yes to exit the store No: ");
            command = bufferedReader.readLine().trim();

            if (command.equalsIgnoreCase("yes")) {
                Customer customer = createCustomerAndCart(bufferedReader);
                info();
                while (true) {
                    command = bufferedReader.readLine().trim();
                    if (command.equalsIgnoreCase("-exit")) {
                        printTotalPrice(customer);
                        break;
                    } else {
                        commandProcessing(command, customer);
                    }
                }
            } else {
                System.out.println("На все добре!");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void commandProcessing(String command, Customer customer) {
        String[] com = command.split(" ");
        int cartNumber = Integer.parseInt(com[1].replace(":", ""));
        if (cartNumber > customer.getCarts().size()) {
            System.err.printf("You have only %s baskets", customer.getCarts().size());
            return;
        }
        String[] items;
        switch (com[0].trim()) {
            case "-add":
                items = com[2].split(",");
                for (int i = 0; i < items.length; i++) {
                    int idProduct = Integer.parseInt(items[i]);
                    customer.getCarts().get(cartNumber - 1).addProduct(idProduct);
                }
                System.out.println("Products have been added to the cart");
                break;

            case "-del":
                items = com[2].split(",");
                for (int i = 0; i < items.length; i++) {
                    int idProduct = Integer.parseInt(items[i]);
                    customer.getCarts().get(cartNumber - 1).removeProduct(idProduct);
                }
                System.out.println("Products have been removed from the shopping cart");
                break;

            case "-show":
                Cart cart = customer.getCarts().get(cartNumber - 1);
                List<Product> products = cart.getAllProducts();
                System.out.println("All products in your shopping cart: ");

                products.forEach(System.out::println);
                break;
            default:
                System.err.println("Check that the command is entered correctly");
        }
    }

    private static void printTotalPrice(Customer customer) {
        List<Cart> carts = customer.getCarts();
        double totalPrice;
        System.out.printf("\n%s your purchases: \n", customer.getName());
        for (int i = 0; i < carts.size(); i++) {
            System.out.printf(" - cart # %s\n", carts.get(i).getPersonCart());
            System.out.println("-----------------------------------------");
            List<Product> products = carts.get(i).getAllProducts();
            totalPrice = 0;
            for (int j = 0; j < products.size(); j++) {
                System.out.println(products.get(j));
                totalPrice = totalPrice + products.get(i).getPrice();
            }
            System.out.println("-----------------------------------------");
            System.out.println("                    Total price: " + totalPrice);
        }
        System.err.println("GOOD DAY");
    }


    private static Customer createCustomerAndCart(BufferedReader bufferedReader) throws IOException {
        Customer customer;
        String command;
        while (true) {
            System.out.print("Say your name: ");
            command = bufferedReader.readLine().trim();
            if (command.equals("")) {
                System.err.println("The field cannot be empty");
            } else {
                customer = new Customer(command);
                Cart cart = context.getBean("cart", Cart.class);
                cart.setPersonCart(command);
                customer.getCarts().add(cart);
                System.out.printf("%s your cart: %s\n", command, cart.getPersonCart());
                break;
            }
        }
        while (true) {
            System.out.print("Want to create additional carts? Yes, No: ");
            command = bufferedReader.readLine().trim();
            if (command.equalsIgnoreCase("yes")) {
                Cart cart = context.getBean("cart", Cart.class);
                cart.setPersonCart(customer.getName());
                System.out.printf("An additional carts %s has been created for you.\n", cart.getPersonCart());
                customer.getCarts().add(cart);
            } else break;
        }
        System.out.printf("%s the following carts are available for shopping: \n", customer.getName());
        System.out.println("-------------------------");
        int num = 1;
        for (int i = 0; i < customer.getCarts().size(); i++) {
            System.out.println("# " + num + " - " + customer.getCarts().get(i).getPersonCart());
            num++;
        }
        System.out.println("-------------------------");
        return customer;
    }

    private static void info() {
        System.out.println("Use commands to manage purchases: ");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("commands example notation ");
        System.out.println("-add   | -add 1: 1,4,6  | - adding products 1,4,6 to the cart 1");
        System.out.println("-del   | -del 2: 3,2    | - removing products 3,2 from the cart 2");
        System.out.println("-show  | -show 2        | - show the contents of the cart 2");
        System.out.println("-exit  |                | - completing purchases");
        System.out.println("-------------------------------------------------------------------");
    }

    private static void showAllProductsStore() {
        System.out.println("Products are available for purchase :");
        System.out.println("------------------------------------");
        List<Product> products = productRepo.getAll();
        products.forEach(System.out::println);
        System.out.println("------------------------------------");

    }


}
