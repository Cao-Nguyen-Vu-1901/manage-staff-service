package com.manage_staff.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeaveDayResponse {

    String id;

    String name;

    LocalDate startDate;

    LocalDate endDate;

    String regulation;

    String reason;
}
