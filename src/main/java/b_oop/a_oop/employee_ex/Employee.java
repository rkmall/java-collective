package b_oop.a_oop.employee_ex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Employee  {
    // Static Fields
    public final static String CLASSNAME = "Employee";  // static constant
    private final static int CLASS_ID;                  // static constant
    private static int NEXT_ID = 1;                     // static field

    // Instance Fields
    private final int empId = assignId();           // explicit field initialization
    private final String name;                      // final fields must be initialized on object creation
    private final LocalDate startDate;
    private double salary = 0.0;                    // explicit field initialization
    private List<Task> taskList = new ArrayList<>();

    // Static block
    static
    {
        Random random = new Random();
        CLASS_ID = random.nextInt(100);
    }

    // Non-static block
    { startDate = LocalDate.now(); }

    // Constructor A
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Constructor B
    public Employee(String name) { this.name = name; }

    // Constructor C calls Constructor A
    public Employee() { this("n/a", 0.0); }

    // Methods
    public String getName() { return name; }

    public double getSalary() { return salary; }

    public LocalDate getStartDate() { return startDate; }

    public void raiseSalary(double percent) { salary += calculateRaise(percent); }

    protected int getEmpId() {
        return empId;
    }

    protected void testEmp() {
        System.out.println("testing");
    }

    // Private/Helper methods
    private double calculateRaise(double percent) { return salary * percent/100; }

    private static int assignId() {
        int currentId = NEXT_ID;
        NEXT_ID++;
        return currentId;
    }

    public static long getClassId() { return CLASS_ID; }

    public Task createTask(int taskId, String taskDesc) {
        Task task = new Task(taskId, taskDesc);
        taskList.add(task);
        return task;
    }

    // Overridden methods
    @Override
    public String toString() {
        return getClass().getName() + ":\n" +
                "Id: " + empId +
                ", Name: " + name +
                ", Salary: " + salary +
                ", Start Date: " + startDate + "\n";
    }

    @Override
    public boolean equals(Object o) {
        // Test if objects are identical
        if (this == o) return true;

        // Test if object is null or the class don't match
        if (o == null || getClass() != o.getClass()) return false;

        // Now we know that the object is non-null Employee
        Employee e = (Employee) o;

        // Test state; whether the fields have identical value
        if (Double.compare(e.salary, salary) != 0) return false;
        if (!Objects.equals(name, e.name)) return false;
        return Objects.equals(startDate, e.startDate);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;    // name hashcode
        temp = Double.doubleToLongBits(salary);         // salary to bits
        result = 31 * result + (int) (temp ^ (temp >>> 32)); // right shift bits temp
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);  // startDate hashcode
        return result;
    }

    /**
     * Compare employees by salary
     * @param o the Employee to be compared.
     * @return a negative value if this employee has a lower salary than
     * other Employee, 0 if the salaries are same, positive value otherwise.
     */
    public final int compareTo(Employee o) {
        return Double.compare(this.salary, o.salary);
    }
}
