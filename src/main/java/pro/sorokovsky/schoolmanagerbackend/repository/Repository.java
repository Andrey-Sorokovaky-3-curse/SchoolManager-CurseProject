package pro.sorokovsky.schoolmanagerbackend.repository;

import java.util.Optional;

public interface Repository<Model> {
    Optional<Model> findById(Long id);
    Model create(Model model);
    Model update(Model model, Long id);
    void delete(Long id);
}
