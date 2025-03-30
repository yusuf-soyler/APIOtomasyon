package payload;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetNegativePayloadGenerator {

    // Eksik name alanı
    public static Map<String, Object> pet_missing_name() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", 5001);
        payload.put("category", Map.of("id", 1, "name", "dog"));
        payload.put("photoUrls", List.of("https://image.com/1"));
        payload.put("tags", List.of(Map.of("id", 1, "name", "tag1")));
        payload.put("status", "available");
        // name eksik bırakıldı
        return payload;
    }

    // id string verildi (geçersiz tip)
    public static Map<String, Object> pet_with_invalid_id_type() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", "abc");  // Hatalı: int yerine string
        payload.put("name", "KırıkPet");
        payload.put("category", Map.of("id", 1, "name", "cat"));
        payload.put("photoUrls", List.of("https://image.com/2"));
        payload.put("tags", List.of(Map.of("id", 2, "name", "tag2")));
        payload.put("status", "available");
        return payload;
    }

    // id eksik
    public static Map<String, Object> pet_missing_id() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "Kayıpsız");
        payload.put("category", Map.of("id", 2, "name", "hamster"));
        payload.put("photoUrls", List.of("https://image.com/3"));
        payload.put("tags", List.of(Map.of("id", 3, "name", "tag3")));
        payload.put("status", "available");
        // id bilinçli olarak eklenmedi
        return payload;
    }

    // Eksik photoUrls alanı
    public static Map<String, Object> pet_missing_photoUrls() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", 5002);
        payload.put("name", "Ghost");
        payload.put("category", Map.of("id", 4, "name", "wolf"));
        payload.put("tags", List.of(Map.of("id", 4, "name", "tag4")));
        payload.put("status", "available");
        // photoUrls eksik bırakıldı
        return payload;
    }



}
