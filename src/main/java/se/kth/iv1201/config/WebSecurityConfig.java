package se.kth.iv1201.config;

import org.hibernate.Internal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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

import static se.kth.iv1201.presentation.person.PersonController.*;

/**
 * This class is used to configure the security filter chain.
 * Specifically, it specifies which pages can be reached by which user types,
 * and encrypts the passwords.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String ROLE_APPLICANT = "ROLE_APPLICANT";
    private static final String ROLE_RECRUITER = "ROLE_RECRUITER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    public WebSecurityConfig() {}

    /**
     * Sets up the security filter chain. This is the entry point into the Spring Security configuration.
     *
     * @param http The HttpSecurity object to configure.
     * @return The security filter chain.
     * @throws Exception If an error occurs.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/css/style.css", "/images/**").permitAll()
                        .requestMatchers("/", "/" + LOGIN_PAGE_URL, "/" + CREATE_USER_PAGE_URL, "/error").permitAll()
                        .requestMatchers("/" + HOME_RECRUITER_URL, "/" + APPLICATIONS_URL).hasAuthority(ROLE_RECRUITER)
                        .requestMatchers("/" + HOME_APPLICANT_URL).hasAuthority(ROLE_APPLICANT)
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/" + LOGIN_PAGE_URL)
                        .permitAll()
//                        .defaultSuccessUrl(DEFAULT_PAGE_URL, false)
                        .successHandler((req, res, auth) -> {
                            for (GrantedAuthority authority : auth.getAuthorities()) {
                                if (authority.getAuthority().equals(ROLE_APPLICANT)) {
                                    res.sendRedirect("/" + HOME_APPLICANT_URL); // Redirect to applicant home page
                                    return;
                                } else if (authority.getAuthority().equals(ROLE_RECRUITER)) {
                                    res.sendRedirect("/" + HOME_RECRUITER_URL); // Redirect to recruiter home page
                                    return;
                                } else {
                                    res.sendRedirect("/error"); // Redirect to error page
                                    return;
                                }
                            }
                            throw new IllegalStateException("User has no role assigned");
                        })
//                        .failureUrl("/" + LOGIN_PAGE_URL + "?error=true")
                        .failureHandler((request, response, exception) -> {
                            request.getSession().setAttribute("username", request.getParameter("username"));
                            if (exception instanceof BadCredentialsException) {
                                response.sendRedirect("/login?badCredentials");
                            }
                            else if (exception instanceof InternalAuthenticationServiceException){
                                response.sendRedirect("/login?authenticationError");
                            }
                            else {
                                response.sendRedirect("/login?error");
                            }

                        })
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    /**
     * Takes the service for the application and builds a new service for Spring Security user handling.
     * @param recruitmentService
     * @return a service for the user details that is built by the repository
     */
    @Bean
    public UserDetailsService userDetailsService(RecruitmentService recruitmentService) {
        return username -> User.withUsername(username)
                .password(recruitmentService.getPersonByUsername(username).getPassword())
                .authorities(getAuthority(recruitmentService.getPersonByUsername(username).getRole_id()))
                .build();
    }

    /**
     * Encodes passwords using BCrypt. DelegatingPasswordEncoder is more flexible, but we're only supporting BCrypt.
     *
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