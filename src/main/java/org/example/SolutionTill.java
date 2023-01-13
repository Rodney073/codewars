package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionTill {
    public static int solveSuperMarketQueue(int[] customers, int n) {

        int result = 0;

        List<Integer> customerList = new ArrayList<>(Arrays.stream(customers)
                .boxed().toList());

        while (customerList.stream().filter(i -> i == 0).count() != customers.length) {
            int numberOfCustomersAtTill = 0;
            for (int customerIndex = 0; customerIndex < customers.length; customerIndex++) {
                int customerValue = customerList.get(customerIndex);
                if (customerValue != 0) {
                    numberOfCustomersAtTill++;
                    customerValue--;
                    customerList.set(customerIndex, customerValue);
                }
                if (numberOfCustomersAtTill == n) {
                    break;
                }
            }
            result++;
        }
        return result;
    }
}
