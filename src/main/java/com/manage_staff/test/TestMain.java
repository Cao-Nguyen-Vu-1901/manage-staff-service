package com.manage_staff.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manage_staff.entity.Permission;

public class TestMain {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println((objectMapper.writeValueAsString(
                Permission.builder().name("abc").description("cds").build())));
    }
}
