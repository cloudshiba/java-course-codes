package com.clooudshiba.dynamicdatasource.datasource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataSourceRouting extends AbstractRoutingDataSource {
    private PrimaryDataSourceConfig primaryDataSourceConfig;
    private SecondaryDataSourceConfig secondaryDataSourceConfig;
    private DataSourceContextHolder dataSourceContextHolder;

    public DataSourceRouting(DataSourceContextHolder dataSourceContextHolder, PrimaryDataSourceConfig primaryDataSourceConfig,
                             SecondaryDataSourceConfig secondaryDataSourceConfig) {
        this.primaryDataSourceConfig = primaryDataSourceConfig;
        this.secondaryDataSourceConfig = secondaryDataSourceConfig;
        this.dataSourceContextHolder = dataSourceContextHolder;

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceEnum.PRIMARY, primaryDataSource());
        dataSourceMap.put(DataSourceEnum.SECONDARY, secondaryDataSource());
        this.setTargetDataSources(dataSourceMap);
        this.setDefaultTargetDataSource(primaryDataSource());
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceContextHolder.getBranchContext();
    }

    public DataSource primaryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(primaryDataSourceConfig.getUrl());
        dataSource.setUsername(primaryDataSourceConfig.getUsername());
        dataSource.setPassword(primaryDataSourceConfig.getPassword());
        return dataSource;
    }

    public DataSource secondaryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(secondaryDataSourceConfig.getUrl());
        dataSource.setUsername(secondaryDataSourceConfig.getUsername());
        dataSource.setPassword(secondaryDataSourceConfig.getPassword());
        return dataSource;
    }
}
