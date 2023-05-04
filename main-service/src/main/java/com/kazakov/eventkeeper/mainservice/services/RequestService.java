package com.kazakov.eventkeeper.mainservice.services;

import org.springframework.stereotype.Service;
import com.kazakov.eventkeeper.mainservice.dto.RequestDto;

import java.util.List;

@Service
public interface RequestService {
    List<RequestDto> findAllUserRequests(Long userId);

    RequestDto createRequest(Long userId, Long eventId);

    RequestDto cancelRequest(Long userId, Long requestId);
}
