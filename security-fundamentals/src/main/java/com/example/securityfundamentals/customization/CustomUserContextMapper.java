package com.example.securityfundamentals.customization;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserContextMapper implements UserDetailsContextMapper {

    @Resource
    private DataSource dataSource;

    private static final String loadUserByUsernameQuery = "select username, password, enabled, customField from users where username=?";

    @Override
    public UserDetails mapUserFromContext(DirContextOperations dirContextOperations, String s, Collection<? extends GrantedAuthority> collection) {

        final CustomUserDetails customUserDetails = new CustomUserDetails(
                dirContextOperations.getStringAttribute("uid"),
                "<hidden>",
                List.of()
        );

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.queryForObject(loadUserByUsernameQuery, new RowMapper<CustomUserDetails>() {
            @Override
            public CustomUserDetails mapRow(ResultSet resultSet, int i) throws SQLException {
                customUserDetails.setCustomField(resultSet.getString("customField"));
                return customUserDetails;
            }
        }, dirContextOperations.getStringAttribute("uid"));

        return customUserDetails;
    }

    @Override
    public void mapUserToContext(UserDetails userDetails, DirContextAdapter dirContextAdapter) {

    }
}
