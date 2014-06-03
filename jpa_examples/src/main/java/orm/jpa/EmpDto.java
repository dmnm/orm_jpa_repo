package orm.jpa;


public class EmpDto {
    public Long id;
    public String name;
    public String prof;
    public String department;
    public String proj;

    public EmpDto(final Long id, final String firstName, final String secondName, final String department, final String proj, final String prof) {
        this.id = id;
        this.name = firstName + " " + secondName;
        this.prof = prof;
        this.department = department;
        this.proj = proj;
    }

    @Override
    public String toString() {
        return "EmpDto [id=" + id + ", name=" + name + ", prof=" + prof + ", department=" + department + ", proj=" + proj + "]";
    }
}
