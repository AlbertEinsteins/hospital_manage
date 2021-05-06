package com.graduate.hospital_manage.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.hospital_manage.model.Dict;
import com.graduate.hospital_manage.model.Log;
import com.graduate.hospital_manage.model.constant.ELogLevel;
import com.graduate.hospital_manage.service.DictService;
import com.graduate.hospital_manage.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class LogUtils {
    private Logger logger = LoggerFactory.getLogger(LogUtils.class) ;


    @Autowired
    private LogService logService ;

    @Autowired
    private DictService dictService ;

    private Map<String, Integer> logLevelWithId ;

    @PostConstruct
    private void init() {
        List<Dict> types = this.dictService.findAllEventTypes();
        this.logLevelWithId = types.stream()
                .collect(Collectors.toMap(Dict::getValue, Dict::getId)) ;

    }

    /**
     * 向数据库写日志
     * @param level 日志级别
     * @param describe 描述内容
     * @param operate 操作状态
     */
    public void writeLog(ELogLevel level, String describe, String operate) {
        Integer typeId = logLevelWithId.get(level.name()) ;
        Log log = Log.create(typeId, describe, operate);

        logger.info("{}", log) ;
        this.logService.save(log) ;
    }

    /**
     * 从数据库查询日志
     * @param level
     * @param from
     * @param page
     * @return
     */
    public IPage<Log> readLogByLevel(ELogLevel level, LocalDateTime from, Page<Log> page) {
        Log query = new Log()
                .setTypeId(logLevelWithId.get(level != null ? level.name() : null))
                .setHappenTime(from) ;
        return this.logService.page(page, Wrappers.query(query).orderByDesc("happen_time"));
    }
}
