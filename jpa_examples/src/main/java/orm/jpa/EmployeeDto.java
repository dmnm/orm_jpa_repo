package orm.jpa;

public class EmployeeDto {

    private Long id;
    private String name;
    private String department;
    private String project;

    public EmployeeDto(final Long id, final String firstName, final String secondName, final String department, final String project) {
        this.id = id;
        this.name = firstName + " " + secondName;
        this.department = department;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(final String department) {
        this.department = department;
    }

    public String getProject() {
        return project;
    }

    public void setProject(final String project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "EmployeeDto [id=" + id + ", name=" + name + ", department=" + department + ", project="
                + project + "]";
    }
}
