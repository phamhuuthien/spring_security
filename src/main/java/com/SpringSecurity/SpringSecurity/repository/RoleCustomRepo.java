package com.SpringSecurity.SpringSecurity.repository;

import com.SpringSecurity.SpringSecurity.entity.Role;
import com.SpringSecurity.SpringSecurity.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.cglib.core.Transformer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoleCustomRepo {

//    native query
    @PersistenceContext
    private EntityManager entityManager;

    public List<String[]> getRole(User user){
        StringBuilder sql = new StringBuilder()
                .append("select r.name as name from users join learn_security.users_role ur on users.id = ur.users_id\n" +
                        "join roles r on ur.roles_id = r.id ");
        sql.append("Where 1=1 ");
        if(user.getEmail()!=null){
            sql.append(" and email = :email");
        }

        NativeQuery<Role> query =((Session)entityManager.getDelegate()).createNativeQuery(sql.toString());

        if(user.getEmail()!=null){
            query.setParameter("email", user.getEmail());
        }

        query.addScalar("name", StandardBasicTypes.STRING);

        query.setResultListTransformer(Transformers.aliasToBean(Role.class));

//        return query.list();

        List results = query.getResultList();

        if (results.isEmpty()) {
            return new ArrayList<>();
        }

        if (results.get(0) instanceof String) {
            return ((List<String>) results)
                    .stream()
                    .map(s -> new String[] { s })
                    .collect(Collectors.toList());
        } else {
            return (List<String[]>) results;
        }
    }
}
