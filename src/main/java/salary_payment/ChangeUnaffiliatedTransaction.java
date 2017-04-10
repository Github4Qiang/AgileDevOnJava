package salary_payment;

/**
 * Created by Polylanger on 2017/4/10.
 */
public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {

    public ChangeUnaffiliatedTransaction(int empId) {
        super(empId);
    }

    @Override
    public Affiliation getAffiliation() {
        return new NoAffiliation();
    }

    public void recordMembership(Employee e) {
        Affiliation af = e.getAffiliation();
        if (af instanceof UnionAffiliation) {
            int memberId = ((UnionAffiliation) af).getMemberId();
            PayrollDatabase.GPayroolDatabase.removeUnionMember(memberId);
        }
    }
}
