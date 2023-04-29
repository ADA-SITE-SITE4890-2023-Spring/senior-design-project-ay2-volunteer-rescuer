package com.java.sdpprojectay2.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Component;

@Component
public class PageUtils {

    public Pageable getPageConfig(Integer page, Integer limit, Integer ascending, String orderBy, boolean sortByJPA) {
        return PageRequest.of(page != null ? page : 0, limit != null ? limit : 20,
                sortByJPA ? getJPASort(ascending, orderBy != null && orderBy.length() > 0 ? orderBy : "id")
                        : getSort(ascending, orderBy != null ? orderBy : "id")
        );
    }

    private Sort getSort(int ascending, String orderBy) {
        return Sort.by(ascending == 0 ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy);
    }

    public JpaSort getJPASort(int ascending, String orderBy) {
        return JpaSort.unsafe(ascending == 0 ? Sort.Direction.ASC : Sort.Direction.DESC, "(" + changeSortName(orderBy) + ")");
    }

    private String changeSortName(String args) {
        String[] split = args.split("");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : split) {
            if (Character.isUpperCase(s.charAt(0))) {
                stringBuilder.append("_").append(s.toLowerCase());
            } else {
                stringBuilder.append(s);
            }
        }
        return stringBuilder.toString();
    }
}
