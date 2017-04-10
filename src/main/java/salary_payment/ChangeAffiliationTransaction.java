package salary_payment;

/**
 * Created by Polylanger on 2017/4/10.
 */
public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {

    public ChangeAffiliationTransaction(int empId) {
        super(empId);
    }

    @Override
    public void change(Employee e) {
        recordMembership(e);
        e.setAffiliation(getAffiliation());
    }

    public abstract Affiliation getAffiliation();

    public abstract void recordMembership(Employee e);
}
