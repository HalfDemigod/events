package com.kazakov.eventkeeper.statsserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.kazakov.eventkeeper.statsserver.model.EndpointHit;
import com.kazakov.eventkeeper.statsserver.model.Stats;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<EndpointHit, Long> {
    @Query("select new com.kazakov.eventkeeper.statsserver.model.Stats(e.app, e.uri, count(e.ip)) from EndpointHit e " +
            "where e.timestamp >= ?1 and timestamp <= ?2 and e.uri in (?3)" +
            "group by e.app, e.uri " +
            "order by count(e.ip) desc")
    List<Stats> findUniqueEndpointHitsByUriInAndTimeStampAfterAndTimeStampBefore(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("select new com.kazakov.eventkeeper.statsserver.model.Stats(e.app, e.uri, count(e.ip)) from EndpointHit e " +
            "where e.timestamp >= ?1 and timestamp <= ?2 and e.uri in (?3)" +
            "group by e.app, e.uri " +
            "order by count(e.ip) desc")
    List<Stats> findEndpointHitsByUriInAndTimeStampAfterAndTimeStampBefore(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("select new com.kazakov.eventkeeper.statsserver.model.Stats(e.app, e.uri, count(e.ip)) from EndpointHit e " +
            "where e.timestamp >= ?1 and timestamp <= ?2" +
            "group by e.app, e.uri " +
            "order by count(e.ip) desc")
    List<Stats> findUniqueEndpointHitsByTimeStampAfterAndTimeStampBefore(LocalDateTime start, LocalDateTime end);

    @Query("select new com.kazakov.eventkeeper.statsserver.model.Stats(e.app, e.uri, count(e.ip)) from EndpointHit e " +
            "where e.timestamp >= ?1 and timestamp <= ?2" +
            "group by e.app, e.uri " +
            "order by count(e.ip) desc")
    List<Stats> findEndpointHitsByTimeStampAfterAndTimeStampBefore(LocalDateTime start, LocalDateTime end);

}
