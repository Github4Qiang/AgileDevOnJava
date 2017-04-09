package salary_payment;

import junit.textui.TestRunner;


/**
 * Created by Polylanger on 2017/3/27.
 */
public class Runner {


    public static final String SALARY_PAYMENT_PAYROLL_TEST = "salary_payment.PayrollTest";

    public static void main(String[] args) {
        TestRunner.main(new String[]{
                SALARY_PAYMENT_PAYROLL_TEST,
        });
    }

}
