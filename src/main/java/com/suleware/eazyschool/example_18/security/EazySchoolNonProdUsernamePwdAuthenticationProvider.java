package com.suleware.eazyschool.example_18.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.suleware.eazyschool.example_18.model.Person;
import com.suleware.eazyschool.example_18.model.Roles;
import com.suleware.eazyschool.example_18.repository.PersonRepository;

@Component
@Profile("!prod")
public class EazySchoolNonProdUsernamePwdAuthenticationProvider implements
    AuthenticationProvider {

  private PersonRepository personRepository;
  private PasswordEncoder passwordEncoder;

  public EazySchoolNonProdUsernamePwdAuthenticationProvider(
      PersonRepository personRepository,
      PasswordEncoder passwordEncoder
  ) {
    this.personRepository = personRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  /**
   * This method will be used to authenticate users. If the user name and the
   * password match the record in the database, the user will be authenticated
   * and a successful authentication object will be returned. If the user name
   * and the password do not match the record in the database, a
   * <code>BadCredentialsException</code> will be thrown.
   *
   * @param authentication
   *                         The authentication object containing the user name
   *                         and the password.
   * 
   * @return A successful authentication object if the user name and the
   *         password match the record in the database.
   * 
   * @throws AuthenticationException
   *                                   If the user name and the password do not
   *                                   match the record in the database.
   */
  /****** 8f9af872-d654-4ba7-a047-f80532679911 *******/
  public Authentication authenticate(
      Authentication authentication
  ) throws AuthenticationException {
    String email = authentication.getName();
    Person person = personRepository.findByEmail(email);
    if (null != person && person.getPersonId() > 0) {
      return new UsernamePasswordAuthenticationToken(
          person.getEmail(),
          null,
          getGrantedAuthorities(person.getRoles())
      );
    } else {
      throw new BadCredentialsException("Invalid credentials!");
    }
  }

  @Override
  public boolean supports(
      Class<?> authentication
  ) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

  private List<GrantedAuthority> getGrantedAuthorities(
      Roles roles
  ) {
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities
        .add(new SimpleGrantedAuthority("ROLE_" + roles.getRoleName()));
    return grantedAuthorities;
  }

}
