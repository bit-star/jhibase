package com.lazulite.base.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.lazulite.base.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.lazulite.base.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.lazulite.base.domain.User.class.getName());
            createCache(cm, com.lazulite.base.domain.Authority.class.getName());
            createCache(cm, com.lazulite.base.domain.User.class.getName() + ".authorities");
            createCache(cm, com.lazulite.base.domain.UserProduct.class.getName());
            createCache(cm, com.lazulite.base.domain.CspaceFile.class.getName());
            createCache(cm, com.lazulite.base.domain.GovernmentReport.class.getName());
            createCache(cm, com.lazulite.base.domain.GovernmentReport.class.getName() + ".cspaceFiles");
            createCache(cm, com.lazulite.base.domain.HistorySearch.class.getName());
            createCache(cm, com.lazulite.base.domain.LocationDTO.class.getName());
            createCache(cm, com.lazulite.base.domain.LocationVM.class.getName());
            createCache(cm, com.lazulite.base.domain.LocationFlat.class.getName());
            createCache(cm, com.lazulite.base.domain.FmpSubCompany.class.getName());
            createCache(cm, com.lazulite.base.domain.FmpSubCompany.class.getName() + ".serviceWindows");
            createCache(cm, com.lazulite.base.domain.ServiceWindow.class.getName());
            createCache(cm, com.lazulite.base.domain.MsgReceiverGroup.class.getName());
            createCache(cm, com.lazulite.base.domain.MsgReceiverGroup.class.getName() + ".uucDepartmentTrees");
            createCache(cm, com.lazulite.base.domain.MsgReceiverGroup.class.getName() + ".uucUserBaseinfos");
            createCache(cm, com.lazulite.base.domain.UucDepartmentTree.class.getName());
            createCache(cm, com.lazulite.base.domain.UucUserBaseinfo.class.getName());
            createCache(cm, com.lazulite.base.domain.FmpSubCompany.class.getName() + ".msgReceiverGroups");
            createCache(cm, com.lazulite.base.domain.FmpSubCompany.class.getName() + ".pushSubjects");
            createCache(cm, com.lazulite.base.domain.PushSubject.class.getName());
            createCache(cm, com.lazulite.base.domain.UucDepartmentTree.class.getName() + ".msgReceiverGroups");
            createCache(cm, com.lazulite.base.domain.UucUserBaseinfo.class.getName() + ".msgReceiverGroups");
            createCache(cm, com.lazulite.base.domain.ProcessMsgTask.class.getName());
            createCache(cm, com.lazulite.base.domain.PushSubject.class.getName() + ".processMsgTasks");
            createCache(cm, com.lazulite.base.domain.MpHotspot.class.getName());
            createCache(cm, com.lazulite.base.domain.FmpSubCompany.class.getName() + ".mpHotspots");
            createCache(cm, com.lazulite.base.domain.MicroAppGroup.class.getName());
            createCache(cm, com.lazulite.base.domain.MicroAppGroup.class.getName() + ".uucDepartmentTrees");
            createCache(cm, com.lazulite.base.domain.MicroAppGroup.class.getName() + ".uucUserBaseinfos");
            createCache(cm, com.lazulite.base.domain.MicroAppGroup.class.getName() + ".fmpMicroApps");
            createCache(cm, com.lazulite.base.domain.FmpMicroApp.class.getName());
            createCache(cm, com.lazulite.base.domain.FmpMicroApp.class.getName() + ".fmpWidgetInfos");
            createCache(cm, com.lazulite.base.domain.FmpMicroApp.class.getName() + ".uucDepartmentTrees");
            createCache(cm, com.lazulite.base.domain.FmpMicroApp.class.getName() + ".usableUsers");
            createCache(cm, com.lazulite.base.domain.FmpMicroApp.class.getName() + ".fmpSubCompanies");
            createCache(cm, com.lazulite.base.domain.FmpMicroApp.class.getName() + ".collectionUsers");
            createCache(cm, com.lazulite.base.domain.FmpMicroApp.class.getName() + ".microAppGroups");
            createCache(cm, com.lazulite.base.domain.UucDepartmentTree.class.getName() + ".usables");
            createCache(cm, com.lazulite.base.domain.UucDepartmentTree.class.getName() + ".managers");
            createCache(cm, com.lazulite.base.domain.UucDepartmentTree.class.getName() + ".fmpSubCompanies");
            createCache(cm, com.lazulite.base.domain.UucUserBaseinfo.class.getName() + ".collectionFmpMicroApps");
            createCache(cm, com.lazulite.base.domain.UucUserBaseinfo.class.getName() + ".usableFmpMicroApps");
            createCache(cm, com.lazulite.base.domain.FmpSubCompany.class.getName() + ".managerUsers");
            createCache(cm, com.lazulite.base.domain.FmpSubCompany.class.getName() + ".banners");
            createCache(cm, com.lazulite.base.domain.FmpSubCompany.class.getName() + ".fmpMicroAppTypes");
            createCache(cm, com.lazulite.base.domain.FmpSubCompany.class.getName() + ".createdApps");
            createCache(cm, com.lazulite.base.domain.FmpSubCompany.class.getName() + ".microAppGroups");
            createCache(cm, com.lazulite.base.domain.FmpSubCompany.class.getName() + ".fmpMicroApps");
            createCache(cm, com.lazulite.base.domain.FmpSubCompany.class.getName() + ".uucDepartmentTrees");
            createCache(cm, com.lazulite.base.domain.FmpMicroAppType.class.getName());
            createCache(cm, com.lazulite.base.domain.FmpMicroAppType.class.getName() + ".fmpMicroApps");
            createCache(cm, com.lazulite.base.domain.FmpWidgetInfo.class.getName());
            createCache(cm, com.lazulite.base.domain.Banner.class.getName());
            createCache(cm, com.lazulite.base.domain.DdUser.class.getName());
            createCache(cm, com.lazulite.base.domain.ManagerUser.class.getName());
            createCache(cm, com.lazulite.base.domain.ManagerUser.class.getName() + ".uucDepartmentTrees");
            createCache(cm, com.lazulite.base.domain.PortalRouting.class.getName());
            createCache(cm, com.lazulite.base.domain.DdUserPortalRouting.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }
}
