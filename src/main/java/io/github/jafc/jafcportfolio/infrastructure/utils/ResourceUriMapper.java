package io.github.jafc.jafcportfolio.infrastructure.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceUriMapper {

    private static final String API_BASE_URI = "/api/v1";
    public static final String USER_URI = API_BASE_URI + "/user";
    public static final String SKILL_URI = API_BASE_URI + "/skill";
    public static final String REVIEW_URI = API_BASE_URI + "/review";
    public static final String PROJECT_URI = API_BASE_URI + "/project";
    public static final String PROFESSIONAL_EXPERIENCE_URI = API_BASE_URI + "/professional";
    public static final String ACADEMIC_EXPERIENCE_URI = API_BASE_URI + "/academic";
}
