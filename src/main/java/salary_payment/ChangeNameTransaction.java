package salary_payment;

/**
 * Created by Polylanger on 2017/4/9.
 */
public class ChangeNameTransaction extends ChangeEmployeeTransaction {
    private String name;

    public ChangeNameTransaction(int empId, String name) {
        super(empId);
        this.name = name;
    }

    public void change(Employee e) {
        e.setName(name);
    }
}
