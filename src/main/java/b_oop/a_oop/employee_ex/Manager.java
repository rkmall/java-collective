package b_oop.a_oop.employee_ex;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Manager extends Employee {
    private String position;                            // additional field
    private double bonus;                               // additional field
    private List<Task> delegations = new ArrayList<>(); // additional field

    public Manager(String name, double salary, String position, double bonus) {
        super(name, salary);        // super calls superclass constructor
        this.position = position;   // additional fields are initialized in subclass
        this.bonus = bonus;         // additional fields are initialized in subclass
    }

    // Method specific to Manager
    public void addDelegation(Task task, Employee emp) {
        task.addContributor(emp);
        delegations.add(task);
    }

    @Override
    public double getSalary() {
        return super.getSalary() + bonus;   // super calls superclass method
    }

    @Override
    public void raiseSalary(double percent) {
        super.raiseSalary(percent + bonus);  // super calls superclass method
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getPosition() {
        return position;
    }

    public double getBonus() {
        return bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;

        // super.equals checked that this and o belong to the same class
        Manager manager = (Manager) o;
        if (!Objects.equals(position, manager.position)) return false;
        if (Double.compare(bonus, manager.bonus) != 0) return false;
        return Objects.equals(delegations, ((Manager) o).delegations);
    }

    @Override
    public String toString() {
        return super.toString() +
                "Position: " + position +
                ", Bonus: " + bonus + "\n";
    }
}
