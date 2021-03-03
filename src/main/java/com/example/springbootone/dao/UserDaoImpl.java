package com.example.springbootone.dao;

import com.example.springbootone.model.User;
import com.example.springbootone.model.requestdto.UserSearchRequest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Collections;
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

        sql.append(" order by id desc");

        sql.append(" limit " + (request.getPage() - 1) * request.getPageSize() + "," + request.getPageSize());

        System.out.println(sql.toString());

        Query dataQuery = entityManager.createNativeQuery(sql.toString());

        return dataQuery.getResultList();
    }

    /*
    自定义sql条件拼接查询
     */
    public Page<User> GetUserInfoListByPage(UserSearchRequest request, Pageable pageable) {
        StringBuilder datasql = new StringBuilder("select * from user where 1 = 1 ");
        StringBuilder countSql = new StringBuilder("select count(*) from user where 1 = 1 ");
        if (!request.getName().isEmpty()) {
            datasql.append("AND name = '" + request.getName() + "' ");
            countSql.append("AND name = '" + request.getName() + "' ");
        }

        if (request.getId() > 0) {
            datasql.append(" AND id = " + request.getId() + "");
            countSql.append(" AND id = " + request.getId() + "");
        }

        if (!request.getMobile().isEmpty()) {
            datasql.append(" AND mobile like '%" + request.getMobile() + "%'");
            countSql.append(" AND mobile like '%" + request.getMobile() + "%'");
        }

        System.out.println(countSql.toString());
        System.out.println(datasql.toString());

        Query countQuery = entityManager.createNativeQuery(countSql.toString());
        Query dataQuery = entityManager.createNativeQuery(datasql.toString());

        int offset = (int) pageable.getOffset();
        int size = pageable.getPageSize();

        dataQuery.setFirstResult(offset);
        dataQuery.setMaxResults(size);

        Long total = Long.valueOf(countQuery.getResultList().size());

        List<User> content = dataQuery.getResultList();

        return new PageImpl<User>(content, pageable, total);
    }
}
