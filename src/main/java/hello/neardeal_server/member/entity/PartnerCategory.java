package hello.neardeal_server.member.entity;

public enum PartnerCategory {

    없음("NONE"),
    총학생회("STUDENT_COUNCIL"),
    간호대학("NURSING"),
    경상대학("ECONOMICS_AND_BUSINESS"),
    공과대학("ENGINEERING"),
    농생대학("AGRICULTURE_AND_LIFE_SCIENCES"),
    사범대학("EDUCATION"),
    사회과학대학("SOCIAL_SCIENCES"),
    생활과학대학("HUMAN_ECOLOGY"),
    약학대학("PHARMACY"),
    예술대학("ARTS"),
    의과대학("MEDICINE"),
    인문대학("HUMANITIES"),
    자연과학대학("NATURAL_SCIENCES"),
    치과대학("DENTISTRY"),
    국제이공학부("INTERNATIONAL_SCHOOL_OF_ENGINEERING"),
    수의과대학("VETERINARY_MEDICINE"),
    융합자율전공학부("INTERDISCIPLINARY_AND_AUTONOMOUS_MAJORS"),
    융합학부("SCHOOL_OF_CONVERGENCE"),
    한옥학과("DEPARTMENT_OF_HANOK"),
    환경생명자원대학("ENVIRONMENTAL_AND_BIORESOURCE_SCIENCES");

    private final String englishName;

    PartnerCategory(String englishName) {
        this.englishName = englishName;
    }

    public String getEnglishName() {
        return englishName;
    }

//    NONE,                    // 없음
//    NURSING,                 // 간호대학
//    ECONOMICS_AND_BUSINESS,  // 경상대학
//    ENGINEERING,             // 공과대학
//    AGRICULTURE_AND_LIFE_SCIENCES, // 농생대학
//    EDUCATION,               // 사범대학
//    SOCIAL_SCIENCES,         // 사회과학대학
//    HUMAN_ECOLOGY,           // 생활과학대학
//    PHARMACY,                // 약학대학
//    ARTS,                    // 예술대학
//    MEDICINE,                // 의과대학
//    HUMANITIES,              // 인문대학
//    NATURAL_SCIENCES,        // 자연과학대학
//    DENTISTRY,               // 치과대학
//    INTERNATIONAL_SCHOOL_OF_ENGINEERING, // 국제이공학부
//    VETERINARY_MEDICINE,     // 수의과대학
//    INTERDISCIPLINARY_AND_AUTONOMOUS_MAJORS, // 융합자율전공학부
//    SCHOOL_OF_CONVERGENCE,   // 융합학부
//    DEPARTMENT_OF_HANOK,     // 한옥학과
//    ENVIRONMENTAL_AND_BIORESOURCE_SCIENCES // 환경생명자원대학
}
