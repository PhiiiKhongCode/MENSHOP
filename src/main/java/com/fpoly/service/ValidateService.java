package com.fpoly.service;

import java.util.List;

public interface ValidateService <T>{
    boolean checkTrung(String ten, List<T> existingList);
}
