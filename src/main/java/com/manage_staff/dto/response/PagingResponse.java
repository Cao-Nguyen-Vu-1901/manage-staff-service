package com.manage_staff.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagingResponse<T> {

    int code;
    int currentPage;
    int pageSize;
    String type;
    String value;
    String sortBy;
    String orderBy;
    long totalItem;
    int totalPage;
    T result;
}
