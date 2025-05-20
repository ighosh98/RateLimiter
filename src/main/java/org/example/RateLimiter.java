package org.example;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {
    private Map<Integer,RequestData> customerMap; // for a given cid number of requests left, time window endpoint
    static private final int numberOfRequests = 2;
    static private final long timeWindow = 100;
    static private final int creditLimit = 3;
    private Map<Integer, Integer> customerCredits;
  //  private RateLimiter instance = null;
    public  RateLimiter(){
        customerMap = new HashMap<>();
        customerCredits = new HashMap<>();
    }
    //  space complexity: O(n)
    // time complexity: average case: O(1) worst case: O(n)
    // if we use a treemap -> O(logn)

     /**
     * x Requests per y seconds
     * @param customerId
     * @return true if request is allowed else return false
     */
    boolean isAllowed(int customerId) {
        if(!customerMap.containsKey(customerId)) {
            customerMap.put(customerId, new RequestData(numberOfRequests-1,
                    System.currentTimeMillis()+timeWindow));
            return true;
        } else {
            RequestData requestData = customerMap.get(customerId);
            if(System.currentTimeMillis() <= requestData.timeWindow) {
                if(requestData.numberOfRequests>0) {
                    requestData.numberOfRequests -=1;
                    customerMap.put(customerId, requestData);
                    return true;
                } else {
                    if(customerCredits.containsKey(customerId)) {
                        int remainingCredits = customerCredits.get(customerId);
                        if(remainingCredits>0) {
                            customerCredits.put(customerId,remainingCredits-1);
                            return true;
                        } else {
                            return false;
                        }
                    }
                    return false;
                }
            } else {
                int numberOfTimeWindows = (int) ((System.currentTimeMillis()-requestData.timeWindow)/timeWindow);
                if(customerCredits.containsKey(customerId)) {
                  int extraCredits=  customerCredits.get(customerId);
                  customerCredits.put(customerId,
                          Math.min(extraCredits+requestData.numberOfRequests+numberOfRequests*numberOfTimeWindows,creditLimit));
                } else {
                    customerCredits.put(customerId,Math.min(requestData.numberOfRequests+numberOfRequests*numberOfTimeWindows,creditLimit));
                }
                // reset the customer map
                customerMap.put(customerId, new RequestData(numberOfRequests-1,
                        System.currentTimeMillis()+timeWindow));
                return true;
            }
        }
    }

}
