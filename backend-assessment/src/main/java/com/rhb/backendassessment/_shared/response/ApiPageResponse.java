package com.rhb.backendassessment._shared.response;

import com.rhb.backendassessment._shared.constant.CodeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiPageResponse<T> {

  private CodeResponse code;
  private String message;
  private List<T> data;
  private Integer total;
  private Integer limit;
  private Integer offset;
}

