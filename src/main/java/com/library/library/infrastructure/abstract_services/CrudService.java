package com.library.library.infrastructure.abstract_services;

public interface CrudService<RQ, RS, ID> {
    public RS create(RQ request);
    public RS getAll();
    public RS update (RQ request, ID id);
    public void delete(ID id);
}
