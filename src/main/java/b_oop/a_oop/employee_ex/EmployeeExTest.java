package b_oop.a_oop.employee_ex;

public class EmployeeExTest {

    public static void main(String[] args) {

        // Superclass
        Employee emp1 = new Employee("Jim", 2000);
        Employee emp2 = new Employee("Kim");
        Employee emp3 = new Employee("Gary", 4000);

        Employee[] employees = new Employee[] {emp1, emp2, emp3};
        for (Employee e : employees) {
            System.out.println(e);
        }
        System.out.println(Employee.getClassId());

        // Subclass
        Manager manager = new Manager("Mackey", 5000, "CTO", 0.0);
        System.out.println(manager);
        manager.raiseSalary(10);
        System.out.println(manager);

        // Polymorphism:
        //  - Using superclass reference to store subclass object
//        Employee emp = new Employee("Joe", 1500);
//        Employee mgr = new Manager("Kim", 2000, "HR", 1000);
//        Employee dev = new Developer("Jim", 1700, "Tech", "Java");
//        System.out.println(emp.getSalary());    // calls getSalary of Employee class
//        System.out.println(mgr.getSalary());    // calls getSalary of Manager class


        // ArrayStoreException
//        Manager boss = new Manager("Kim", 2000, "HR", 1000);
//        Manager[] managers = new Manager[2];    // array element type Manager
//        managers[0] = boss;
//        Employee[] staffs = managers;           // staffs references array of Managers
//        staffs[1] = new Employee("Jim", 1500);  // element type Employee! throws ArrayStoreException

//        for (Employee e : staffs) {
//            System.out.println(e);
//        }

        // equals()
//        Employee emp1 = new Employee("Jim", 2000);
//        Manager m1 = new Manager("Jim", 2000, "CTO", 0.0);
//        System.out.println(m1.equals(emp1));

    }
}