package com.example.springbootone.dao;

import com.example.springbootone.model.User;
import com.example.springbootone.model.requestdto.UserSearchRequest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository("dao")
public class UserDaoImpl {
    @PersistenceContext
    private EntityManager entityManager;

    /*
    自定义sql条件拼接查询
     */
    public List<User> GetUserInfoList(UserSearchRequest request) {
        StringBuilder sql = new StringBuilder("select * from user where 1 = 1 ");
        if (!request.getName().isEmpty()) {
            sql.append("AND name = '" + request.getName() + "' ");
        }

        if (request.getId() > 0) {
            sql.append(" AND id = " + request.getId() + "");
        }

        if (!request.getMobile().isEmpty()) {
            sql.append(" AND mobile like '%" + request.getMobile() + "%'");
        }

        sql.append(" limit " + (request.getPage() - 1) * request.getPageSize() + "," + request.getPageSize());

        System.out.println(sql.toString());

        Query dataQuery = entityManager.createNativeQuery(sql.toString());

        return dataQuery.getResultList();
    }
}
