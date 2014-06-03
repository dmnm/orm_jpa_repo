package orm.jpa;

public class EmployeeDto {

    public Long id;
    public String name;
    public String department;
    public String project;

    public EmployeeDto(final Long id, final String firstName, final String secondName, final String department,
            final String project) {
        this.id = id;
        this.name = firstName + " " + secondName;
        this.department = department;
        this.project = project;
    }

    @Override
    public String toString() {
        return "EmpDto [id=" + id + ", name=" + name + ", department=" + department + ", project="
                + project + "]";
    }
}
