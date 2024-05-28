package b_oop.a_oop.employee_ex;

public class Developer extends Manager {

    private String department;
    private String technology;

    public Developer(String name, double salary, String position, double bonus) {
        super(name, salary, position, bonus);
    }

    public void testDev() {
        System.out.println("Testing...");
    }
}
