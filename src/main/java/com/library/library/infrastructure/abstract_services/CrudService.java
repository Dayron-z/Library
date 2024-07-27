package com.library.library.infrastructure.abstract_services;

import com.library.library.utils.enums.SortType;
import org.springframework.data.domain.Page;

public interface CrudService<RQ, RS, ID> {
    public RS create(RQ request);
    public Page<RS> getAll(int page, int size, SortType sortType);
/*    public RS update (RQ request, ID id);*/
    public RS findById(ID id);
    public void delete(ID id);
}
