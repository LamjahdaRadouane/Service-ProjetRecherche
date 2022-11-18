
package com.example.serviceprojetrecherche.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="Service-Section")
public interface SectionServiceClient {
    @GetMapping("/sections/{id}")
    public Section findSectionsById(@PathVariable(name="id") Long id);
    @GetMapping("/sections")
    public PagedModel<Section> findAllSections();
}
