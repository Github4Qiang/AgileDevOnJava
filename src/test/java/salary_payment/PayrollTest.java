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