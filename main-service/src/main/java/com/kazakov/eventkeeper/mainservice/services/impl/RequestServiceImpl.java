package com.kazakov.eventkeeper.mainservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kazakov.eventkeeper.mainservice.dao.EventRepository;
import com.kazakov.eventkeeper.mainservice.dao.RequestRepository;
import com.kazakov.eventkeeper.mainservice.dao.UserRepository;
import com.kazakov.eventkeeper.mainservice.dto.RequestDto;
import com.kazakov.eventkeeper.mainservice.dto.mappers.RequestMapper;
import com.kazakov.eventkeeper.mainservice.exceptions.EventNotFoundException;
import com.kazakov.eventkeeper.mainservice.exceptions.RequestCreateDeniedException;
import com.kazakov.eventkeeper.mainservice.exceptions.RequestNotFoundException;
import com.kazakov.eventkeeper.mainservice.exceptions.UserNotFoundException;
import com.kazakov.eventkeeper.mainservice.model.*;
import com.kazakov.eventkeeper.mainservice.services.RequestService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final RequestMapper requestMapper;

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with id=%d was not found", id)));
    }

    private Event findEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(
                        String.format("Event with id=%d was not found", id)));
    }

    private Request findRequestById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(
                        String.format("Request with id=%d was not found", id)));
    }

    @Override
    public List<RequestDto> findAllUserRequests(Long userId) {
        User user = findUserById(userId);
        return requestRepository.findAllByRequester(user)
                .stream()
                .map(requestMapper::requestToRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public RequestDto createRequest(Long userId, Long eventId) {
        User user = findUserById(userId);
        Event event = findEventById(eventId);
        if (event.getInitiator().equals(user)) {
            throw new RequestCreateDeniedException(
                    String.format("User with id=%d is initiator of event with id=%d.", userId, eventId));
        }
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new RequestCreateDeniedException("Request can be only to published events.");
        }
        List<Request> requests = requestRepository.findAllByEventAndStatus(event, RequestStatus.CONFIRMED);
        if (event.getParticipantLimit() != 0 && requests.size() + 1 > event.getParticipantLimit()) {
            throw new RequestCreateDeniedException(
                    String.format("Participants limit of the event with id=%d is reached.", eventId));
        }
        if (requestRepository.findAllByRequesterAndEvent(user, event).isPresent()) {
            throw new RequestCreateDeniedException(
                    String.format("Request on event with id=%d by user with id=%d already exist.", eventId, userId));
        }

        Request request = Request.builder()
                .event(event)
                .requester(user)
                .created(LocalDateTime.now())
                .status(event.getRequestModeration() ? RequestStatus.PENDING : RequestStatus.CONFIRMED)
                .build();
        return requestMapper.requestToRequestDto(requestRepository.save(request));
    }

    @Override
    public RequestDto cancelRequest(Long userId, Long requestId) {
        findUserById(userId);
        Request request = findRequestById(requestId);
        request.setStatus(RequestStatus.CANCELED);

        return requestMapper.requestToRequestDto(requestRepository.save(request));
    }
}
