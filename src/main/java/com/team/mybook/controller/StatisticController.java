package com.team.mybook.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team.mybook.data.entity.Statistic;
import com.team.mybook.data.entity.User;
import com.team.mybook.data.repository.StatisticRepository;
import com.team.mybook.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path="/api/statistic")
public class StatisticController {

    @Autowired
    private StatisticRepository statisticRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public void addNewStatistic (/*HttpServletResponse response,*/ @RequestBody Statistic requestStatistic){
        Statistic statistic = new Statistic(requestStatistic.getStatus(), requestStatistic.getScore(), requestStatistic.getType(),
                requestStatistic.getTime(), requestStatistic.getCurrentPage(), requestStatistic.getUser(), requestStatistic.getBook());
        statisticRepository.save(statistic);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Statistic> getAllStatistics(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return statisticRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update")
    public void updateStatistic (@RequestBody Statistic requestStatistic){
        Statistic statistic = statisticRepository.findStatisticByStatisticID(requestStatistic.getStatisticID());

        statistic.setCurrentPage(requestStatistic.getCurrentPage());
        statistic.setScore(requestStatistic.getScore());
        statistic.setStatus(requestStatistic.getStatus());
        statistic.setTime(requestStatistic.getTime());
        statistic.setType(requestStatistic.getType());

        statisticRepository.save(statistic);
    }
}
