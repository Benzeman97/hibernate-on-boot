package com.benz.hib.jpa.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@ConfigurationProperties(prefix = "db")
@Profile({"db"})
@Getter
@Setter
public class HibernateProperties {

    private String driver;
    private String password;
    private String url;
    private String userName;
    private String dialect;
    private boolean showSql;
    private String ddlAuto;
    private String packageToScan;

}
