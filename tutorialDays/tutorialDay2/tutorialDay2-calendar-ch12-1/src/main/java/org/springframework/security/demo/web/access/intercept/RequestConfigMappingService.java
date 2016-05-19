package org.springframework.security.demo.web.access.intercept;

import java.util.List;

public interface RequestConfigMappingService {

    List<RequestConfigMapping> getRequestConfigMappings();

}