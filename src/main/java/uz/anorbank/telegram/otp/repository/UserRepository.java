package uz.anorbank.telegram.otp.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import uz.anorbank.telegram.otp.entity.User;

@Repository
public interface UserRepository extends R2dbcRepository<User, String> {
}
