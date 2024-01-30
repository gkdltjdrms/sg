package util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BoardStatusConverter {

    private static final Map<String, String> koreanStatusMap;

    static {
        koreanStatusMap = new HashMap<>();
        koreanStatusMap.put("save", "임시저장");
        koreanStatusMap.put("wait", "결제대기");
        koreanStatusMap.put("checking", "결재중");
        koreanStatusMap.put("end", "결제완료");
        koreanStatusMap.put("Companion", "반려");
    }

    public static String getKoreanStatus(String flag) {
        return Optional.ofNullable(koreanStatusMap.get(flag)).orElse(flag);
    }
}
