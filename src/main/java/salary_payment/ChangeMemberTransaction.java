package salary_payment;

/**
 * Created by Polylanger on 2017/4/10.
 */
public class ChangeMemberTransaction extends ChangeAffiliationTransaction {

    private int memberId;
    private double dues;

    public ChangeMemberTransaction(int empId, int memberId, double dues) {
        super(empId);
        this.memberId = memberId;
        this.dues = dues;
    }

    @Override
    public Affiliation getAffiliation() {
        return new UnionAffiliation(memberId, dues);
    }

    @Override
    public void recordMembership(Employee e) {
        PayrollDatabase.GPayroolDatabase.addUnionMember(memberId, getEmpId());
    }
}
