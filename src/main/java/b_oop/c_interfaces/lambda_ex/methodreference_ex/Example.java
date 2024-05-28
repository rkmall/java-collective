package b_oop.c_interfaces.lambda_ex.methodreference_ex;


public class Example {
    private  String name;

    public Example(String name) { this.name = name; }

    public static void displayContent(String s) {
        System.out.println("Length: " + s.length() + ", Content: " + s);
    }

    public int listContent(String[] content) {
        for (String s : content) {
            System.out.println(s);
        }
        return content.length;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }

}
