package com.team.mybook.data.repository;

import com.team.mybook.data.entity.Statistic;
import org.springframework.data.repository.CrudRepository;

public interface StatisticRepository extends CrudRepository<Statistic, Long> {
    Statistic findStatisticByStatisticID(long statisticID);
}
