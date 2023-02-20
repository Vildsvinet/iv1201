package se.kth.iv1201.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import se.kth.iv1201.application.RecruitmentService;

import static se.kth.iv1201.presentation.person.PersonController.DEFAULT_PAGE_URL;
import static se.kth.iv1201.presentation.person.PersonController.LOGIN_PAGE_URL;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String ROLE_APPLICANT = "ROLE_APPLICANT";
    private static final String ROLE_RECRUITER = "ROLE_RECRUITER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    public WebSecurityConfig() {

    }

    /**
     * Sets up the security filter chain. This is the entry point into the Spring Security configuration.
     * @param http The HttpSecurity object to configure.
     * @return The security filter chain.
     * @throws Exception If an error occurs.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/login", "/createUser").permitAll()
                        .requestMatchers("/homeRecruiter").hasAuthority(ROLE_RECRUITER)
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/" + LOGIN_PAGE_URL)
                        .permitAll()
                        .defaultSuccessUrl(DEFAULT_PAGE_URL, false)
                        .successHandler((req, res, auth) -> {
                            for (GrantedAuthority authority : auth.getAuthorities()) {
                                if (authority.getAuthority().equals("ROLE_APPLICANT")) {
                                    res.sendRedirect("/homeApplicant"); // Redirect to applicant home page
                                    return;
                                } else if (authority.getAuthority().equals("ROLE_RECRUITER")) {
                                    res.sendRedirect("/homeRecruiter"); // Redirect to recruiter home page
                                    return;
                                }
                            }
                            throw new IllegalStateException("User has no role assigned");
                        })
                )
                .logout(LogoutConfigurer::permitAll);


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(RecruitmentService recruitmentService) {
        return username -> User.withUsername(username)
                .password(recruitmentService.getPersonByUsername(username).getPassword())
                .authorities(getAuthority(recruitmentService.getPersonByUsername(username).getRole_id()))
                .build();
    }

    /**
     * Encodes passwords using BCrypt. DelegatingPasswordEncoder is more flexible, but we're only supporting BCrypt.
     * @return The BCrypt password encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private SimpleGrantedAuthority getAuthority(int id) {
        String roleName = switch (id) {
            case 0 -> ROLE_ADMIN;
            case 1 -> ROLE_RECRUITER;
            case 2 -> ROLE_APPLICANT;
            default -> throw new IllegalArgumentException("Invalid role id: " + id);
        };
        return new SimpleGrantedAuthority(roleName);
    }

}