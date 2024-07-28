package com.library.library.api.dto.request.custom_request;

import com.library.library.api.dto.request.used_request.BookRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultipleBookRequest {
    List<BookRequest> bookRequestList;
}
