package repository;


import model.Usuario;

import java.util.Optional;

public interface UserRepository {
    void saveUser(String username, String password);

    Optional<Usuario> getUserByUsername(String username);
}
