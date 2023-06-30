package com.kazakov.events.mainservice.services;

import com.kazakov.events.mainservice.dto.RequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequestService {
    List<RequestDto> findAllUserRequests(Long userId);

    RequestDto createRequest(Long userId, Long eventId);

    RequestDto cancelRequest(Long userId, Long requestId);
}
