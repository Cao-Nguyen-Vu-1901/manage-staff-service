package com.manage_staff.dto.response;

import java.time.LocalDate;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RewardDisciplineResponse {

    String id;

    String name;

    String content;

    LocalDate beginDate;

    LocalDate expiryDate;

    String creatorId;
}
