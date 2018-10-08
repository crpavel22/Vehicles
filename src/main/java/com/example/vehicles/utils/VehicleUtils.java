package com.example.vehicles.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleUtils {

    public static Predicate getPredicate(List<SearchCriteria> params, CriteriaBuilder builder, Root r, Predicate predicate) {
        for (SearchCriteria param : params) {
            if (StringUtils.equalsIgnoreCase(param.getOperation(),">")) {
                predicate = builder.and(predicate,
                        builder.greaterThanOrEqualTo(r.get(param.getKey()),
                                param.getValue().toString()));
            } else if (StringUtils.equalsIgnoreCase(param.getOperation(),"<")) {
                predicate = builder.and(predicate,
                        builder.lessThanOrEqualTo(r.get(param.getKey()),
                                param.getValue().toString()));
            } else if (StringUtils.equalsIgnoreCase(param.getOperation(),":")) {
                if (r.get(param.getKey()).getJavaType() == String.class) {
                    predicate = builder.and(predicate,
                            builder.like(r.get(param.getKey()),
                                    "%" + param.getValue() + "%"));
                } else {
                    predicate = builder.and(predicate,
                            builder.equal(r.get(param.getKey()), param.getValue()));
                }
            }
        }
        return predicate;
    }

    public static void retriveParams(@RequestParam(value = "search", required = false) String search, List<SearchCriteria> params) {
        if (StringUtils.isNotBlank(search)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+)");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1),
                        matcher.group(2), matcher.group(3)));
            }
        }
    }

}
