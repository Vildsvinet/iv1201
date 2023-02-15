package se.kth.iv1201.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import se.kth.iv1201.domain.Person;
import se.kth.iv1201.repository.PersonRepository;

import java.util.Optional;

import static se.kth.iv1201.presentation.person.PersonController.LOGIN_PAGE_URL;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final PersonRepository personRepository;
    private static final String ROLE_APPLICANT = "ROLE_APPLICANT";
    private static final String ROLE_RECRUITER = "ROLE_RECRUITER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    public WebSecurityConfig(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/createUser").permitAll()
                        .requestMatchers("/review").hasAuthority(ROLE_RECRUITER)
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/" + LOGIN_PAGE_URL)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> personRepository.findPersonByUsername(username)
                .map(person -> User.withUsername(person.getUsername())
//                        .password(person.getPassword())
                        .password(getUnencryptedPassword(username))
                        .authorities(getAuthority(person))
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    // TODO Map a password encoder instead
    private String getUnencryptedPassword(String username) {
        Optional<Person> user = personRepository.findPersonByUsername(username);
        String password = user.map(Person::getPassword).get();
        String noop = "{noop}";
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        return noop+password;
    }

    private SimpleGrantedAuthority getAuthority(Person person) {
        System.out.println("Role ID: " + person.getRole_id());
        String roleName = switch (person.getRole_id()) {
            case 0 -> ROLE_ADMIN;
            case 1 -> ROLE_RECRUITER;
            case 2 -> ROLE_APPLICANT;
            default -> throw new IllegalArgumentException("Invalid role id: " + person.getRole_id());
        };
        return new SimpleGrantedAuthority(roleName);
    }

}

