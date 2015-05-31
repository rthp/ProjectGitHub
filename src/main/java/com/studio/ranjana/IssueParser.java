package com.studio.ranjana;


import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class IssueParser {
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public List<Issue> parseIssues(String jsonContent) {
        Gson gson = new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .setFieldNamingPolicy(
                        FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        Type collectionType = new TypeToken<List<Issue>>() {}.getType();
        List<Issue> issues = gson.fromJson(jsonContent, collectionType);
        return issues;
    }
}
