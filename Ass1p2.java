//By RuM
//Telegram: @RU44_Y
//Twitter:  @RU44_Y

import java.io.*;
import java.util.*;

public class Ass1p2 {

    // WARNING DO NOT COPY AND PASTE THE CODE IF YOU DO THAT YOU WILL GET -4 !!
    public static void main(String[] args) throws Exception {
        File input_File = new File("input.txt");
        File Print_File = new File("Print.txt");
        PrintWriter out = new PrintWriter(Print_File);
        //to check if the file is Exists 
        if (!(input_File.exists())) {
            out.println("File does not exist!");
            System.exit(0);
        }
        //---R---u---M------
        Scanner in = new Scanner(input_File);
        Date date = new Date();
        //---------------
        //      Customers Array
        String[] customers = new String[in.nextInt()];
        //      Items Array
        String[] items = new String[in.nextInt()];
        double[] itemsprice = new double[items.length];
        //        Items invoice
        int[][][] invoice = new int[customers.length][][];
        //---------------
        out.println("\n***** Welcome to EasyBilling Invoicing System *****");
        out.println("    Today's Date:" + date);
        //---------------
        String command = in.next();
        invoking_methods(in, out, command, customers, items, itemsprice, invoice, date);
    }

    static void invoking_methods(Scanner in, PrintWriter out, String command, String[] customers,
            String[] items, double[] itemsprice, int[][][] invoice, Date date) throws Exception {
        do {
            if (command.equalsIgnoreCase("add_customer")) {
                add_cutomer(in, out, customers);
            } else if (command.equalsIgnoreCase("add_ItemName")) {
                add_ItemName(in, out, items);
            } else if (command.equalsIgnoreCase("add_ItemPrice")) {
                add_ItemPrice(in, out, itemsprice);
            } else if (command.equalsIgnoreCase("add_Invoice")) {
                add_Invoice(in, out, customers, invoice);
            } else if (command.equalsIgnoreCase("print_Item_details")) {
                print_Item_details(out, items, itemsprice);
            } else if (command.equalsIgnoreCase("search_Item")) {
                search_Item(in, out, items, itemsprice);
            } else if (command.equalsIgnoreCase("print_All_Invoices")) {
                print_All_Invoices(out, customers, items, itemsprice, invoice, date);
            } else if (command.equalsIgnoreCase("about_App")) {
                about_App(out);
            }
            //Read command from the file 
            command = in.next();
        } while (!(command.equalsIgnoreCase("Quit")));
        Quit(in, out);
    }

    static void add_cutomer(Scanner in, PrintWriter out, String[] customers) {
        out.println("--------Executing add_customer Command------------");
        for (int i = 0; i < customers.length; i++) {
            customers[i] = textSplit(in.next());
            out.print(" " + customers[i]);
        }
        out.println();
    }

    static void add_ItemName(Scanner in, PrintWriter out, String[] items) {
        out.println("--------Executing add_ItemName Command------------");
        for (int i = 0; i < items.length; i++) {
            items[i] = in.next();
            out.print(items[i] + " ");
        }
        out.println();
    }

    static void add_ItemPrice(Scanner in, PrintWriter out, double[] itemsprice) {
        out.println("--------Executing add_ItemPrice Command------------");
        for (int i = 0; i < itemsprice.length; i++) {
            itemsprice[i] = in.nextDouble();
            out.print(itemsprice[i] + " ");
        }
        out.println("\n");
    }

    //if you didn't understand it feel free to contact me on Telegram @RU44_Y and I will explain it :)  
    /*
    from input file you will find command add_Invoice look like this:
    add_Invoice 0   2   2    8     4    5
                ^                       
    So First number for customerIndex in CustomerArray  
    add_Invoice 0   2   2    8     4    5
                    ^
    Second number for number of items
    add_Invoice 0   2   2    8     4    5
                        ^
    third number for ItemsIndex in ItemsArray
    add_Invoice 0   2   2    8     4    5
                             ^
    fourth number for Quantity and 4 for ItemsIndex and 5 for Quantity so on repeated
     */
    static void add_Invoice(Scanner in, PrintWriter out, String[] customers, int[][][] invoice) {
        out.println("\n------Executing add_Invoice Command-----");
        // First Number
        int customer = in.nextInt();
        // Second Number number of itmes
        int nofitems = in.nextInt();

        // why its 2                         here
        //cuz its fixed for third and fourth number     
        invoice[customer] = new int[nofitems][2];
        for (int j = 0; j < nofitems; j++) {
            for (int k = 0; k < 2; k++) {
                invoice[customer][j][k] = in.nextInt();
                out.print(invoice[customer][j][k] + " ");
            }
            out.println();
        }

    }

    static void print_Item_details(PrintWriter out, String[] items, double[] itemsprice) {
        out.println("--------Executing print_Item_details Command------------");
        out.println("\tItem Name\t\t\tPrice");
        out.println("\t---------------------------------------------------------------\n");
        for (int i = 0; i < items.length; i++) {
            out.printf("   %-18s%10s0%n", items[i], itemsprice[i]);
        }
        out.println();
    }

    static void search_Item(Scanner in, PrintWriter out, String[] items, double[] itemsprice) {
        out.println("--------Executing search_Item Command------------");
        String key = in.next().trim();
        int result = Search(items, key);
        if (result == -1) {
            out.print("\t\t\tSorry! " + key + ", Not Found!!!! ");
        } else {
            out.println("\t\t\tGreat!" + items[result] + ", Found!!!! ");
            out.println("\t\tItem Name\t\t\tPrice ");
            // please use printf Dont COPY AND PASTE !!
            out.println("\t\t" + items[result] + "\t\t\t\t  " + itemsprice[result] + "0");

        }
        out.println();
    }

    static void print_All_Invoices(PrintWriter out, String[] customers, String[] items, double[] itemsprice, int[][][] invoice, Date date) throws Exception {

        out.println("--------Executing print_All_Invoices Command------------");
        for (int i = 0; i < customers.length; i++) {
            double bill = 0.0;
            out.println("    ------------------------ Invoice Details --------------------------");
            out.println("	Invoice Number :Jed_" + generateRandomNumber() + "\t\t Date :" + date);
            out.println("	Customer Name : " + customers[i].toUpperCase());
            out.println("	Item Name           	Price     	Quantity  	Total Amount");
            out.println("	---------------------------------------------------------------\n");
            for (int j = 0; j < invoice[i].length; j++) {
                double total_amount = itemsprice[invoice[i][j][0]] * invoice[i][j][1];
                bill += total_amount;
                out.printf("	%-20s%10s0%13d%13.2f%n", items[invoice[i][j][0]], itemsprice[invoice[i][j][0]], invoice[i][j][1], total_amount);
            }
            out.printf("%n\t\t\t\t\tTotal Bill:\t\t\t\t%.2f%s%n%n%n", bill, " SR");
        }
        out.println();
    }

    static void about_App(PrintWriter out) {
        out.println("- Command: about_app");
        out.println("\t-> Developer Name: RuM");
        out.println("\t-> Developer UID: 1800008");
        out.println("\t-> Developer Section: @RU44_Y\n");
    }

    static void Quit(Scanner in, PrintWriter out) {
        out.println(" Thank you for using  EasyBilling Invoicing System!");
        in.close();
        out.flush();
        out.close();
        System.exit(0);
    }

    static int Search(String[] items, String key) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equalsIgnoreCase(key)) {
                return i;
            }
        }
        return -1;
    }

    static int generateRandomNumber() {
        return ((int) (Math.random() * 10000));
    }

    static String textSplit(String str) {
        return str.replace("_", "");
    }

    //By RuM
    //Telegram: @RU44_Y
    //Twitter: @RU44_Y
}
