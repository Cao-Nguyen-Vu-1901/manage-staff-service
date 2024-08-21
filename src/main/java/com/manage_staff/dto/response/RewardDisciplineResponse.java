package com.manage_staff.dto.response;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class RewardDisciplineResponse {

    String id;

    String name;

    String content;

    LocalDate beginDate;

    LocalDate expiryDate;

    String creatorId;
}
