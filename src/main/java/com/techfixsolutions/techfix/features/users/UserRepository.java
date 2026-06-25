package com.techfixsolutions.techfix.features.users;

import com.techfixsolutions.techfix.features.users.models.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
