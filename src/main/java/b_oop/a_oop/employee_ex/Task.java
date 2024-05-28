package b_oop.a_oop.employee_ex;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private int taskId;
    private String taskDesc;
    private List<Employee> contributors = new ArrayList<>();

    public Task(int taskId, String taskDesc) {
        this.taskId = taskId;
        this.taskDesc = taskDesc;
    }

    public void addContributor(Employee employee) {
        if (employee != null) {
            contributors.add(employee);
            System.out.println("Task: added contributor: " + employee.getName());
        } else {
            System.out.println("Task: problem adding contributor, null employee");
        }
    }

    public List<Employee> getContributors() {
        return contributors;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskDesc() {
        return taskDesc;
    }


}
