package socialNetwork.service;

import socialNetwork.model.AppUser;

import java.util.Optional;

public interface IAppUserService {
    Iterable<AppUser> findAll();

    AppUser findByName(String name);

    Optional<AppUser> findById(Long id);

    void save(AppUser appUser);

    void edit(AppUser appUser);

    void delete(AppUser appUser);
}
