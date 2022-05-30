package com.sekoding.example.demo.model.repos;

import com.sekoding.example.demo.model.entity.Clock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.websocket.server.PathParam;
import java.util.List;

public interface ClockRepo extends CrudRepository<Clock, Long> {

    @Query(value = "SELECT tbl_user.nama,* from tbl_clock JOIN tbl_user ON tbl_clock.user_id_id = tbl_user.id ORDER BY tbl_clock.id DESC", nativeQuery = true)
    public List<Clock> findAllDesc();

    @Query(value = "SELECT * FROM tbl_clock WHERE tbl_clock.user_id_id=:masuk ORDER BY id DESC LIMIT 1", nativeQuery = true)
    public List<Clock> findByIdDesc(@PathParam("masuk") Long masuk);

    @Query(value = "SELECT * from tbl_clock JOIN tbl_user ON tbl_clock.user_id_id = tbl_user.id where tbl_user.nik_manager=:team and tbl_clock.times >= current_date ORDER BY tbl_clock.id DESC", nativeQuery = true)
    public List<Clock> findByTeam(@PathParam("team") String team);

    @Query(value = "SELECT * FROM tbl_clock WHERE tbl_clock.user_id_id=:masuk ORDER BY id DESC LIMIT 10", nativeQuery = true)
    public List<Clock> findByIdHistory(@PathParam("masuk") Long masuk);

    @Query(value = "SELECT working FROM tbl_clock WHERE tbl_clock.user_id_id=:id ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Boolean findStatusByIdDesc(@PathParam("id") Long id);

    @Query(value = "SELECT (SELECT count(tbl_clock.working) FROM tbl_clock where tbl_clock.working=true AND date_part('hour', tbl_clock.times)=:jam AND date_part('day', tbl_clock.times)=date_part('day',current_date)) AS Clockin,(select count(tbl_clock.working) FROM tbl_clock where tbl_clock.working=false AND date_part('hour', tbl_clock.times)=:jam AND date_part('day', tbl_clock.times)=date_part('day',current_date)) as ClockOut", nativeQuery = true)
    public List<String> getTotalClockthisMonth(@PathParam("jam") Long jam);

    @Query(value = "SELECT (SELECT count(tbl_clock.working) FROM tbl_clock where tbl_clock.working=true AND date_part('hour', tbl_clock.times)=:jam AND date_part('day', tbl_clock.times)=date_part('day',current_date)-1) AS Clockin,(select count(tbl_clock.working) FROM tbl_clock where tbl_clock.working=false AND date_part('hour', tbl_clock.times)=:jam AND date_part('day', tbl_clock.times)=date_part('day',current_date)-1) as ClockOut", nativeQuery = true)
    public List<String> getTotalClockthisMonthMinOne(@PathParam("jam") Long jam);

    @Query(value = "SELECT (SELECT count(tbl_clock.working) FROM tbl_clock where tbl_clock.working=true AND date_part('month', tbl_clock.times)=:bulan) as ClockIN,(select count(tbl_clock.working) FROM tbl_clock where tbl_clock.working=false AND date_part('month', tbl_clock.times)=:bulan) as ClockOut", nativeQuery = true)
    public List<String> getTotalClockPerMonth(@PathParam("bulan") Long bulan);

}
