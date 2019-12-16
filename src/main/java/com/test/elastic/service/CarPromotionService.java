package com.test.elastic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface CarPromotionService {

    List<String> PROMOTION_TYPES = new ArrayList<String>(Arrays.asList(new String[] 
			{"Bonus", "Discount"}));
    boolean isValidPromotionType(String promotionType);

}
