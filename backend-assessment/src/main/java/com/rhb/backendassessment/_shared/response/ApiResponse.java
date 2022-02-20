package com.rhb.backendassessment._shared.response;

import com.rhb.backendassessment._shared.constant.CodeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

  private CodeResponse code;
  private String message;
  private T data;
}
