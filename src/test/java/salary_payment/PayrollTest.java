package salary_payment;

import junit.framework.TestCase;

import java.util.Date;


/**
 * Created by Polylanger on 2017/4/9.
 */
public class PayrollTest extends TestCase {

    @Override
    public void setUp() throws Exception {
        PayrollDatabase.GPayroolDatabase.clear();
        super.setUp();
    }

    public void testPaySingleHourlyEmployeeTwoTimeCards() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double hourlyRate = 25.0;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, name, home, hourlyRate);
        t.execute();

        Date payDate1 = DateUtils.CreateDate(2017, 4, 7);
        double hours1 = 9.0;
        TimeCardTransaction tct1 = new TimeCardTransaction(payDate1, hours1, empId);
        tct1.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate1);
        pt.execute();

        ValidatePaycheck(pt, empId, payDate1, (hours1 - 8) * 1.5 * hourlyRate + 8 * hourlyRate);
    }

    public void testPaySingleHourlyEmployeeOneTimeCard() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double hourlyRate = 25.0;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, name, home, hourlyRate);
        t.execute();

        Date payDate = DateUtils.CreateDate(2017, 4, 7);
        double hours = 2.0;
        TimeCardTransaction tct = new TimeCardTransaction(payDate, hours, empId);
        tct.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        ValidatePaycheck(pt, empId, payDate, hours * hourlyRate);
    }

    public void testPaySingleHourlyEmployeeNoTimeCards() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double hourlyRate = 25.0;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, name, home, hourlyRate);
        t.execute();

        Date payDate = DateUtils.CreateDate(2017, 4, 7);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        ValidatePaycheck(pt, empId, payDate, 0.0);
    }

    private void ValidatePaycheck(PaydayTransaction pt, int empId, Date payDate, double pay) {
        Paycheck pc = pt.getPaycheck(empId);
        assertTrue(pc != null);
        assertEquals(payDate.getTime(), pc.getPayPeriodEndDate().getTime());
        assertEquals(pay, pc.getGrossPay());
        assertEquals(0.0, pc.getDeductions());
        assertEquals(pay, pc.getNetPay());
    }

    public void testPaySingleSalariedEmployee() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double salary = 1000.0;
        AddEmployeeTransaction t = new AddSalariedEmployee(empId, name, home, salary);
        t.execute();

        Date payDate = DateUtils.CreateDate(2017, 3, 31);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        Paycheck pc = pt.getPaycheck(empId);
        assertEquals(payDate.getTime(), pc.getPayDate().getTime());
        assertEquals(salary, pc.getGrossPay());
        assertEquals(0.0, pc.getDeductions());
        assertEquals(salary, pc.getNetPay());
        assertEquals("Hold", pc.getField("Disposition"));
    }

    public void testPaySingleSalariedEmployeeOnWrongDate() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double salary = 1000.0;
        AddEmployeeTransaction t = new AddSalariedEmployee(empId, name, home, salary);
        t.execute();

        Date payDate = new Date(2017, 2, 30);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        Paycheck pc = pt.getPaycheck(empId);
        assertEquals(null, pc);
    }

    public void testChangeMemberTransaction() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double salary = 1000.0;
        AddEmployeeTransaction t = new AddSalariedEmployee(empId, name, home, salary);
        t.execute();

        int memberId = 3232;
        double dues = 77.32;
        ChangeAffiliationTransaction cmt = new ChangeMemberTransaction(empId, memberId, dues);
        cmt.execute();
        Employee e1 = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        Affiliation af = e1.getAffiliation();
        assertTrue(af instanceof UnionAffiliation);

        Employee e2 = PayrollDatabase.GPayroolDatabase.getUnionMember(memberId);
        assertEquals(e1, e2);
    }

    public void testChangeUnffiliatedTransaction() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double salary = 1000.0;
        AddEmployeeTransaction t = new AddSalariedEmployee(empId, name, home, salary);
        t.execute();

        int memberId = 3232;
        double dues = 77.32;
        ChangeAffiliationTransaction cat1 = new ChangeMemberTransaction(empId, memberId, dues);
        cat1.execute();
        Employee e1 = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        Affiliation af = e1.getAffiliation();

        ChangeAffiliationTransaction cat2 = new ChangeUnaffiliatedTransaction(empId);
        cat2.execute();
        Employee e2 = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        Affiliation af2 = e2.getAffiliation();
        assertTrue(af2 instanceof NoAffiliation);

        Employee e3 = PayrollDatabase.GPayroolDatabase.getUnionMember(memberId);
        assertEquals(null, e3);
    }

    public void testChangeHoldMethodTransaction() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double salary = 1000.0;

        AddEmployeeTransaction t = new AddSalariedEmployee(empId, name, home, salary);
        t.execute();

        String address = "Shi Hezi";
        ChangeMethodTransaction cmt1 = new ChangeMailMethodTransaction(empId, address);
        cmt1.execute();

        ChangeMethodTransaction cmt2 = new ChangeHoldMethodTransaction(empId);
        cmt2.execute();
        Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        assertTrue(e.getMethod() instanceof HoldMethod);
    }

    public void testChangeDirectMethodTransaction() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double salary = 1000.0;

        AddEmployeeTransaction t = new AddSalariedEmployee(empId, name, home, salary);
        t.execute();

        String bank = "CCB";
        int account = 12345;
        ChangeMethodTransaction cmt = new ChangeDirectMethodTransaction(empId, bank, account);
        cmt.execute();

        Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        assertTrue(e.getMethod() instanceof DirectMethod);
    }

    public void testChangeMailMethodTransaction() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double salary = 1000.0;

        AddEmployeeTransaction t = new AddSalariedEmployee(empId, name, home, salary);
        t.execute();

        String bank = "CCB";
        int account = 12345;
        ChangeMethodTransaction cmt1 = new ChangeDirectMethodTransaction(empId, bank, account);
        cmt1.execute();

        String address = "Shi Hezi";
        ChangeMethodTransaction cmt2 = new ChangeMailMethodTransaction(empId, address);
        cmt2.execute();
        Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        assertTrue(e.getMethod() instanceof MailMethod);
    }

    public void testChangeHourlyTransaction() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double salary = 1000.0;

        AddEmployeeTransaction t = new AddSalariedEmployee(empId, name, home, salary);
        t.execute();

        double hourlyRate = 25.0;
        ChangeClassificationTransaction cct = new ChangeHourlyTransaction(empId, hourlyRate);
        cct.execute();
        {
            Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
            assertEquals(name, e.getName());

            PaymentClassification pc = e.getClassification();
            assertTrue(pc instanceof HourlyClassification);

            PaymentSchedule ps = e.getSchedule();
            assertTrue(ps instanceof WeeklySchedule);
        }
    }

    public void testChangeSalariedTransaction() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double salary = 1000.0;
        double commissionRate = 0.1;

        AddEmployeeTransaction t = new AddCommissionEmployee(empId, name, home, salary, commissionRate);
        t.execute();

        ChangeClassificationTransaction cct = new ChangeSalariedTransaction(empId, salary);
        cct.execute();
        {
            Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
            assertEquals(name, e.getName());

            PaymentClassification pc = e.getClassification();
            assertTrue(pc instanceof SalariedClassification);

            PaymentSchedule ps = e.getSchedule();
            assertTrue(ps instanceof MonthlySchedule);
        }
    }

    public void testChangeCommissionTransaction() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double hourlyRate = 25.0;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, name, home, hourlyRate);
        t.execute();

        double salary = 1000.0;
        double commissionRate = 0.1;
        ChangeClassificationTransaction cct = new ChangeCommissionedTransaction(empId, salary, commissionRate);
        cct.execute();
        {
            Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
            assertEquals(name, e.getName());

            PaymentClassification pc = e.getClassification();
            assertTrue(pc instanceof CommissionedClassification);

            PaymentSchedule ps = e.getSchedule();
            assertTrue(ps instanceof BiweeklySchedule);
        }
    }

    public void testChangeAttributeTransaction() throws Exception {
        int empId = 1;
        String name = "Bob";
        String address = "Home";
        double salary = 1000.00;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, name, address, salary);
        t.execute();

        String name1 = "Bill";
        ChangeNameTransaction cnt = new ChangeNameTransaction(empId, name1);
        cnt.execute();
        String address1 = "Cheng du";
        ChangeAddressTransaction cat = new ChangeAddressTransaction(empId, address1);
        cat.execute();

        Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        assertEquals(name1, e.getName());
        assertEquals(address1, e.getAddress());
    }

    public void testAddServiceCharge() throws Exception {
        int empId = 1;
        String name = "Bob";
        String home = "Home";
        double salary = 1000.00;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, name, home, salary);
        t.execute();
        Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        assertEquals(name, e.getName());

        int memberId = 46;
        double dues = 12.3;
        UnionAffiliation af = new UnionAffiliation(memberId, dues);
        e.setAffiliation(af);
        PayrollDatabase.GPayroolDatabase.addUnionMember(memberId, empId);

        Date date = new Date(2017, 4, 9);
        double serviceCharge = 12.84;
        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, date, serviceCharge);
        sct.execute();
        ServiceCharge sc = af.getServiceCharge(date);
        assertEquals(serviceCharge, sc.getAmount());
    }

    public void testSalesReceiptTransaction() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double salary = 1000.00;
        double commissionRate = 0.1;

        AddEmployeeTransaction t = new AddCommissionEmployee(empId, name, home, salary, commissionRate);
        t.execute();

        Date date = new Date(2017, 4, 9);
        double amount = 1000;
        SalesReceiptTransaction srt = new SalesReceiptTransaction(date, amount, empId);
        srt.execute();

        Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        assertEquals(name, e.getName());

        PaymentClassification pc = e.getClassification();
        assertTrue(pc instanceof CommissionedClassification);
        SalesReceipt sr = ((CommissionedClassification) pc).getSalesReceipt(date);
        assertEquals(amount, sr.getAmount());
    }

    public void testTimeCardTransaction() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double hourlyRate = 25.0;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, name, home, hourlyRate);
        t.execute();

        Date date = new Date(2017, 4, 9);
        double hours = 8.0;
        TimeCardTransaction tct = new TimeCardTransaction(date, hours, empId);
        tct.execute();

        Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        assertEquals(name, e.getName());
        PaymentClassification pc = e.getClassification();
        assertTrue(pc instanceof HourlyClassification);

        TimeCard tc = ((HourlyClassification) pc).getTimeCard(date);
        assertEquals(hours, tc.getHours());
    }

    public void testDeleteEmployee() throws Exception {
        int empId = 1;
        String name = "Bob";
        String home = "Home";
        double salary = 1000.00;
        double commissionRate = 0.1;

        AddEmployeeTransaction at = new AddCommissionEmployee(empId, name, home, salary, commissionRate);
        at.execute();
        {
            Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
            assertEquals(name, e.getName());
        }

        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
        dt.execute();
        {
            Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
            assertEquals(null, e);
        }
    }

    public void testAddSalariedEmployee() throws Exception {
        int empId = 1;
        String name = "Bob";
        String home = "Home";
        double salary = 1000.00;

        AddSalariedEmployee t = new AddSalariedEmployee(empId, name, home, salary);
        t.execute();

        Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        assertEquals(name, e.getName());

        PaymentClassification pc = e.getClassification();
        assertTrue(pc instanceof SalariedClassification);

        PaymentSchedule ps = e.getSchedule();
        assertTrue(ps instanceof MonthlySchedule);

        PaymentMethod pm = e.getMethod();
        assertTrue(pm instanceof HoldMethod);
    }

    public void testAddHourlyEmployee() throws Exception {
        int empId = 2;
        String name = "Bob";
        String home = "Home";
        double hourlyRate = 25.0;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, name, home, hourlyRate);
        t.execute();

        Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        assertEquals(name, e.getName());

        PaymentClassification pc = e.getClassification();
        assertTrue(pc instanceof HourlyClassification);

        PaymentSchedule ps = e.getSchedule();
        assertTrue(ps instanceof WeeklySchedule);

        PaymentMethod pm = e.getMethod();
        assertTrue(pm instanceof HoldMethod);
    }

    public void testAddCommissionEmployee() throws Exception {
        int empId = 3;
        String name = "Bob";
        String home = "Home";
        double salary = 1000.0;
        double commissionRate = 0.1;

        AddCommissionEmployee t = new AddCommissionEmployee(empId, name, home, salary, commissionRate);
        t.execute();

        Employee e = PayrollDatabase.GPayroolDatabase.getEmployee(empId);
        assertEquals(name, e.getName());

        PaymentClassification pc = e.getClassification();
        assertTrue(pc instanceof CommissionedClassification);

        PaymentSchedule ps = e.getSchedule();
        assertTrue(ps instanceof BiweeklySchedule);

        PaymentMethod pm = e.getMethod();
        assertTrue(pm instanceof HoldMethod);
    }
}