package salary_payment;

import junit.framework.TestCase;


/**
 * Created by Polylanger on 2017/4/9.
 */
public class PayrollTest extends TestCase {

    @Override
    public void setUp() throws Exception {
        PayrollDatabase.GPayroolDatabase.clear();
        super.setUp();
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