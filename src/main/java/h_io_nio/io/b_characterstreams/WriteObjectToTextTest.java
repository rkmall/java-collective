package h_io_nio.io.b_characterstreams;

import h_io_nio.io.Company;
import h_io_nio.io.Guitar;

import java.io.*;
import java.util.Scanner;

public class WriteObjectToTextTest {

    public static void main(String[] args) {
        Guitar[] guitars = new Guitar[4];
        Guitar gtr1 = new Guitar(
                "Strat",
                "Fender",
                "Double cut",
                2000,
                new Company("Fender", "US"));

        Guitar gtr2 = new Guitar(
                "Les Paul",
                "Gibson",
                "Single cut",
                4000,
                new Company("Gibson", "US"));

        Guitar gtr3 = new Guitar(
                "Vader",
                "Kiesel",
                "Super strat",
                1400,
                new Company("Keisel", "US"));

        Guitar gtr4 = new Guitar(
                "Flying V",
                "ESP",
                "Xiphos",
                1700,
                new Company("ESP", "Japan"));

        guitars[0] = gtr1;
        guitars[1] = gtr2;
        guitars[2] = gtr3;
        guitars[3] = gtr4;

        String file = "guitars.dat";
        writeObject(guitars, file);
        readObject(file);
    }

    public static void writeObject(Guitar[] guitars, String path) {
        System.out.println("Writing to file");

        // FileOutputStream wrapped by PrintWriter.
        // PrintWriter writes the character, then FileOutputStream converts
        // the characters into bytes.
        try (PrintWriter out = new PrintWriter(new FileOutputStream(path))) {
            writeData(guitars, out);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readObject(String path) {
        System.out.println("Reading from file");

        // FileInputStream wrapped by Scanner
        // FileInputStream reads the bytes from the file
        // Scanner converts the bytes to their corresponding types.
        try (Scanner in = new Scanner(new FileInputStream(path))) {
            readData(in);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeData(Guitar[] guitars, PrintWriter out) {
        out.println(guitars.length);    // write number of elements
        for (Guitar gtr : guitars) {
            out.println(gtr.getName() + "|" +
                    gtr.getBrand() + "|" +
                    gtr.getStyle() + "|" +
                    gtr.getPrice() + "|" +
                    gtr.getCompany().getName() + "|" +
                    gtr.getCompany().getLocation()
            );
        }
    }

    public static void readData(Scanner in) {
         int n =  in.nextInt();     // get number of items
         in.nextLine();             // consume next line

         Guitar[] result = new Guitar[n];
         for (int i = 0; i < n; i++) {
             String line = in.nextLine();
             String[] tokens = line.split("\\|");
             String name = tokens[0];
             String brand = tokens[1];
             String style = tokens[2];
             double price = Double.parseDouble(tokens[3]);
             String companyName = tokens[4];
             String companyLocation = tokens[5];
             Company company = new Company(companyName, companyLocation);
             result[i] = new Guitar(name, brand, style, price, company);
         }

         for (Guitar gtr : result) {
             System.out.println(gtr);
         }
    }
}
