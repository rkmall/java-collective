package b_oop.c_interfaces.lambda_ex.intro;

import b_oop.a_oop.employee_ex.Employee;

public class LambdaExDriver {

    public static void main(String[] args) {
        //lambdaSyntax();
    }

    public static void lambdaSyntax() {
        // Anonymous function
        EmployeeFilter anonFun = new EmployeeFilter() {
            @Override
            public boolean filter(Employee e) {
                return e.getSalary() > 2000;
            }
        };

        // When the code block doesn't fit in a single expression:
        //  - use braces "{}" to enclose the code block.
        //  - use explicit return statement if required (inferred form the context).
        EmployeeFilter shortLambda = (Employee e) -> {
            double salary = e.getSalary();
            System.out.println("Salary: " + salary);
            return  salary > 2000;
        };

        // When the code block is a single expression:
        //  - omit braces "{}".
        //  - omit explicit return statement.
        // The parameter types are inferred from the interface variable type.
        //  - If it is a single parameter and is inferred type, omit the parenthesis "()"
        EmployeeFilter shortestLambda = e -> e.getSalary() > 2000;

        // For lambda expr with zero parameter, must supply empty parenthesis "()"
        Executor executor = () -> System.out.println("Executing");
    }
}




















