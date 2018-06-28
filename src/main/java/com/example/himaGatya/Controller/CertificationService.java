//package com.example.himaGatya;
//
//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.example.himaGatya.entity.Certifications;
//
//@Service
//public class CertificationService implements UserDetailsService {
//
//    @Autowired
//    private CertificationRepository repository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
//        if (mail == null || "".equals(mail)) {
//            throw new UsernameNotFoundException("Username is empty");
//        }
//
//        Certifications certification = repository.findByMail(mail);
//        if (certification == null) {
//            throw new UsernameNotFoundException("User not found: " + mail);
//        }
//        Certifications cer = new Certifications(certification.getMail(),certification.getPassword());
//
//        return cer;
//    }
//    
///*
//    @Transactional
//    public void registerAdmin(String username, String password, String mailAddress) {
//        Certifications user = new Certifications(username, passwordEncoder.encode(password), mailAddress);
//        user.setAdmin(true);
//        repository.save(user);
//    }
//    */
///*
//    @Transactional
//    public void registerUser(String username, String password, String mailAddress) {
//        User user = new User(username, passwordEncoder.encode(password), mailAddress);
//        repository.save(user);
//    }
//    */
//
//}