package com.techfixsolutions.techfix.features.users;

import com.techfixsolutions.techfix.features.users.models.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

}
